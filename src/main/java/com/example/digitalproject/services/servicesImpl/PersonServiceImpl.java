package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.PersonMapper;
import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.*;
import com.example.digitalproject.services.AuthenticationService;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private PersonMapper personMapper;
    private JobRepository jobRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;

    @Override
    public PersonGetDTO getPerson(String token) {
        List<Person> list = personRepository.getPersonByToken(token);
        return personMapper.entityToGet(list.get(0));
    }

    @Override
    public void putPersonEmail(String email, String token) {
        User user = authenticationService.getUserByToken(token);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void putPersonPassword(String password, String token) {
        User user = authenticationService.getUserByToken(token);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void putPersonPhone(String phone, String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        person.setPhone(phone);
        person.setNumber(phone);
        personRepository.save(person);
    }

    @Override
    public PersonFullInfoGetDTO getFullInfo(String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        PersonFullInfoGetDTO personFullInfoGetDTO = PersonFullInfoGetDTO.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .phone(person.getPhone())
                .email(person.getUser().getEmail())
                .salary(person.getJob().getSalary())
                .position(person.getJob().getTitle())
                .rating("4.7")
                .build();
        return personFullInfoGetDTO;
    }

    @Override
    public PersonGetDTO getEntity(Long id) {
        return personMapper.entityToGet(personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(PersonPostDTO personPostDTO, Long idUser) {
        Person person = personMapper.postToEntity(personPostDTO);
        person.setUser(userRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Not found user")));
        personRepository.save(person);
    }

    @Override
    public void deleteEntity(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void putEntity(PersonPutDTO personPutDTO, Long id) {
        Person person = personMapper.putToEntity(personPutDTO);
        person.setId(id);
        personRepository.save(person);
    }

    @Override
    public List<PersonGetDTO> getAllEntities() {
        return personMapper.getAll(personRepository.findAll());
    }
}
