package com.example.springai.service;

import com.example.springai.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(ChatRequest request) {

        String systemPrompt = """
                You are a helpful Math Assistant.
                You only answer math-related questions.
                Adjust your explanation based on the user's level:
                - Beginner: simple words and step-by-step explanation
                - Intermediate: examples and best practices
                - Advanced: deeper concepts and formulas
                """;

        String userPrompt = """
                Level: %s
                Question: %s
                """.formatted(request.getLevel(), request.getQuestion());

        return chatClient.prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .call()
                .content();
    }
}
