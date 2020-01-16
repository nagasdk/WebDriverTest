package com.restassured;

import org.testng.annotations.Test;

public class RestAssured1 {

	
  @Test(priority=1)
  public void test1() {
	  System.out.println("test1");	  
  }
  
  
  @Test(priority=2)
  public void test2() {
	  System.out.println("test2");	  
  }
  
  
  
}
