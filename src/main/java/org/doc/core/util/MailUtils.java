/**
 * 
 */
package org.doc.core.util;

/**
 * @author pandiyaraja
 *
 */
public class MailUtils {
	private String fromAddress;
	private String toAddress;
	private String mailCC;
	private String mailBCC;
	private String mailSubject;
	private String mailBody;
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the mailCC
	 */
	public String getMailCC() {
		return mailCC;
	}
	/**
	 * @param mailCC the mailCC to set
	 */
	public void setMailCC(String mailCC) {
		this.mailCC = mailCC;
	}
	/**
	 * @return the mailBCC
	 */
	public String getMailBCC() {
		return mailBCC;
	}
	/**
	 * @param mailBCC the mailBCC to set
	 */
	public void setMailBCC(String mailBCC) {
		this.mailBCC = mailBCC;
	}
	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}
	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	/**
	 * @return the mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}
	/**
	 * @param mailBody the mailBody to set
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
}
