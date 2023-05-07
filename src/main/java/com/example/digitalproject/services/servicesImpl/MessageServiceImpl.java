package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.MessageMapper;
import com.example.digitalproject.models.dto.message.MessageGetDTO;
import com.example.digitalproject.models.dto.message.MessagePostDTO;
import com.example.digitalproject.models.entities.Message;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.MessageRepository;
import com.example.digitalproject.repositories.PersonRepository;
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
    private PersonService personService;

    @Override
    public List<MessageGetDTO> getAllMessages(String token) {
        List<Message> messageList = messageRepository.findAll();
        List<MessageGetDTO> messageMapperAll = messageMapper.getAll(messageList);
        messageMapperAll.forEach(x -> x.setStatusMember(false));
        Person person = personRepository.getPersonByToken(token).get(0);
        User user = person.getUser();
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).getPerson().getUser().equals(user)) {
                messageMapperAll.get(i).setStatusMember(true);
            }
        }
        return messageMapperAll;
    }

    @Override
    public void postMessage(MessagePostDTO messagePostDTO, String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        Message message = messageMapper.postToEntity(messagePostDTO);
        message.setPerson(person);
        messageRepository.save(message);
    }
}
