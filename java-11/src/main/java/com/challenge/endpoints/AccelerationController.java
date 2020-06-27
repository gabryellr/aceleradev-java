package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.entity.User;
import com.challenge.service.impl.AccelerationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("acceleration")
@AllArgsConstructor
public class AccelerationController {

    private AccelerationService accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accelerationService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return new ResponseEntity<>(accelerationService.findByCompanyId(companyId), HttpStatus.OK);
    }

}
