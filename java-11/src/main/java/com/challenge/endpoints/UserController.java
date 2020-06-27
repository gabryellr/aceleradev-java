package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrCompanyId(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId) {

        if (hasText(accelerationName)) return ResponseEntity.ok(userService.findByAccelerationName(accelerationName));
        if (nonNull(companyId)) return ResponseEntity.ok(userService.findByCompanyId(companyId));

        return ResponseEntity.status(NOT_FOUND).build();
    }

}