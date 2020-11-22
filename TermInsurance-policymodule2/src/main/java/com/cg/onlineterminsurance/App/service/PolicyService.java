package com.cg.onlineterminsurance.App.service;

import java.util.List;
import com.cg.onlineterminsurance.App.entity.Policy;
import com.cg.onlineterminsurance.App.exception.ResourceNotFoundException;

public interface PolicyService {
	public List<Policy> getAllPolicies();
	public Policy findPolicyById(Integer policyid) throws ResourceNotFoundException;
	public boolean deletePolicyById(Integer policyid)throws ResourceNotFoundException;
	public Policy savePolicy(Policy policy);
	public Policy updatePolicy(Integer policyid,Policy policy)throws ResourceNotFoundException;

}
