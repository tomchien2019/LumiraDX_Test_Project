////////////////////////////////////////////////////////////// 
// Author : Tom Chien                                       //
// Date : 06/16/2020                                        //
// Test Name: TC001_GET_Request_Categories                  //
// Test cover : Send GET request to get all categories.     //
//              Check body, status code, response time      //
//              content type, content length, and status    // 
//              line for response.                          //
//////////////////////////////////////////////////////////////
                
package com.LumiraDX_Test_Project.testCases;

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


public class TC001_GET_Request_Categories extends TestBase {
	
	@BeforeClass
	void getAllCategories() throws InterruptedException {
	test = rep.startTest("TC001_GET_Request_Categories");	
	test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories");
	test.log(LogStatus.INFO, "Send GET Request to get all categories");
	logger.info("******* Start TC001_Get_All_Categories *******");
	
	RestAssured.baseURI = ("http://localhost:8888/api/blog");
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET, "/categories/");
	Headers allheaders=response.headers();
	for(Header header:allheaders)
	{
		System.out.println(header.getName()+"--->    "+header.getValue());
	}
	
	
	Thread.sleep(3);
	}
	
		
	@AfterTest
	void quit() {
		rep.endTest(test);
		rep.flush();
	}
	
	@Test
	void checkResponseBody()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Response Body");
		logger.info("*********  Checking Response Body ******** ");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		logger.info("Response Body ==> " + responseBody);
		Assert.assertTrue(responseBody!=null);
		test.log(LogStatus.PASS, "Check Response Body Passed.");
	}
	
	@Test
	void checkStatusCode()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Status Code");
		logger.info("*********  Checking Status Code ******** ");
		
		int statusCode = response.getStatusCode();
		System.out.println("statusCode is ===> "+statusCode);
		logger.info("Status Code is ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
		test.log(LogStatus.PASS, "Check Response Status Code Passed.");
		
	}
	
	@Test
	void checkResponseTime()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Response Time");
		logger.info("*********  Checking Response Time ******** ");
		
		long responseTime = response.getTime();
		logger.info("Response Time is ==> " + responseTime);
		
		if(responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		
		Assert.assertTrue(responseTime < 2000);
		test.log(LogStatus.PASS, "Check Response time Passed.");
		
	}
	
	@Test
	void checkStatusLine()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Response Status Line");
		logger.info("*********  Checking Status Line ******** ");
		
		String statusLine = response.getStatusLine();
		System.out.println("statusLine is ===> " +statusLine);
		logger.info("Status Line is ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.0 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Response Content Type");
		logger.info("*********  Checking Content Type ******** ");
		
		String ContentType = response.header("content-type");
		System.out.println("ContentType is ===> " +ContentType);
		logger.info("Content type is ==> " + ContentType);
		Assert.assertEquals(ContentType,"application/json");
		test.log(LogStatus.PASS, "Check Response Content Type Passed.");
		
	}
	
		
	@Test
	void checkContentLength()
	{
		test.log(LogStatus.INFO, "Start TC001_GET_Request_Categories Check Response Content Length");
		logger.info("*********  Checking content encoding ******** ");
		
		String contentLength = response.header("Content-Length");
		System.out.println("content length is ==> "+contentLength);
		logger.info("Content length is ==> " + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		test.log(LogStatus.PASS, "Check Response Content Length Passed.");
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Categories **********");
		test.log(LogStatus.INFO, "Finished TC001_Get_All_Categories");
		rep.endTest(test);
		rep.flush();
	}
			
	
}
