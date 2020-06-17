////////////////////////////////////////////////////////////// 
// Author : Tom Chien                                       //
// Date : 06/16/2020                                        //
// Test Name: TC002_POST_Request_Categories                 //
// Test cover : Send post request to create one categories. //
//              Check status code, response time            //
//              content type, content length for response   // 
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

public class TC002_POST_Request_Categories extends TestBase{
	
	@BeforeClass
	void addOneCategory() throws InterruptedException {
	test = rep.startTest("TC002_POST_Request_Categories");	
	test.log(LogStatus.INFO, "Start TC002_POST_Request_Categories");
	test.log(LogStatus.INFO, "Send POST Request to add one category");
	logger.info("******* Started TC002_POST_Categories_Record *******");
	
	RestAssured.baseURI = ("http://localhost:8888/api/blog");
	httpRequest = RestAssured.given();
	
	JSONObject  requestParams = new JSONObject();
	
	requestParams.put("name", " Physics");
		
	httpRequest.header("Content-Type","application/json");
	
	httpRequest.body(requestParams.toJSONString());
	
	response = httpRequest.request(Method.POST, "/categories/");
	
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
		test.log(LogStatus.INFO, "Start TC002_POST_Request_Categories Check Response Status Code");
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		logger.info("Status code is ==> "+statusCode);
		Assert.assertEquals(201, statusCode);
		test.log(LogStatus.PASS, "Check Response Status Code Passed.");
	}
	
	@Test
	void checkContentLength()
	{
		test.log(LogStatus.INFO, "Start TC002_POST_Request_Categories Check Response content length");
		logger.info("*********  Checking content length ******** ");
		
		String contentLength = response.header("Content-Length");
		System.out.println("content length is ==> "+contentLength);
		logger.info("Content length is ==> " + contentLength);
		
		if(Integer.parseInt(contentLength)>20)
			logger.warn("Content length is greater than 20");
		
		Assert.assertTrue(Integer.parseInt(contentLength)<20);
		test.log(LogStatus.PASS, "Check Response Content Length Passed.");
		
	}
	
	@Test
	void checkContentType()
	{
		test.log(LogStatus.INFO, "Start TC002_POST_Request_Categories Check Response Content Type");
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
		test.log(LogStatus.INFO, "Start TC002_POST_Request_Categories Check Response Time");
		logger.info("*********  Checking Response Time ******** ");
		
		long responseTime = response.getTime();
		logger.info("Response Time is ==> " + responseTime);
		
		if(responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		
		Assert.assertTrue(responseTime < 2000);
		test.log(LogStatus.PASS, "Check Response Time Passed.");
	}
	
		
}
