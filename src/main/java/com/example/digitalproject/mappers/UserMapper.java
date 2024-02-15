package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.entities.Message;
import com.example.digitalproject.models.security.RegisterResponse;
import com.example.digitalproject.models.security.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    List<RegisterResponse> getAll(List<User> list);

    User postToEntity(RegisterResponse postDTO);
}
