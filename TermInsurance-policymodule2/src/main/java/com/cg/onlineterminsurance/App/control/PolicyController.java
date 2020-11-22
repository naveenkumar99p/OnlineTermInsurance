package com.cg.onlineterminsurance.App.control;

import java.util.List;


//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineterminsurance.App.entity.Policy;
import com.cg.onlineterminsurance.App.exception.ResourceNotFoundException;
import com.cg.onlineterminsurance.App.service.PolicyService;

@RestController
@RequestMapping("/api/v1")
public class PolicyController {
	@Autowired
	private PolicyService policyService;

	@GetMapping("/policy")
	public List<Policy> getAllPolicy() {
		return  policyService.getAllPolicies();
	}

	@GetMapping("/policy/{id}")
	public ResponseEntity<Policy> getUserPolicyById(@PathVariable(value = "id") Integer policyid) throws ResourceNotFoundException {
		Policy policy= policyService.findPolicyById(policyid);
		return  ResponseEntity.ok(policy);
	}
	@PostMapping("/policy")
	public Policy saveUser( @RequestBody Policy policy1 ) {
		return  policyService.savePolicy(policy1);
	}

	@PutMapping("/policy/{id}")
	public ResponseEntity<Policy> updatePolicyById(@PathVariable(value = "id") Integer policyid,
			 @RequestBody Policy policy) throws ResourceNotFoundException {
		Policy policy1= policyService.updatePolicy(policyid, policy);
		return  ResponseEntity.ok(policy1);
	}
	 @DeleteMapping("/policy/{id}")
	 public ResponseEntity<Boolean> deleteUserPolicy(@PathVariable(value = "id") Integer policyid,@RequestBody Policy policy) throws ResourceNotFoundException	{
			Boolean policy1 = policyService.deletePolicyById(policyid);
			return  ResponseEntity.ok(policy1);
    }

}
