/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.exception;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class ErrorResponse {
    
    private boolean status;
    private Date timestamp;
    private String message;
    private List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        this.status = false;
        this.message = message;
        this.errors = errors;
        this.timestamp = new Date();
    }    
}
