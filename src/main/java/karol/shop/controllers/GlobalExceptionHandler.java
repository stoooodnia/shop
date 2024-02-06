package karol.shop.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        // Pobierz listę błędów walidacji
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        // Zbuduj komunikat zawierający treść błędów
        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            errorMessage.append(violation.getMessage()).append("; ");
        }

        // Zwróć komunikat błędu walidacji
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

}
