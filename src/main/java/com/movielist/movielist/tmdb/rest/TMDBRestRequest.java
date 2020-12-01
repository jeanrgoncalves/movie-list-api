package com.movielist.movielist.tmdb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.movielist.movielist.apierror.CustomException;
import com.movielist.movielist.util.NetworkUtil;
import com.movielist.movielist.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
public class TMDBRestRequest {

    @Autowired
    private Translator translator;

    public <T extends TMDBAbstractResponse> T request(String url, String params, Map<String, String> paramsValues,
                                                    HttpMethod method, Object body,  Class<T> returnClass) throws CustomException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TMDBRestApiConfiguration.AUTH_TOKEN);

        HttpEntity httpEntity = new HttpEntity<>(body, headers);

        try {
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

            if (!Strings.isNullOrEmpty(params)) {
                url = NetworkUtil.encodeUrlParams(url.concat(params), paramsValues);
            }

            url = TMDBRestApiConfiguration.BASE_URL
                    .concat("/").concat(url);

            ResponseEntity<T> response;
            response = restTemplate.exchange(url,
                    method,
                    httpEntity,
                    returnClass);

            T returnObj = response.getBody();
            if (returnObj == null) {
                String errorMsg = translator.getText("nullReturn", "TMDB", response.getStatusCode().toString());
                throw new CustomException(errorMsg, null);
            }

            return returnObj;
        }
        catch (HttpStatusCodeException e) {
            String errorMsg = e.getMessage();

            String responseBody = e.getResponseBodyAsString();
            if (!Strings.isNullOrEmpty(responseBody)) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    TMDBRestRequestError error = mapper.readValue(responseBody, TMDBRestRequestError.class);
                    errorMsg = translator.getText("tmdbError", error.getStatus_code().toString(), error.getStatus_message());
                } catch (IOException ex) {
                    errorMsg = translator.getText("tmdbParseErrorFail", ex.getMessage());
                }
            }

            throw new CustomException(errorMsg, null);
        }  catch (Exception e) {
            throw new CustomException(translator.getText("requisitionFail", "TMDB", e.getMessage()), null);
        }
    }

}
