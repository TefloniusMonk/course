package com.young.fighter.course.backend.web.aspect;

import com.young.fighter.course.backend.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MessageHandler {
    @ExceptionHandler(BusinessLogicException.class)
    public void handleCustomException(BusinessLogicException ex) {
        //logic here
        log.error("Something happen...");
    }
}
