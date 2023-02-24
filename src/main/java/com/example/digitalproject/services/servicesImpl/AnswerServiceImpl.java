package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.AnswerMapper;
import com.example.digitalproject.models.dto.answers.AnswerGetDTO;
import com.example.digitalproject.models.dto.answers.AnswerPostDTO;
import com.example.digitalproject.models.dto.answers.AnswerPutDTO;
import com.example.digitalproject.repositories.AnswerRepository;
import com.example.digitalproject.services.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;

    @Override
    public AnswerGetDTO getEntity(Long id) {
        return answerMapper.entityToGet(answerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "answer не найден")));
    }

    @Override
    public void postEntity(AnswerPostDTO AnswerPostDTO) {
        answerRepository.save(answerMapper.postToEntity(AnswerPostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public void putEntity(AnswerPutDTO AnswerPutDTO) {
        answerRepository.save(answerMapper.putToEntity(AnswerPutDTO));
    }

    @Override
    public List<AnswerGetDTO> getAllEntities() {
        return answerMapper.getAll(answerRepository.findAll());
    }
}
