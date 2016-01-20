/**
 * 
 */
package org.doc.donoroncall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doc.core.api.registration.info.RegistrationInfo;
import org.doc.donoroncall.admin.AdminHandler;
import org.doc.donoroncall.admin.dao.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * @author pandiyaraja
 *
 */
@Controller
@RequestMapping("/admn")
public class AdminController {
	@Autowired
	private AdminHandler adminHandler;
	@RequestMapping(value = "/regUserList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody <T> String listAllRegisteredUsers(){
		Gson gson=new Gson();
		List<RegistrationInfo> userList = adminHandler.getRegUserList();
		Map<String, T> obj = new HashMap<String,T>();
		System.out.println("User list size ---->  "+userList.size());
		if(!userList.isEmpty()){
		obj.put("users", (T) userList);
		obj.put("status", (T)"success");
		}else{
			obj.put("status", (T)"failed");
		}		
		return gson.toJson(obj);
	}
	
	@RequestMapping(value = "/authorize", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String authorizeUser(@RequestBody String userString){
		Gson gson=new Gson();
		AdminInfo admiInfo = gson.fromJson(userString, AdminInfo.class);
		String resString =  adminHandler.authorizeUser(admiInfo.getUserName());
		Map<String, String> obj = new HashMap<String,String>();
		if(resString.equalsIgnoreCase("success")){
		obj.put("authorization", "yes");
		obj.put("status", "success");
		}else{
			obj.put("authorization", "unknown");
			obj.put("status", "failed");
		}		
		return gson.toJson(obj);
	}
	
	@RequestMapping(value = "/acceptBloodRequest", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String acceptBloodRequest(@RequestBody String userString){
		Gson gson=new Gson();
		AdminInfo admiInfo = gson.fromJson(userString, AdminInfo.class);
		String resString = adminHandler.acceptBloodRequest(admiInfo.getUserName());
		Map<String, String> obj = new HashMap<String,String>();
		if(resString.equalsIgnoreCase("success")){
		obj.put("blood_request", "accepted");
		obj.put("status", "success");
		}else{
			obj.put("blood_request", "unknown");
			obj.put("status", "failed");
		}		
		return gson.toJson(obj);
	}
}
