package org.skill.internetbankapi.repository;

import org.skill.internetbankapi.entity.TypeOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOperationsRepository extends JpaRepository <TypeOperations, Long> {
}
