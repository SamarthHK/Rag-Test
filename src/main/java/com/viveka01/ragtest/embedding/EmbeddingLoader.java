package com.viveka01.ragtest.embedding;

import com.viveka01.ragtest.template.RequestEmbeddingModel;
import com.viveka01.ragtest.template.ResponseEmbeddingModel;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

@Controller
public class EmbeddingLoader {
    private String message;
    private String[] chunks;
    private RestClient restClient = RestClient.create();
    private static final HashMap<String,double[]> embeddingMap = new HashMap<>();

    @PostConstruct
    public void init(){
        OutputStream write;
        try {
            loadDocument("static/source.txt");
            File file = new File("src/write/output.txt");
            write = new FileOutputStream(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        chunks = message.split("\n\n");
        System.out.println("Chunked everything");
        ResponseEmbeddingModel modelResponse = getEmbeddings(chunks);
        System.out.println("DID IT!");
        for (int i = 0; i != chunks.length; i++){
            embeddingMap.put(chunks[i],modelResponse.getVector(i));
            String chunk = chunks[i]+"\n"+ Arrays.toString(modelResponse.getVector(i)) +"\r\n\r\n";
            try {
                write.write(chunk.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadDocument(String name) throws IOException {
        File file = ResourceUtils.getFile("classpath:"+name);
        try (InputStream read = new FileInputStream(file)) {
            this.message = new String(read.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private ResponseEmbeddingModel getEmbeddings(String[] prompts){
        return restClient.post()
                .uri("http://localhost:11434/api/embed")
                .body(new RequestEmbeddingModel(prompts))
                .retrieve()
                .body(ResponseEmbeddingModel.class);
    }

    public static HashMap<String,double[]> getEmbeddingMap(){
        return embeddingMap;
    }
}
