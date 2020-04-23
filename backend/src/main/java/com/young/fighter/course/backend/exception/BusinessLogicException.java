package com.young.fighter.course.backend.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BusinessLogicException extends RuntimeException {
    private String message;
}
