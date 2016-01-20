/**
 * 
 */
package com.doc.controller;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Test;

/**
 * @author Pandiyaraja
 *
 */
public class AdminControllerTest {
	@Test
	public void registerUserListTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		//strb.append("{\"userName\":\"pontiyaraja@gmail.com\",\"passWord\":\"Pan\",\"name\":\"Pan\",\"bloodGroup\":\"O+\",\"type\":\"Donor\",\"dob\":\"1987-06-28\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/admn/regUserList");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
	//@Test
	public void userAuthorizationTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		strb.append("{\"userName\":\"pontiyaraja@gmail.com\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/admn/authorize");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
	//@Test
	public void bloodRequesAcceptanceTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		strb.append("{\"userName\":\"pontiyaraja14@gmail.com\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/admn/acceptBloodRequest");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
}
