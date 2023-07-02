package com.vallabha.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vallabha.entity.Plan;
import com.vallabha.entity.PlanCategory;
import com.vallabha.repo.PlanCategoryRepository;
import com.vallabha.repo.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService
{
	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private PlanCategoryRepository planCategoryRepo;
	
	@Override
	public Map<Integer, String> getPlanCategories() {
		//Here we are getting List of items
		List<PlanCategory> categories = planCategoryRepo.findAll(); 
		
		//Creating a Map
		Map<Integer,String> categoriesMap = new HashMap<>();
		
		//Converting List into Map by iterating each list item in the collection
		categories.forEach(category -> {
		     categoriesMap.put(category.getCategoryId(),category.getCategoryName());    
		});
		return categoriesMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan planObj = planRepo.save(plan);
		/*  if(planObj.getPlanId() != null) 
		       return true; 
		    else 
		       return false; */
		//using Ternary Operator
		return planObj.getPlanId() != null ? true : false ;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> plans = planRepo.findAll();
		return plans;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> plan = planRepo.findById(planId);
		if(plan.isPresent())
			return plan.get();
		else
			return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan planObj = planRepo.save(plan);
		if(planObj.getPlanId() != null)
		{
			return true;
		}
		return false;
		// return planObj.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String activeSw) {
		Optional<Plan> planObj = planRepo.findById(planId);
		if(planObj.isPresent())
		{
			Plan plan = planObj.get();
			plan.setPlanId(planId);
			plan.setActiveSw(activeSw);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
