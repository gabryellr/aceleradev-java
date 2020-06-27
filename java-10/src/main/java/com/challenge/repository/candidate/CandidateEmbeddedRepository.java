package com.challenge.repository.candidate;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateEmbeddedRepository extends JpaRepository<Candidate, CandidateId> {

}
