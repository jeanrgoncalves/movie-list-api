package com.movielist.movielist.apierror;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CustomException extends RuntimeException {
    private String message;
    private List<String> errors;
}
