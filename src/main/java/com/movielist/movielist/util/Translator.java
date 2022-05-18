package com.movielist.movielist.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Component
@Slf4j
public class Translator {

    ResourceBundle resourceMessages = ResourceBundle.getBundle("i18n.messages");

    public String getText(String messageCode, String... parameters) {
        String text = resourceMessages.getString(messageCode);
        return MessageFormat.format(text, parameters);
    }

}
