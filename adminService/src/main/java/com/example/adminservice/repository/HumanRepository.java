package com.example.adminservice.repository;

import com.example.adminservice.entity.Human;
import com.example.adminservice.entity.enums.ClientStatus;
import com.example.adminservice.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HumanRepository extends JpaRepository<Human, Long> {
    Optional<Human> findByStatusIsNotAndId(ClientStatus status, Long id);
    List<Human> findByUserTypeEquals(UserType userType);
    List<Human> findByUserTypeEqualsAndStatusIsNot(UserType userType, ClientStatus status);
}