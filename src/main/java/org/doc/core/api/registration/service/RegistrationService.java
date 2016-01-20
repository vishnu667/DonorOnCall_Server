/**
 * 
 */
package org.doc.core.api.registration.service;

import org.doc.core.api.registration.dao.RegistrationDAOHandler;
import org.doc.core.api.registration.handler.RegisterationHandler;
import org.doc.core.api.registration.info.RegistrationInfo;
import org.doc.core.util.DocMailingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 * 
 */
@Service
public class RegistrationService implements RegisterationHandler {
	@Autowired
	private RegistrationDAOHandler rdHand;
	
	@Autowired
	DocMailingInterface dmi;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.doc.core.api.registration.handler.RegisterationHandler#registerAccount
	 * (org.doc.core.api.registration.handler.RegisterationHandler)
	 */
	@Override
	public String registerAccount(RegistrationInfo regInfo) {
		String regRes = rdHand.registrationAccountDAO(regInfo);
		if(regRes.equalsIgnoreCase("success")){
			dmi.sendMail("pontiyaraja14@gmail.com", "REG request", "Hi, Please accept my reg request");			
		}
		return regRes;
	}

}
