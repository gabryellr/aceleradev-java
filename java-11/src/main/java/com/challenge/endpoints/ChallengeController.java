package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("challenge")
@AllArgsConstructor
public class ChallengeController {

    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@RequestParam("accelerationId") Long accelerationId,
                                                                         @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(challengeService.findByAccelerationIdAndUserId(accelerationId, userId), OK);
    }

}
