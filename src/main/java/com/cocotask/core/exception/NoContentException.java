package com.cocotask.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NO_CONTENT, reason = "No content!!")  // 204
public class NoContentException extends Exception {
}
