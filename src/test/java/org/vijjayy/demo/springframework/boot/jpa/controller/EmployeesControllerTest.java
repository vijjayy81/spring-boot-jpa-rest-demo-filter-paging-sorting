package org.vijjayy.demo.springframework.boot.jpa.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.vijjayy.demo.springframework.boot.jpa.v1.api.model.ApiModelEmployees;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeesControllerTest {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testGetEmployees() {
		
		// Salary < 150000, pageSize 5
		Response response = RestAssured
				.given().port(port).when()
				.queryParam("pageNumber", 1)
				.queryParam("pageSize", 5)
				.queryParam("sort", "+salary")
				.queryParam("salary", "lt:150000")
				.get("/employees")
				.peek();

		Assert.assertEquals(200, response.getStatusCode());
		
		ApiModelEmployees responseObj = response.getBody().as(ApiModelEmployees.class);
		
		Assert.assertNotNull(responseObj);
		Assert.assertNotNull(responseObj.getData());
		Assert.assertNotNull(responseObj.getPaging());
		Assert.assertEquals(5, responseObj.getData().size());
		Assert.assertTrue(responseObj.getData().stream().allMatch(data -> data.getSalary() < 150000));
		

		// Salary > 150000, pageSize 4
		response = RestAssured
				.given().port(port).when()
				.queryParam("pageNumber", 1)
				.queryParam("pageSize", 4)
				.queryParam("sort", "-salary")
				.queryParam("salary", "gt:150000")
				.get("/employees")
				.peek();

		Assert.assertEquals(200, response.getStatusCode());
		
		responseObj = response.getBody().as(ApiModelEmployees.class);
		
		Assert.assertNotNull(responseObj);
		Assert.assertNotNull(responseObj.getData());
		Assert.assertNotNull(responseObj.getPaging());
		Assert.assertEquals(4, responseObj.getData().size());
		Assert.assertTrue(responseObj.getData().stream().allMatch(data -> data.getSalary() > 150000));

		
		
	}

}
