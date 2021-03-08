/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.controller;

import com.clane.app.dto.BaseResponse;
import com.clane.app.dto.JwtResponse;
import com.clane.app.dto.LoginRequest;
import com.clane.app.dto.RegisterRequest;
import com.clane.app.model.User;
import com.clane.app.security.JwtTokenProvider;
import com.clane.app.service.UserService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    //Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), 
                        loginRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.generateAccessToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    //Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        if(userService.existsByEmail(registerRequest.getEmail())){
            return new ResponseEntity(new BaseResponse(false, "Email already exist", null), 
                    HttpStatus.OK);
        }
        
        User result = userService.saveUser(registerRequest);
        if (result != null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{id}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).body(new BaseResponse(true, "Registration Successful", result));
        } else {
            return ResponseEntity.ok(new BaseResponse(false, "Registeration Failed", null));
        }
    }
    
}
