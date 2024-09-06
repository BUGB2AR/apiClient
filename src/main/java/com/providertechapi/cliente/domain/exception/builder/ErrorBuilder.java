package com.providertechapi.cliente.domain.exception.builder;

import com.providertechapi.cliente.domain.exception.error.ErrorApplication;
import com.providertechapi.cliente.domain.exception.severity.Severity;
import org.springframework.http.HttpStatus;

public class ErrorBuilder {

    private static ErrorApplication error;

    public ErrorBuilder() {
        error = new ErrorApplication();
    }

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }

    public ErrorBuilder withStatus(HttpStatus status) {
        error.setStatus(status);
        error.setCode(status.value());
        return this;
    }

    public ErrorBuilder withMessage(String message) {
        error.setMessage(message);
        return this;
    }

    public ErrorBuilder withDetails(Throwable ex) {
        error.setDetails(ex);
        return this;
    }

    public ErrorBuilder withSeverity(Severity severity) {
        error.setSeverity(severity.name());
        return this;
    }

    public ErrorApplication build() {
        return error;
    }

}
