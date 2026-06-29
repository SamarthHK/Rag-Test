package com.viveka01.ragtest.template;

import java.util.ArrayList;
import java.util.List;

public class ResponseEmbeddingModel {

    private String model;
    private List<List<Double>> embeddings;
    private long total_duration;
    private long load_duration;
    private int prompt_eval_count;

    public ResponseEmbeddingModel() {}

    public ResponseEmbeddingModel(String model, List<List<Double>> embeddings, long total_duration, long load_duration, int prompt_eval_count) {
        this.model = model;
        this.embeddings = embeddings;
        this.total_duration = total_duration;
        this.load_duration = load_duration;
        this.prompt_eval_count = prompt_eval_count;
    }

    public List<List<Double>> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(List<List<Double>> embeddings) {
        this.embeddings = embeddings;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTotal_duration() {
        return total_duration;
    }

    public void setTotal_duration(long total_duration) {
        this.total_duration = total_duration;
    }

    public long getLoad_duration() {
        return load_duration;
    }

    public void setLoad_duration(long load_duration) {
        this.load_duration = load_duration;
    }

    public int getPrompt_eval_count() {
        return prompt_eval_count;
    }

    public void setPrompt_eval_count(int prompt_eval_count) {
        this.prompt_eval_count = prompt_eval_count;
    }

    public double[] getVector(int index) {
        List<Double> vectorList = embeddings.get(index);

        double[] vector = new double[vectorList.size()];

        for (int i = 0; i < vectorList.size(); i++) {
            vector[i] = vectorList.get(i);
        }

        return vector;
    }
}