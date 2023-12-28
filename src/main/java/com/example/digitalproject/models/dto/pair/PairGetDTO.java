package com.example.digitalproject.models.dto.pair;

import com.example.digitalproject.models.security.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PairGetDTO {
    private RegisterResponse first;
    private RegisterResponse second;
    private boolean status;
}
