package com.viveka01.ragtest.template;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelMessage {

    private final String model = "qwen3:4b";

    private String prompt;

    private String system;

    private final boolean stream = false;

    private final boolean think = true;

    @JsonProperty("keep_alive")
    private final int keepAlive = 0;

    @JsonProperty("logprobs")
    private final boolean logProbs = false;

    @JsonCreator
    public ModelMessage(String prompt, String system) {
        this.prompt = prompt;
        this.system = system;
    }

    // getters + setter

    public String getModel() {
        return model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSystem() {
        return system;
    }

    public boolean isStream() {
        return stream;
    }

    public boolean isThink() {
        return think;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public boolean isLogProbs() {
        return logProbs;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}