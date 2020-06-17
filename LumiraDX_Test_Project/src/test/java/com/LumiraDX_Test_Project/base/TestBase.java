package com.LumiraDX_Test_Project.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.LumiraDX_Test_Project.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static RequestSpecification httpRequest;
	public static Response response;
		
	public Logger logger;
	
	@BeforeClass
	public void setup(){
		
		logger=Logger.getLogger("EmployeesRestAPI");//added Logger
		PropertyConfigurator.configure("log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
		
	}
	
}
