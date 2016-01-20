/**
 * 
 */
package org.doc.donoroncall.admin;

import java.util.List;

import org.doc.core.api.registration.info.RegistrationInfo;
import org.doc.core.util.DocMailingInterface;
import org.doc.donoroncall.admin.dao.AdminDAOHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 *
 */
@Service
public class AdminService implements AdminHandler{

	@Autowired
	private AdminDAOHandler adminDAOHandler;
	
	@Autowired
	DocMailingInterface dmi;
	
	@Override
	public List<RegistrationInfo> getRegUserList() {
		List<RegistrationInfo> regUserList = adminDAOHandler.getRegUserList();
		return regUserList;
	}

	@Override
	public String authorizeUser(String userName) {
		String resString = adminDAOHandler.authorizeUser(userName);
		if(resString.equalsIgnoreCase("success")){
			dmi.sendMail(userName, "Registration request approved", "Hi, Your registration has been done successfully!");
		}
		return resString;
	}

	@Override
	public String acceptBloodRequest(String userName) {
		String resString = adminDAOHandler.acceptRequest(userName);
		if(resString.equalsIgnoreCase("success")){
			dmi.sendMail(userName, "Blood Request request approved", "Hi, Your blood request accepted successfully!");
		}
		return resString;
	}

}
