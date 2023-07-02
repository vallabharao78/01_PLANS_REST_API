package com.vallabha.repo;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vallabha.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Serializable> {

}
