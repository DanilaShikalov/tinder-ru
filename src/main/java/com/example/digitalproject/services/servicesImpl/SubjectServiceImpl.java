package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.SubjectMapper;
import com.example.digitalproject.models.dto.subjects.*;
import com.example.digitalproject.models.entities.Subject;
import com.example.digitalproject.repositories.SubjectRepository;
import com.example.digitalproject.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;

    @Override
    public SubjectGetDTO getEntity(Long id) {
        return subjectMapper.entityToGet(subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(SubjectPostDTO subjectPostDTO) {
        subjectRepository.save(subjectMapper.postToEntity(subjectPostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void putEntity(SubjectPutDTO subjectPutDTO, Long id) {
        Subject subject = subjectMapper.putToEntity(subjectPutDTO);
        subject.setId(id);
        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectGetDTO> getAllEntities() {
        return subjectMapper.getAll(subjectRepository.findAll());
    }

    @Override
    public List<SubjectWithTasksGetDTO> getAllSubjectsWithTasks() {
        return subjectMapper.getAllSubjectsWithTasks(subjectRepository.findAll());
    }
}
