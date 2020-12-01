package com.movielist.movielist.util;

import org.springframework.web.util.UriTemplate;

import java.util.Map;

public class NetworkUtil {

    public static String encodeUrlParams(String urlWithParams, Map<String, String> paramsValues)  {
        return new UriTemplate(urlWithParams)
                .expand(paramsValues)
                .toString();
    }

}
