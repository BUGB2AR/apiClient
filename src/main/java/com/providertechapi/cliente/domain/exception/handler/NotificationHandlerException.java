package com.providertechapi.cliente.domain.exception.handler;

import com.providertechapi.cliente.domain.exception.builder.ErrorBuilder;
import com.providertechapi.cliente.domain.exception.error.ErrorApplication;
import com.providertechapi.cliente.domain.exception.notification.NotificationException;
import com.providertechapi.cliente.domain.exception.notification.NotificationNotFoundException;
import com.providertechapi.cliente.domain.exception.notification.NotificationViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;

@RestControllerAdvice
public class NotificationHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotificationException.class})
    public ResponseEntity<List<ErrorApplication>> handleNotificationException(NotificationException ex) {
        List<ErrorApplication> errors = List.of((ErrorBuilder.builder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .withSeverity(ex.getSeverity())
                .build()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({NotificationNotFoundException.class})
    public ResponseEntity<List<ErrorApplication>> handleNotificationNotFoundException(NotificationNotFoundException ex) {
        List<ErrorApplication> errors = List.of((ErrorBuilder.builder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withMessage(ex.getMessage())
                .withSeverity(ex.getSeverity())
                .withDetails(ex)
                .build()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotificationViolationException.class)
    public ResponseEntity<ErrorApplication> handleNotificationViolationException(NotificationViolationException ex) {
        ErrorApplication error = ErrorBuilder.builder()
                .withStatus(HttpStatus.CONFLICT)
                .withMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}