package com.challenge.service.acceleration;

import com.challenge.entity.Acceleration;
import com.challenge.repository.acceleration.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccelerationService implements AccelerationServiceInterface {

    private AccelerationRepository accelerationRepository;

    @Override
    public Acceleration save(Acceleration object) {
        return accelerationRepository.save(object);
    }

    @Override
    public Optional<Acceleration> findById(Long id) {
        return accelerationRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return accelerationRepository.findByCompanyId(companyId);
    }

}
