package com.example.demo.controller;

import com.example.demo.bo.UpdateUserProfileRequest;
import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;
import com.example.demo.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @GetMapping("/sayHi")
    public String sayHi(){
        return "Hi, you are an authenticated user";
    }

    @Autowired
    private UserService userService;

    @PutMapping("/update-profile")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        UserResponse updatedUser = userService.updateUserProfile(request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/profile")
    public UserProfileResponse getUserProfile(Authentication authentication) {
        String username = authentication.name();
        return userService.getUserProfile(username);
    }


}
