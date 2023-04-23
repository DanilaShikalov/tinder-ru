package com.example.digitalproject.services.servicesImpl;

import com.example.digitalproject.mappers.JobMapper;
import com.example.digitalproject.models.dto.jobs.*;
import com.example.digitalproject.models.entities.Job;
import com.example.digitalproject.models.entities.Person;
import com.example.digitalproject.repositories.JobRepository;
import com.example.digitalproject.repositories.PersonRepository;
import com.example.digitalproject.services.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private PersonRepository personRepository;

    @Override
    public JobGetDTO getEntity(Long id) {
        return jobMapper.entityToGet(jobRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @Override
    public void postEntity(JobPostDTO jobPostDTO) {
        jobRepository.save(jobMapper.postToEntity(jobPostDTO));
    }

    @Override
    public void deleteEntity(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void putJobPerson(String job, String token) {
        Person person = personRepository.getPersonByToken(token).get(0);
        Optional<Job> jobOptional = jobRepository.findAll().stream().filter(x -> x.getTitle().equals(job)).findFirst();
        if (jobOptional.isPresent()) {
            Job jobEntity = jobOptional.get();
            person.setJob(jobEntity);
            personRepository.save(person);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Job not found");
        }
    }

    @Override
    public void putEntity(JobPutDTO jobPutDTO, Long id) {
        Job job = jobMapper.putToEntity(jobPutDTO);
        job.setId(id);
        jobRepository.save(job);
    }

    @Override
    public List<JobGetDTO> getAllEntities() {
        return jobMapper.getAll(jobRepository.findAll());
    }
}
