package com.challenge.repository.company;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query("SELECT DISTINCT c.id.company FROM Candidate c WHERE c.id.acceleration.id = :accelerationId")
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("SELECT c.id.company FROM Candidate c WHERE c.id.user.id = :userId")
    List<Company> findByUserId(@Param("userId") Long userId);
}