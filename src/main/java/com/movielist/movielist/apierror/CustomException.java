package com.movielist.movielist.apierror;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private String message;
    private List<String> errors;

    public CustomException(String mainMsg, List<String> errors) {
        if (errors != null) {
            if (errors.size() > 1)
                message = mainMsg;
            else
                message = errors.get(0);
        } else {
            message = mainMsg;
        }

        this.errors = errors;
    }
}
