package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.StudentMapper;
import com.example.digitalproject.models.dto.students.*;
import com.example.digitalproject.repositories.StudentRepository;
import com.example.digitalproject.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @Override
    public StudentGetDTO getStudent(Long id) {
        return studentMapper.entityToGet(studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postStudent(StudentPostDTO studentPostDTO) {
        studentRepository.save(studentMapper.postToEntity(studentPostDTO));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void putStudent(StudentPutDTO studentPutDTO) {
        studentRepository.save(studentMapper.putToEntity(studentPutDTO));
    }

    @Override
    public List<StudentGetDTO> getAllStudents() {
        return studentMapper.getAllStudents(studentRepository.findAll());
    }
}
