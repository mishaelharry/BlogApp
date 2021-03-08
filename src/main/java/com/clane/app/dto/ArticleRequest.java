/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.dto;

import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class ArticleRequest {
    
    private String title;
    
    private String slug;
    
    private String content;
    
}
