package com.FreeCodeUserJack.recipeProject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    // Recipe and Ing both could have String as Id; not to duplicate code in both controller, put in controller advice
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class) // when NumberFormatException is thrown, Http Response is 400
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling Number Format Exception");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");

        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}

