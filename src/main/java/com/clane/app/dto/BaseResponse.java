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
public class BaseResponse<T> {
    private Boolean succeeded;
    private String message;
    private T result;

    public BaseResponse(Boolean succeeded, String message, T result) {
        this.succeeded = succeeded;
        this.message = message;
        this.result = result;
    }
    
}
