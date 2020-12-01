package com.movielist.movielist.apierror;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@Setter
public class ApiError {

    private String httpStatus;
    private String message;
    private List<String> errors;

    public ApiError(CustomException e) {
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.toString();
        message = e.getMessage();
        errors = e.getErrors();
    }

    public ApiError(String httpStatus, String message, List<String> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }
}
