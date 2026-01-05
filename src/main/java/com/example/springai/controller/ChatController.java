package com.example.springai.controller;

import com.example.springai.dto.ChatRequest;
import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public String chat(@Valid @RequestBody ChatRequest request) {

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

