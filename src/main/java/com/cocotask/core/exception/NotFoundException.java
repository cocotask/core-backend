package com.cocotask.core.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Not Found!!")  // 404
public class NotFoundException extends Exception {
}