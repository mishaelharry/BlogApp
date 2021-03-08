/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.dto;

import com.clane.app.model.User;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class ArticleResponse {
    
    private Long id;

    private String title;
    
    private String slug;
    
    private String content;
    
    //private User user;
    
}
