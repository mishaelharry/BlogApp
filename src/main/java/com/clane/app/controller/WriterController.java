/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.controller;

import com.clane.app.dto.ArticleResponse;
import com.clane.app.dto.BaseResponse;
import com.clane.app.dto.PagedResponse;
import com.clane.app.dto.UserResponse;
import com.clane.app.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/v1/writer")
public class WriterController {
    
    @Autowired
    private UserService userService;
    
    //Get all writer endpoint
    @GetMapping
    public ResponseEntity<BaseResponse<List<UserResponse>>> getWriters() {
        List<UserResponse> userResponses = userService.getUsers();
        if (userResponses != null) {
            return ResponseEntity.ok(new BaseResponse<>(true, "Writer retrieved successfully", userResponses));
        } else {
            return ResponseEntity.ok(new BaseResponse<>(false, "Writer not found", null));
        }
    }
}
