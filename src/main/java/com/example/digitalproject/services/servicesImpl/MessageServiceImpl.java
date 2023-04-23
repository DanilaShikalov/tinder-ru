package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.MessageMapper;
import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.entities.Message;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.MessageRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private MessageMapper messageMapper;
    private PersonRepository personRepository;

    @Override
    public List<MessageGetDTO> getAllMessages() {
        return messageMapper.getAll(messageRepository.findAll());
    }

    @Override
    public void postMessage(MessagePostDTO messagePostDTO, String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        Message message = messageMapper.postToEntity(messagePostDTO);
        message.setPerson(person);
        messageRepository.save(message);
    }
}
