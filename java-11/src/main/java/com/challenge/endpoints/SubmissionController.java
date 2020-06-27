package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("submission")
@AllArgsConstructor
public class SubmissionController {

    private SubmissionService submissionService;
    private SubmissionMapper submissionMapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findById(@RequestParam("challengeId") Long challengeId,
                                                        @RequestParam("accelerationId") Long accelerationId) {
        return new ResponseEntity<>(submissionMapper.map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)), OK);
    }

}
