package com.movielist.movielist.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
@Slf4j
public class Translator {

    private Locale locale = Locale.getDefault();
    ResourceBundle resourceMessages = ResourceBundle.getBundle("i18n.messages", locale);

    public String getText(String messageCode, String... parameters) {
        log.info(locale.toString());
        log.info(locale.getLanguage());
         String text = resourceMessages.getString(messageCode);
        return MessageFormat.format(text, parameters);
    }

}
