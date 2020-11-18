package com.movielist.movielist.util;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class Translator {

    private Locale locale = Locale.getDefault();
    ResourceBundle resourceMessages = ResourceBundle.getBundle("i18n.messages", locale);

    public String getText(String messageCode, String... parameters) {
         String text = resourceMessages.getString(messageCode);
        return MessageFormat.format(text, parameters);
    }

}
