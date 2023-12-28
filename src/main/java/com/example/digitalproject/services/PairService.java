package com.example.digitalproject.services;

import com.example.digitalproject.models.dto.pair.PairPostDTO;
import com.example.digitalproject.models.entities.Pair;
import com.example.digitalproject.models.security.User;

import java.util.List;

public interface PairService {
    List<Pair> getAllPairs(String token);

    void postPair(PairPostDTO pair);

    User getRandomPairUser(String token);

    List<Pair> getAllNotMatchPairs(String token);
}
