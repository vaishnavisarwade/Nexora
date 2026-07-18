package com.vaishnavi.nexora.ai.dto;

public record AIResponse(
        boolean success,
        String message,
        String data
) {
}