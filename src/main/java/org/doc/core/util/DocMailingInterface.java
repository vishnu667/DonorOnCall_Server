/**
 * 
 */
package org.doc.core.util;

import org.springframework.stereotype.Service;

/**
 * @author Pandiyaraja
 *
 */
@Service
public interface DocMailingInterface {
	public void sendMail(String recip, String subj, String message);
}
