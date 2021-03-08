/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class UserResponse {
    
    private Long id;

    private String name;

    private String email;
    
    private String bio;
}
