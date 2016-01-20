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
public class LoginTest {
	@Test
	public void entryTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		strb.append("{\"userName\":\"pontiyaraja@gmail.com\",\"passWord\":\"Pan\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/doc/gateWay");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
}
