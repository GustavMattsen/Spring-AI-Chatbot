package com.example.springai.controller;

import com.example.springai.dto.ChatRequest;
import com.example.springai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String chat(@Valid @RequestBody ChatRequest request) {
        return chatService.ask(request);
    }
}
