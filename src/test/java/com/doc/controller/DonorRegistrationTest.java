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
public class DonorRegistrationTest {
	@Test
	public void donorRegTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		strb.append("{\"userName\":\"pontiyaraja@gmail.com\",\"bloodGroup\":\"O+\",\"hospitalName\":\"Pan\",\"physicianName\":\"Ponti\",\"patient\":\"Donor\",\"purpose\":\"fever\",\"unit\":\"300\",\"howSoon\":\"5\"}");
		Resource resource = rc
				.resource("http://localhost:8080/donoroncall/doc/donor");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println(response);
	}
}
