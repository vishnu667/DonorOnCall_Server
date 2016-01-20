/**
 * 
 */
package org.doc.donoroncall.donar;

import org.doc.donoroncall.doc.info.DocDonorInfo;
import org.doc.donoroncall.doc.info.DocRequesterInfo;

/**
 * @author pandiyaraja
 * 
 */
public interface DocRequestHandler {
	public String docRequest(DocRequesterInfo drInfo);
	public String docDonorRequest(DocDonorInfo ddInfo);
}
