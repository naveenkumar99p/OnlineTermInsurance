package com.cg.onlineterminsurance.App.control;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.onlineterminsurance.App.entity.Policy;
import com.cg.onlineterminsurance.App.entity.Policy;
import com.cg.onlineterminsurance.App.service.PolicyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PolicyController.class)
public class PolicyControllerTest {
	 @Autowired
	    private MockMvc mockMvc;
	 @MockBean
	    private PolicyService userpolicyService;

	 @Test
		public void testCreatePolicy() throws Exception{
		        String URI = "/api/v1/policy";
		        Policy policy=new Policy();
			    policy.setId(122);
			    policy.setDescription("one crore");
			    policy.setYearsOfPayment(2);
		        policy.setAmount(12000.00);
		        policy.setMaturityperiod(21);
		        policy.setMaturityamount(21000.00);

		        String jsonInput = this.converttoJson(policy);

		        Mockito.when(userpolicyService.savePolicy(Mockito.any(Policy.class))).thenReturn(policy);
		        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		                .andReturn();
		        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		        String jsonOutput = mockHttpServletResponse.getContentAsString();
		        assertThat(jsonInput).isEqualTo(jsonOutput);
		        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 }
	 @Test
		public void testGetPolicyById() throws Exception {
			String URI= "/api/v1/policy/{id}";
			Policy policy=new Policy();
		    policy.setId(102);
		    policy.setDescription("Two crore");
		    policy.setYearsOfPayment(2);
	        policy.setAmount(13000.00);
	        policy.setMaturityperiod(21);
	        policy.setMaturityamount(22000.00);
	        
	        String jsonInput = this.converttoJson(policy);

	       Mockito.when(userpolicyService.findPolicyById(Mockito.any())).thenReturn(policy);
	        //Assert.assertTrue(userpolicyService.findUserPolicyById());
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	 @Test
		public void testUpdatePolicy() throws Exception {
			String URI = "/api/v1/policy/{id}";
			Policy policy1=new Policy();
			policy1.setId(105);
		    policy1.setDescription("Three crore");
		    policy1.setYearsOfPayment(4);
		    policy1.setAmount(13400.00);
		    policy1.setMaturityperiod(25);
		    policy1.setMaturityamount(25000.00);
	        String jsonInput = this.converttoJson(policy1);

	        Mockito.when(userpolicyService.updatePolicy(Mockito.any(),Mockito.any())).thenReturn(policy1);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,105).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	 @Test
		public void testDeletePolicy() throws Exception {
			String URI = "/api/v1/policy/{id}";
			Policy policy=new Policy();
			policy.setId(105);
		    policy.setDescription("Three crore");
		    policy.setYearsOfPayment(4);
	        policy.setAmount(13400.00);
	        policy.setMaturityperiod(25);
	        policy.setMaturityamount(25000.00);
	        Mockito.when(userpolicyService.findPolicyById(Mockito.any())).thenReturn(policy);
	        Mockito.when(userpolicyService.deletePolicyById(Mockito.any())).thenReturn(true);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

	        Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		}
	 @Test
		public void testGetAllPolicies() throws Exception {
			String URI = "/api/v1/policy";
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
		    	 List<Policy> PolicyList=new ArrayList<>();
		    	  PolicyList.add(policy1);
		          PolicyList.add(policy1);
		    	 
		    	String jsonInput = this.converttoJson(PolicyList);

		         Mockito.when(userpolicyService.getAllPolicies()).thenReturn(PolicyList);
		         MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
		         MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		         String jsonOutput = mockHttpServletResponse.getContentAsString();

		         assertThat(jsonInput).isEqualTo(jsonOutput);
		     }
		private String converttoJson(Object policy) throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(policy);
		}
	}
