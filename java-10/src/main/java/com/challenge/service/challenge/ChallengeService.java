package com.challenge.service.challenge;

import com.challenge.entity.Challenge;
import com.challenge.repository.challenge.ChallengeRepository;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChallengeService implements ChallengeServiceInterface {

    private ChallengeRepository challengeRepository;

    @Override
    public Challenge save(Challenge object) {
        return challengeRepository.save(object);
    }

    @Override
    public List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId) {
        return challengeRepository.findByAccelerationIdAndUserId(accelerationId, userId);
    }

}
