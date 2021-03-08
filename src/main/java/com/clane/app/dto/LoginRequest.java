/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class LoginRequest {
    
    @NotBlank(message="Email is required")
    private String email;
    
    @NotBlank(message="Password is required")
    private String password;
}
