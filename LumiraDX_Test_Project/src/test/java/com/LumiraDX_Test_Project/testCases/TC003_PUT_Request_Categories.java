////////////////////////////////////////////////////////////// 
// Author : Tom Chien                                       //
// Date : 06/16/2020                                        //
// Test Name: TC003_PUT_Request_Categories                  //
// Test cover : Send PUT request to UPDATE one category     //
//              name. Check status code, response time      //
//              content type for response                   // 
//                                                          //
//////////////////////////////////////////////////////////////


package com.LumiraDX_Test_Project.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.LumiraDX_Test_Project.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC003_PUT_Request_Categories extends TestBase{
		
	@BeforeClass
		void getAllEmployee() throws InterruptedException {
		test = rep.startTest("TC003_PUT_Request_Categories");	
		test.log(LogStatus.INFO, "Start TC003_PUT_Request_Categories");
		test.log(LogStatus.INFO, "Send PUT Request to MODIFY category name");
		logger.info("******* Started TC003_PUT_Categories_Record *******");
		
		RestAssured.baseURI = ("http://localhost:8888/api/blog");
		httpRequest = RestAssured.given();
		
		JSONObject  requestParams = new JSONObject();
		
		requestParams.put("name", " PHysics");
			
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT, "/categories/4");
		
		Headers allheaders=response.headers(); //capture all the headers from response
		
		for(Header header:allheaders)
		{
			System.out.println(header.getName()+"--->    "+header.getValue());
		}
		
		Thread.sleep(5000);
		}
		
		@AfterTest
		void quit() {
			rep.endTest(test);
			rep.flush();
		}
		
		@Test
		void checkStatusCode() {
			test.log(LogStatus.INFO, "Start TC003_PUT_Request_Categories Check Response Status Code");
			int statusCode = response.statusCode();
			System.out.println(statusCode);
			logger.info("Status code is ==> "+statusCode);
			Assert.assertEquals(204, statusCode);
			test.log(LogStatus.PASS, "Check Response Status Code Passed.");
		}
		
				
		@Test
		void checkContentType()
		{
			test.log(LogStatus.INFO, "Start TC003_PUT_Request_Categories Check Response Content Type");
			logger.info("*********  Checking Content Type ******** ");
			
			String ContentType = response.header("content-type");
			System.out.println("ContentType is ===> " +ContentType);
			logger.info("Content type is ==> " + ContentType);
			Assert.assertEquals(ContentType,"application/json");
			test.log(LogStatus.PASS, "Check Response Content Type Passed.");
		}
		
		
		@Test
		void checkResponseTime()
		{
			test.log(LogStatus.INFO, "Start TC003_PUT_Request_Categories Check Response Time");
			logger.info("*********  Checking Response Time ******** ");
			
			long responseTime = response.getTime();
			logger.info("Response Time is ==> " + responseTime);
			
			if(responseTime > 2000)
				logger.warn("Response time is greater than 2000");
			
			Assert.assertTrue(responseTime < 2000);
			test.log(LogStatus.PASS, "Check Response Time Passed.");
			
		}
		
		
	}
