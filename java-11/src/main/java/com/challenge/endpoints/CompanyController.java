package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyService.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrado")), OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationIdOrCompanyId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId) {

        if (nonNull(accelerationId)) return ResponseEntity.ok(companyService.findByAccelerationId(accelerationId));
        if (nonNull(userId)) return ResponseEntity.ok(companyService.findByUserId(userId));
        return ResponseEntity.status(NOT_FOUND).build();
    }

}