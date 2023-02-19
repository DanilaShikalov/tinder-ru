package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.StudentMapper;
import com.example.digitalproject.models.dto.students.*;
import com.example.digitalproject.models.entities.Student;
import com.example.digitalproject.repositories.DocumentRepository;
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
    private DocumentRepository documentRepository;
    @Override
    public void addDocumentsToStudent(Long idStudent, Long idsDocument) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        student.getDocuments().add(documentRepository.findById(idsDocument).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
        studentRepository.save(student);
    }

    @Override
    public StudentGetDTO getStudent(Long id) {
        return studentMapper.entityToGet(studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postStudent(StudentPostDTO studentPostDTO) {
//        Student student = studentRepository.save(studentMapper.postToEntity(studentPostDTO));
        Student student = studentMapper.postToEntity(studentPostDTO);
        if (studentPostDTO.getDocument_ids() != null) {
            studentPostDTO.getDocument_ids().forEach(x -> student.getDocuments().add(documentRepository.findById(x).orElseThrow(() -> new ResponseStatusException(NOT_FOUND))));
        }
        studentRepository.save(student);
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
