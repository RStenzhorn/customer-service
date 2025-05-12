package de.rjst.cs.logic;

import de.rjst.cs.api.model.ValidationErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse handleInvalidArgument(final MethodArgumentNotValidException ex) {
        final Map<String, String> errorMap = new HashMap<>();
        final var bindingResult = ex.getBindingResult();
        final var fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ValidationErrorResponse("Validation failed", errorMap);
    }

}
