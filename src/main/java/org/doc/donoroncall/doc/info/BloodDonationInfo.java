/**
 * 
 */
package org.doc.donoroncall.doc.info;

/**
 * @author Pandiyaraja
 *
 */
public class BloodDonationInfo {
	private String requester;
	private String donor;
	private String bloodGroup;
	private String hospitalName;
	private String physicianName;
	private String patientName;
	private int unit;
	private String approved;
	
	
	/**
	 * @return the requester
	 */
	public String getRequester() {
		return requester;
	}
	/**
	 * @param requester the requester to set
	 */
	public void setRequester(String requester) {
		this.requester = requester;
	}
	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	/**
	 * @return the physicianName
	 */
	public String getPhysicianName() {
		return physicianName;
	}
	/**
	 * @param physicianName the physicianName to set
	 */
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}
	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}
	/**
	 * @return the donor
	 */
	public String getDonor() {
		return donor;
	}
	/**
	 * @param donor the donor to set
	 */
	public void setDonor(String donor) {
		this.donor = donor;
	}
	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the approved
	 */
	public String getApproved() {
		return approved;
	}
	/**
	 * @param approved the approved to set
	 */
	public void setApproved(String approved) {
		this.approved = approved;
	}	
}
