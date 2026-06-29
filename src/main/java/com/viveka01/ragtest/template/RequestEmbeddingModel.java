package com.viveka01.ragtest.template;

public class RequestEmbeddingModel {
    private final String model = "qwen3-embedding:0.6b";
    private String[] input;
    private final int dimensions = 1024;

    public RequestEmbeddingModel(){}

    public RequestEmbeddingModel(String[] input){this.input = input;}

    public String getModel() {
        return model;
    }

    public String[] getInput() {
        return input;
    }

    public void setInput(String[] input) {
        this.input = input;
    }

    public int getDimensions() {
        return dimensions;
    }
}
