package com.challenge.service.submission;

import com.challenge.entity.Submission;
import com.challenge.repository.submission.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class SubmissionService implements SubmissionServiceInterface {

    private SubmissionRepository submissionRepository;

    @Override
    public Submission save(Submission object) {
        return submissionRepository.save(object);
    }

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        BigDecimal higherScore = this.submissionRepository.findHigherScoreByChallengeId(challengeId);
        if (isNull(higherScore)) {
            return ZERO;
        }

        return higherScore;
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return submissionRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

}
