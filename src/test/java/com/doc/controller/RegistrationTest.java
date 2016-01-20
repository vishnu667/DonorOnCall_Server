/**
 * 
 */
package com.doc.controller;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Test;

/**
 * @author pandiyaraja
 * 
 */
public class RegistrationTest {
	@Test
	public void regTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		strb.append("{\"userName\":\"pontiyaraja14@gmail.com\",\"passWord\":\"Pan\",\"name\":\"Pan\",\"bloodGroup\":\"O+\",\"type\":\"Donor\",\"dob\":\"1987-06-28\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/doc/register");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
}
