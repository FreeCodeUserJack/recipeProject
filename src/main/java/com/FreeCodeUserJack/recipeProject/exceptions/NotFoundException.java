package com.FreeCodeUserJack.recipeProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// when this exception is thrown, http response will be 404 NOT_FOUND for that request
@ResponseStatus(HttpStatus.NOT_FOUND) // for RecipeController Test when recipe is not found
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // test in RecipeServiceImplTest, have RecipeServiceImpl throw NotFoundException when recipe not found
}
