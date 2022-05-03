package com.example.clientservice.repository;

import com.example.clientservice.entity.Human;
import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HumanRepository extends JpaRepository<Human, Long> {
    Optional<Human> findByStatusIsNotAndId(ClientStatus status, Long id);
    List<Human> findByUserTypeEquals(UserType userType);
    List<Human> findByUserTypeEqualsAndStatusIsNot(UserType userType, ClientStatus status);
}