package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.MessageMapper;
import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.entities.Message;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.MessageRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.UserRepository;
import com.example.digitalproject.services.MessageService;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private MessageMapper messageMapper;
    private PersonRepository personRepository;
    private UserRepository userRepository;

    @Override
    public List<MessageGetDTO> getAllMessages(String token, String email) {
        List<Message> messageList = messageRepository.findAll();
        List<MessageGetDTO> messageMapperAll = messageMapper.getAll(messageList);
        messageMapperAll.forEach(x -> x.setStatusMember(false));
        Person person = personRepository.getPersonByToken(token).get(0);
        var emailFromToken = person.getUser().getEmail();
        messageMapperAll = messageMapperAll.stream().filter(x ->
                (x.getUserFrom().getEmail().equals(emailFromToken) && x.getUserTo().getEmail().equals(email)) ||
                        (x.getUserTo().getEmail().equals(emailFromToken) && x.getUserFrom().getEmail().equals(email))).toList();
        return messageMapperAll;
    }

    @Override
    public void postMessage(MessagePostDTO messagePostDTO) {
        var userTo = userRepository.findByEmail(messagePostDTO.getEmailTo());
        var userFrom = userRepository.findByEmail(messagePostDTO.getEmailFrom());
        Message message = messageMapper.postToEntity(messagePostDTO);
        message.setUserFrom(userFrom.get());
        message.setUserTo(userTo.get());
        messageRepository.save(message);
    }
}
