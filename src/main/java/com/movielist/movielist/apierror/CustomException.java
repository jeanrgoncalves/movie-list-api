package com.movielist.movielist.apierror;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
@Getter
@Setter
public class CustomException extends RuntimeException {
    private String message;
    private List<String> errors;

    public CustomException(@Nullable String mainMsg, List<String> errors) {
        if (errors.size() > 1)
            message = mainMsg;
        else
            message = errors.get(0);

        this.errors = errors;
    }
}
