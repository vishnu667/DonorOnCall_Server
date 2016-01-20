/**
 * 
 */
package org.doc.core.util;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 * @author pandiyaraja
 *
 */
@Service
public class DocMailingProcessor implements DocMailingInterface{
	
	  public DocMailingProcessor() {
		  
	  }
	  @Autowired
	  private MailSender mailSender;
	  
	  @Autowired
	  private SimpleMailMessage templateMessage;

	    /*public void setMailSender(MailSender mailSender) {
	        this.mailSender = mailSender;
	    }*/

	   /* public void setTemplateMessage(SimpleMailMessage templateMessage) {
	        this.templateMessage = templateMessage;
	    }*/

	  //arugument constructor, use with params - gmail smtp by default
	  @Override
	  public void sendMail(String recip, String subj, String message) {
		try
	    {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("raja.pandiya@yahoo.com");
	        email.setTo(recip);
	        email.setSubject(subj);
	        email.setText(message);         
	        // sends the e-mail
	        mailSender.send(email);      
	    }
	    catch(Exception ex)
	    {
	      if (ex instanceof AddressException)
	      {
	    	  ex.printStackTrace();
	        //JOptionPane.showMessageDialog(null, "Invalid address.", "Error", JOptionPane.ERROR_MESSAGE);
	      }
	      else if (ex instanceof SendFailedException)
	      {
	    	  ex.printStackTrace();
	        //JOptionPane.showMessageDialog(null, "Unable to send message. Fix fields.", "Error", JOptionPane.ERROR_MESSAGE);
	      }
	      else if(ex instanceof AuthenticationFailedException)
	      {
	    	  ex.printStackTrace();
	        //JOptionPane.showMessageDialog(null, "Password or address invalid incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
	      }
	      else
	      {
	    	  ex.printStackTrace();
	        //JOptionPane.showMessageDialog(null, "Unknown error.", "Error", JOptionPane.ERROR_MESSAGE);
	      }
	    }
	  }

}
