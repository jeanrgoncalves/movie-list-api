package com.movielist.movielist.util;

import java.text.Normalizer;

public class StringUtil {

    public static String upperCaseAndRemoveAccents(String text) {
       return Normalizer.normalize(text, Normalizer.Form.NFD)
               .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
               .toUpperCase();
    }

}
