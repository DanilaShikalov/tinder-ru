package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.TeacherMapper;
import com.example.digitalproject.models.dto.teachers.TeacherGetDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPostDTO;
import com.example.digitalproject.models.dto.teachers.TeacherPutDTO;
import com.example.digitalproject.repositories.TeacherRepository;
import com.example.digitalproject.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService  {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;

    @Override
    public TeacherGetDTO getTeacher(Long id) {
        return teacherMapper.entityToGet(teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postTeacher(TeacherPostDTO teacherPostDTO) {
        teacherRepository.save(teacherMapper.postToEntity(teacherPostDTO));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void putTeacher(TeacherPutDTO teacherPutDTO) {
        teacherRepository.save(teacherMapper.putToEntity(teacherPutDTO));
    }

    @Override
    public List<TeacherGetDTO> getAllTeachers() {
        return teacherMapper.getAllTeachers(teacherRepository.findAll());
    }
}
