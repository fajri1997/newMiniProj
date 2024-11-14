package com.example.demo.service;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUserProfile(UpdateUserProfileRequest request);

    UserProfileResponse getUserProfile(String username);
}
