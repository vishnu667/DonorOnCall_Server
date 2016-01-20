/**
 * 
 */
package org.doc.donoroncall.doc;

import java.util.List;

import org.doc.donoroncall.doc.dao.DocDAOHandler;
import org.doc.donoroncall.doc.info.BloodDonationInfo;
import org.doc.donoroncall.doc.info.DocDonorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pandiyaraja
 *
 */
@Service
public class DocService implements DocHandler{

	@Autowired
	private DocDAOHandler dDHandler;
	@Override
	public List<DocDonorInfo> getAllDonors() {
		return dDHandler.getAllDonors();
	}

	@Override
	public List<DocDonorInfo> getDonorsForRecipient(DocDonorInfo dInfo) {
		return dDHandler.getDonorsforRecipient(dInfo);
	}

	@Override
	public String selectDonors(DocDonorInfo dInfo) {		
		return dDHandler.selectDonor(dInfo);
	}

	@Override
	public List<BloodDonationInfo> getPendingRequest(String userName) {		
		return dDHandler.getPendingRequests(userName);
	}

	@Override
	public String acceptBloodRequest(String userName) {
		return dDHandler.acceptBloodRequest(userName);
	}

}
