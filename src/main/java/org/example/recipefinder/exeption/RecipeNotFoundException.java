package org.example.recipefinder.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a recipe is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)  // Denne annotation sikrer, at HTTP 404 returneres
public class RecipeNotFoundException extends RuntimeException {

    /**
     * Constructor for creating an exception with a message.
     *
     * @param message The error message.
     */
    public RecipeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for creating an exception with a message and a cause.
     *
     * @param message The error message.
     * @param cause The cause of the exception (another exception).
     */
    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
