/**
 * 
 */
package org.doc.donoroncall.admin;

import java.util.List;

import org.doc.core.api.registration.info.RegistrationInfo;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 *
 */
@Service
public interface AdminHandler {
	public List<RegistrationInfo> getRegUserList();
	public String authorizeUser(String userName);
	public String acceptBloodRequest(String userName);
}
