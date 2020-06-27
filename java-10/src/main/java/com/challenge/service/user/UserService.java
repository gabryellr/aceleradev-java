package com.challenge.service.user;

import com.challenge.entity.User;
import com.challenge.repository.user.UserRepository;
import com.challenge.service.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findByAccelerationName(String name) {
        return userRepository.findByAccelerationName(name);
    }

    @Override
    public List<User> findByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }

}
