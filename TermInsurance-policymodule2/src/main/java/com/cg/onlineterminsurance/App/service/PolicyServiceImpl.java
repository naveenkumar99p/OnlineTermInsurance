package com.cg.onlineterminsurance.App.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.onlineterminsurance.App.entity.Policy;
import com.cg.onlineterminsurance.App.exception.ResourceNotFoundException;
import com.cg.onlineterminsurance.App.repository.PolicyDaoImpl;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService{

	@Autowired
	private PolicyDaoImpl policy;
	

	public List<Policy> getAllPolicies(){
		return policy.findAll();
	}

	public Policy findPolicyById(@PathVariable(value="id")Integer policyid) throws ResourceNotFoundException {
	
		Policy policy1 =policy.findById(policyid)
				.orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + policyid));
		return policy1;
	}

   /*public List<userPolicy> findByUserIdAndPolicyId(int userId,int policyNo) {
        return (List<userPolicy>) userpolicy.findByUserIdAndPolicyId(userId, policyNo);
    }*/
    public boolean deletePolicyById(Integer policyid) throws ResourceNotFoundException {
    	Policy policy1 = policy.findById(policyid)
				.orElseThrow(() -> new ResourceNotFoundException("Policy ot found for this id :: " + policyid));
    	 policy.delete(policy1);
    	 if(null == policy1){
	            return true;
	        }
	        return false;
    }
			

	public Policy savePolicy(Policy policy1) {
		
		return policy.save(policy1);
	}

	public Policy updatePolicy(Integer policyid,Policy policies) throws ResourceNotFoundException {
	  Policy policy1 = policy.findById(policyid)
				.orElseThrow(() -> new ResourceNotFoundException("Policy ot found for this id :: " + policyid));
	  policy1.setId(policies.getId());
		policy1.setDescription(policies.getDescription());
		policy1.setYearsOfPayment(policies.getYearsOfPayment());
		policy1.setAmount(policies.getAmount());
		policy1.setMaturityperiod(policies.getMaturityperiod());
		policy1.setMaturityamount(policies.getMaturityamount());
		final Policy updatedPolicy =policy.save(policy1);
		return updatedPolicy;
	}
	

	public List<Policy> saveAll() {
		// TODO Auto-generated method stub
		return null;
	}

}


