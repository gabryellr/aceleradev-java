package com.challenge.repository.submission;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {

    @Query("SELECT MAX(s.score) FROM Submission s WHERE s.id.challenge.id = :challengeId ")
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query("SELECT s, a FROM Submission s, Acceleration a WHERE s.id.challenge.id = :challengeId AND a.id = :accelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);

}
