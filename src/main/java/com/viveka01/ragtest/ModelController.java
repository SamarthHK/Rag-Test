package com.viveka01.ragtest;

import com.viveka01.ragtest.template.ModelMessage;
import com.viveka01.ragtest.template.ModelResponse;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

@Service
public class ModelController {
    private HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    private RestClient client = RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory(httpClient))
            .build();

    public String talkToModel(String message, String system) {
        ModelResponse response = client.post()
                .uri("http://localhost:11434/api/generate")
                .body(new ModelMessage(message,system+"\nDO NOT TALK ABOUT CONTEXT AT ALL, ANSWER THE PROMPT WITH THIS CONTEXT MAKE IT SOUND SEEMLESS LIKE YOU ARE ANSWERING FROM YOUR KNOWLEDGE, DONT MAKE IT SOUND LIKE ANYTHING WAS PROVIDED"))
                .retrieve()
                .body(ModelResponse.class);

        return response != null ? response.getResponse() : "NULL RESPONSE";
    }

}
