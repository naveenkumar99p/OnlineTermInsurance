package com.cg.onlineterminsurance.App.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineterminsurance.App.entity.Policy;

@RunWith(SpringRunner.class)
@DataJpaTest

class PolicyDaoImplTest {
	@Autowired
	private PolicyDaoImpl userpolicy;
	@Autowired
	private TestEntityManager testEntityManager;
	@Test
	public void testNewUserPolicy() throws Exception{
		Policy policy=getUserPolicy();
		Policy saveInDb=testEntityManager.persist(policy);
		Policy getFromInDb=userpolicy.findById(saveInDb.getId()).get();
				assertThat(getFromInDb).isEqualTo(saveInDb);
}
	private Policy getUserPolicy() {
		Policy policy=new Policy();
	    policy.setId(122);
	    policy.setDescription("one crore");
	    policy.setYearsOfPayment(2);
        policy.setAmount(12000.00);
        policy.setMaturityperiod(21);
        policy.setMaturityamount(21000.00);
	    return policy;
	}
	@Test
	public void testGetUserPolicysById() throws Exception{
		Policy policy=new Policy();
	    policy.setId(125);
	    policy.setDescription("Two crore");
	    policy.setYearsOfPayment(2);
        policy.setAmount(13000.00);
        policy.setMaturityperiod(21);
        policy.setMaturityamount(22000.00);
        Policy saveInDb=testEntityManager.persist(policy);
        Policy getInDb=userpolicy.findById(policy.getId()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
	}
	@Test
	public void testGetAllUserPolicy() throws Exception{
		Policy policy1=new Policy();
		policy1.setId(125);
	    policy1.setDescription("Three crore");
	    policy1.setYearsOfPayment(4);
        policy1.setAmount(13400.00);
        policy1.setMaturityperiod(25);
        policy1.setMaturityamount(25000.00);
        
        
        Policy policy2=new Policy();
		policy2.setId(126);
	    policy2.setDescription("five crore");
	    policy2.setYearsOfPayment(4);
        policy2.setAmount(15400.00);
        policy2.setMaturityperiod(25);
        policy2.setMaturityamount(25000.00);
        
 	    
        testEntityManager.persist(policy1);
        testEntityManager.persist(policy2);
        List<Policy> policyList=(List<Policy>) userpolicy.findAll();
        Assert.assertEquals(2, policyList.size());
         }
	@Test
	public void testDeleteUserPolicyById() throws Exception{
		
		Policy policy=new Policy();
		policy.setId(125);
	    policy.setDescription("Three crore");
	    policy.setYearsOfPayment(4);
        policy.setAmount(13400.00);
        policy.setMaturityperiod(25);
        policy.setMaturityamount(25000.00);
        
        
       
        
        Policy policy1=new Policy();
		policy1.setId(127);
	    policy1.setDescription("Three crore");
	    policy1.setYearsOfPayment(4);
        policy1.setAmount(13400.00);
        policy1.setMaturityperiod(25);
        policy1.setMaturityamount(25000.00);
        
        Policy policy2=testEntityManager.persist(policy);
        testEntityManager.persist(policy1);
        testEntityManager.remove(policy2);
        List<Policy> policyList=(List<Policy>) userpolicy.findAll();
        Assert.assertEquals(policyList.size(),1);
        
	}
	@Test
	public void testUpdateUsePolicy()
	{
	Policy policy1=new Policy();
	policy1.setId(127);
    policy1.setDescription("Three crore");
    policy1.setYearsOfPayment(4);
    policy1.setAmount(13400.00);
    policy1.setMaturityperiod(25);
    policy1.setMaturityamount(25000.00);
		
        testEntityManager.persist(policy1);
        Policy getFromDb=userpolicy.findById(policy1.getId()).get();
        getFromDb.setAmount(15000.00);
        testEntityManager.persist(getFromDb);
        assertThat(getFromDb.getAmount()).isEqualTo(15000.00);
	}


}
