package com.example.digitalproject.models.dto.pair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PairPostDTO {
    private String emailFirst;
    private String emailSecond;
    private boolean status;
}
