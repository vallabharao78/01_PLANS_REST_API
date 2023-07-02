package com.vallabha.repo;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vallabha.entity.PlanCategory;

public interface PlanCategoryRepository extends JpaRepository<PlanCategory,Serializable>
{

}
