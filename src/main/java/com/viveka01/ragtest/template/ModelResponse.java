package com.viveka01.ragtest.template;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelResponse {

    private String model;

    @JsonProperty("created_at")
    private String createdAt;

    private String response;

    private String thinking;

    private boolean done;

    @JsonProperty("done_reason")
    private String doneReason;

    @JsonProperty("total_duration")
    private long totalDuration;

    @JsonProperty("load_duration")
    private long loadDuration;

    @JsonProperty("prompt_eval_count")
    private long promptEvalCount;

    @JsonProperty("prompt_eval_duration")
    private long promptEvalDuration;

    @JsonProperty("eval_count")
    private long evalCount;

    @JsonProperty("eval_duration")
    private long evalDuration;

    public ModelResponse(){}

    public ModelResponse(
            String model,
            String createdAt,
            String response,
            String thinking,
            boolean done,
            String doneReason,
            long totalDuration,
            long loadDuration,
            long promptEvalCount,
            long promptEvalDuration,
            long evalCount,
            long evalDuration
    ) {
        this.model = model;
        this.createdAt = createdAt;
        this.response = response;
        this.thinking = thinking;
        this.done = done;
        this.doneReason = doneReason;
        this.totalDuration = totalDuration;
        this.loadDuration = loadDuration;
        this.promptEvalCount = promptEvalCount;
        this.promptEvalDuration = promptEvalDuration;
        this.evalCount = evalCount;
        this.evalDuration = evalDuration;
    }

    public String getModel() {
        return model;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getResponse() {
        return response;
    }

    public String getThinking() {
        return thinking;
    }

    public boolean isDone() {
        return done;
    }

    public String getDoneReason() {
        return doneReason;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public long getLoadDuration() {
        return loadDuration;
    }

    public long getPromptEvalCount() {
        return promptEvalCount;
    }

    public long getPromptEvalDuration() {
        return promptEvalDuration;
    }

    public long getEvalCount() {
        return evalCount;
    }

    public long getEvalDuration() {
        return evalDuration;
    }
}