/**
 * 
 */
package org.doc.donoroncall.admin.dao;

import java.util.List;

import org.doc.core.api.registration.info.RegistrationInfo;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 *
 */
@Service
public interface AdminDAOHandler {
	public List<RegistrationInfo> getRegUserList();
	public String authorizeUser(String userName);
	public String acceptRequest(String userName);	
}
