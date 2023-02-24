package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.GradeMapper;
import com.example.digitalproject.models.dto.grades.GradeGetDTO;
import com.example.digitalproject.models.dto.grades.GradePostDTO;
import com.example.digitalproject.models.dto.grades.GradePutDTO;
import com.example.digitalproject.repositories.GradeRepository;
import com.example.digitalproject.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private GradeRepository gradeRepository;
    private GradeMapper gradeMapper;

    @Override
    public GradeGetDTO getEntity(Long id) {
        return gradeMapper.entityToGet(gradeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(GradePostDTO gradePostDTO) {
        gradeRepository.save(gradeMapper.postToEntity(gradePostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public void putEntity(GradePutDTO gradePutDTO) {
        gradeRepository.save(gradeMapper.putToEntity(gradePutDTO));
    }

    @Override
    public List<GradeGetDTO> getAllEntities() {
        return gradeMapper.getAll(gradeRepository.findAll());
    }
}
