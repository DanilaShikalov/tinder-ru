package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.MessageMapper;
import com.example.digitalproject.mappers.PairMapper;
import com.example.digitalproject.models.dto.pair.PairPostDTO;
import com.example.digitalproject.models.entities.Pair;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.models.security.User;
import com.example.digitalproject.repositories.MessageRepository;
import com.example.digitalproject.repositories.PairRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.repositories.UserRepository;
import com.example.digitalproject.services.PairService;
import com.example.digitalproject.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class PairServiceImpl implements PairService {
    private PersonRepository personRepository;
    private UserRepository userRepository;
    private PairRepository pairRepository;

    @Override
    public List<Pair> getAllPairs(String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        List<Pair> pairs = pairRepository.findAll().stream().filter(x ->
                (x.getFirst().getEmail().equals(person.getUser().getEmail()) ||
                        x.getSecond().getEmail().equals(person.getUser().getEmail())) &&
                x.isStatus()).toList();
        return pairs;
    }

    @Override
    public void postPair(PairPostDTO pair) {
        User userFirst = userRepository.findByEmail(pair.getEmailFirst()).get();
        User userSecond = userRepository.findByEmail(pair.getEmailSecond()).get();
        pairRepository.save(
                new Pair(null, userFirst, userSecond, pair.isStatus())
        );
    }

    @Override
    public User getRandomPairUser(String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        List<Pair> pairsWithUser = getAllPairs(token);
        List<User> users = userRepository.findAll().stream()
                .filter(x -> !x.getEmail().equals(person.getUser().getEmail()) &&
                        pairsWithUser.stream().noneMatch(pair ->
                                pair.getFirst() == x || pair.getSecond() == x))
                .toList();
        Random random = new Random();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(random.nextInt(users.size()));
    }

    @Override
    public List<Pair> getAllNotMatchPairs(String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        List<Pair> pairs = pairRepository.findAll().stream().filter(x ->
                !x.getFirst().getEmail().equals(person.getUser().getEmail()) &&
                        !x.getSecond().getEmail().equals(person.getUser().getEmail())).toList();
        return pairs;
    }
}
