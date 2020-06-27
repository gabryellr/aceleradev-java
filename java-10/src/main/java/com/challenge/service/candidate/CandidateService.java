package com.challenge.service.candidate;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.entity.Company;
import com.challenge.repository.candidate.CandidateEmbeddedRepository;
import com.challenge.repository.candidate.CandidateRepository;
import com.challenge.service.acceleration.AccelerationService;
import com.challenge.service.company.CompanyService;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements CandidateServiceInterface {

    @Autowired
    private CandidateEmbeddedRepository candidateEmbeddedRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AccelerationService accelerationService;

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return candidateEmbeddedRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        return Optional.empty();
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        return companyService.findById(companyId).map(Company::getCandidates).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        return accelerationService.findById(accelerationId).map(Acceleration::getCandidates)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> findAllByCandidatesId(List<CandidateId> candidateIds) {
        return candidateEmbeddedRepository.findAllById(candidateIds);
    }

    public List<Candidate> findAllById(List<Long> candidates) {
        return candidateRepository.findAllById(candidates);
    }

}
