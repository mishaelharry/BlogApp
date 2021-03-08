/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 *
 * @author hp
 */
@Getter
@Setter
@ToString
@Slf4j
public class BlogAppException extends RuntimeException {
    
    private HttpStatus status;

    private Object errors;

    public BlogAppException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
        log.info(message);
    }
    
    public BlogAppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        log.info(message);
    }
    
}
