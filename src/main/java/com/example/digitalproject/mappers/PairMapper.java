package com.example.digitalproject.mappers;

import com.example.digitalproject.models.dto.pair.PairGetDTO;
import com.example.digitalproject.models.entities.Pair;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PairMapper {
    List<PairGetDTO> getAll(List<Pair> list);
}
