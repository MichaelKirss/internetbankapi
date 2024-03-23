package org.skill.internetbankapi.repository;

import org.skill.internetbankapi.entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends JpaRepository<Operations,Long>{
}
