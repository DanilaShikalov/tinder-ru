package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;

import java.util.List;

public interface MessageService {
    List<MessageGetDTO> getAllMessages();

    void postMessage(MessagePostDTO messagePostDTO, String token);
}
