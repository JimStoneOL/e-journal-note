package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimationRepository extends JpaRepository<Estimation, Long>{
}
