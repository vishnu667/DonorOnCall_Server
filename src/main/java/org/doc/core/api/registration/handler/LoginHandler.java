/**
 * 
 */
package org.doc.core.api.registration.handler;

import org.doc.core.api.registration.info.LoginInfo;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 * 
 */
@Service
public interface LoginHandler {
	public String loginAuthenticate(LoginInfo lInfo);
}
