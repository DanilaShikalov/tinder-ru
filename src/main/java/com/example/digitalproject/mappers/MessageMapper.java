package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.entities.Message;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MessageMapper {
    List<MessageGetDTO> getAll(List<Message> list);

    Message postToEntity(MessagePostDTO postDTO);
}
