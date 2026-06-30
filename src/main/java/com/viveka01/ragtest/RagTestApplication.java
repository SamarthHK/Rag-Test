package com.viveka01.ragtest;

import com.viveka01.ragtest.embedding.CompareEmbedding;
import com.viveka01.ragtest.embedding.EmbeddingLoader;
import com.viveka01.ragtest.embedding.SearchResult;
import com.viveka01.ragtest.template.RequestEmbeddingModel;
import com.viveka01.ragtest.template.ResponseEmbeddingModel;
import com.viveka01.ragtest.template.RequestPrompt;
import com.viveka01.ragtest.template.ResponsePrompt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class RagTestApplication {
    private final RestClient restClient = RestClient.create();
    private final CompareEmbedding compare = new CompareEmbedding(EmbeddingLoader.getEmbeddingMap());

    public static void main(String[] args) {
        SpringApplication.run(RagTestApplication.class, args);
    }

    private final ModelController modelService;

    public RagTestApplication(ModelController modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/embed")
    public ResponsePrompt embed(@RequestBody RequestPrompt request) {
        double[] promptEmbedding = getEmbedding(request.getPrompt());
        ArrayList<SearchResult> result =  compare.compare(promptEmbedding);
        String output = "";
        for (int i =0; i != 10; i++){
            output = output + result.get(i).chunk();
        }
//        System.out.println("Talking to model...");
//        System.out.println("Prompt: "+request.getPrompt()+"\nContext:"+output);
        output = modelService.talkToModel(request.getPrompt(),output);
        return new ResponsePrompt(output);
    }

    public double[] getEmbedding(String input){
        return restClient.post()
                        .uri("http://localhost:11434/api/embed")
                        .body(new RequestEmbeddingModel(new String[]{input}))
                        .retrieve()
                        .body(ResponseEmbeddingModel.class)
                        .getVector(0);
    }
}
