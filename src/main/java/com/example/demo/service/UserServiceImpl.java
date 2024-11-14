package com.example.demo.service;


import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override

    public UserResponse createUser(CreateUserRequest request) {
         UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getEmail());
        userEntity.setEmail(request.getEmail());
        userEntity.setPhoneNumber(request.getPhoneNumber());
        userEntity.setAddress(request.getAddress());
        userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userEntity.setRole(request.getRole());
        userEntity.setStatus(Status.ACTIVE);

         userEntity = userRepository.save(userEntity);

         AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(userEntity.getId());
        accountEntity.setBalance(0.0);

         accountRepository.save(accountEntity);

         UserResponse response = new UserResponse(userEntity.getId(), userEntity.getUsername());
        response.setUsername(userEntity.getUsername());
        response.setEmail(userEntity.getEmail());
        response.setPhoneNumber(userEntity.getPhoneNumber());
        response.setAddress(userEntity.getAddress());
        response.setStatus(userEntity.getStatus().name());

         return response;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse updateUserProfile(UpdateUserProfileRequest request) {
         UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found."));

         if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            user.setAddress(request.getAddress());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

         user = userRepository.save(user);

         UserResponse response = new UserResponse(user.getId(), user.getUsername());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAddress(user.getAddress());
        response.setStatus(user.getStatus() != null ? user.getStatus().name() : "UNKNOWN");

        return response;
    }


    @Override
    public UserProfileResponse getUserProfile(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getStatus().name()
        );
    }

}
