package com.jobsync.jobysncapi.organization.domain.model.persistence;

import com.jobsync.jobysncapi.organization.domain.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Optional<Company> findByName(String name);
    Boolean existsCompanyByName(String name);
}
