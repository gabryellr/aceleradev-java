package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("/candidate")
@RestController
@AllArgsConstructor
public class CandidateController {

    private CandidateService candidateService;
    private CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long accelerationId) {

        Optional<Candidate> candidateFound = candidateService.findById(userId, companyId, accelerationId);

        return candidateFound.map(candidate -> ResponseEntity.ok(candidateMapper.map(candidate)))
                .orElseGet(() -> ResponseEntity.status(NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByAccelerationIdOrCompanyId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long companyId) {

        if (nonNull(accelerationId))
            return ResponseEntity.ok(candidateMapper.map(candidateService.findByAccelerationId(accelerationId)));

        if (nonNull(companyId))
            return ResponseEntity.ok(candidateMapper.map(candidateService.findByCompanyId(companyId)));

        return ResponseEntity.status(NOT_FOUND).build();
    }

}