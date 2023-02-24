package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.persons.*;
import com.example.digitalproject.models.entities.Person;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonGetDTO entityToGet(Person entity);
    Person postToEntity(PersonPostDTO postDTO);
    Person putToEntity(PersonPutDTO putDTO);
    List<PersonGetDTO> getAll(List<Person> list);
}
