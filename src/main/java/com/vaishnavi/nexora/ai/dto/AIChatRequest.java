package com.vaishnavi.nexora.ai.dto;

import jakarta.validation.constraints.NotBlank;

public class AIChatRequest {

    @NotBlank(message = "Message cannot be empty")
    private String message;

    public AIChatRequest() {
    }

    public AIChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}