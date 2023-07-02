package com.vallabha.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vallabha.entity.Plan;
import com.vallabha.service.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> getPlanCategories()
	{
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	@PutMapping("/saveplan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan)
	{
		boolean isSaved = planService.savePlan(plan);
		
		String msg = "";
		if(isSaved) {
			msg = " Plan Saved Successfully !!...";
		}else {
			msg = " Plan Not Saved...";
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> viewPlans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdated = planService.updatePlan(plan);
		String msg = "";
		if(isUpdated) {
			msg = " Plan Updated Successfully !!...";
		}else {
			msg = " Plan Not Updated...";
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = "";
		if(isDeleted) {
			msg = " Plan Deleted Successfully !!...";
		}else {
			msg = " Plan Not Deleted...";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId, @PathVariable String status){
		boolean isPlanStatusChanged = planService.planStatusChange(planId, status);
		String msg = "";
		if(isPlanStatusChanged) {
			msg = " Status Changed Successfully...";
		}else {
			msg = " Status Not Changed";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
