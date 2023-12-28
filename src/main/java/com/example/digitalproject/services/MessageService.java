package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;

import java.util.List;

public interface MessageService {
    List<MessageGetDTO> getAllMessages(String token, String email2);

    void postMessage(MessagePostDTO messagePostDTO);
}
