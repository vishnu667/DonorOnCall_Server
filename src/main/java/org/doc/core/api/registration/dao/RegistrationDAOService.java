/**
 * 
 */
package org.doc.core.api.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doc.core.api.registration.info.LoginInfo;
import org.doc.core.api.registration.info.RegistrationInfo;
import org.doc.core.util.db.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author pandiyaraja
 * 
 */
@Service
public class RegistrationDAOService extends ConnectionProvider implements RegistrationDAOHandler {

	Logger log = LoggerFactory.getLogger(RegistrationDAOService.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.doc.core.api.registration.dao.RegistrationDAOHandler#
	 * loginAuthenticationDAO(org.doc.core.api.registration.info.LoginInfo)
	 */
	private final Logger logger = LoggerFactory.getLogger(RegistrationDAOService.class);	
	public String loginAuthenticationDAO(LoginInfo logInfo) {
		String query = "select * from doc.login_auth where username='"+logInfo.getUserName()+"'";
		ResultSet rs = getResult(query);
		String dbUser = "";
		String dbPass = "";
		String userType = "";
		try {
			 while (rs.next()){
				dbUser = rs.getString("username");
				dbPass = rs.getString("password");
				userType = rs.getString("type");
			 }
			System.out.println("USER NAME ---------> "+dbUser);
			System.out.println("USER NAME ---------> "+dbPass);
			logger.debug(RegistrationDAOService.class+"   USER NSME   "+dbUser);
			logger.debug(RegistrationDAOService.class+"  PASSWORD "+dbPass);
			if(dbUser == logInfo.getUserName() && dbPass.equals(logInfo.getPassword())){
				return "success#"+userType;
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.doc.core.api.registration.dao.RegistrationDAOHandler#
	 * registrationAccountDAO
	 * (org.doc.core.api.registration.info.RegistrationInfo)
	 */
	public String registrationAccountDAO(RegistrationInfo regInfo) {
		int retval=0;
		try {
			log.info(RegistrationDAOService.class+"  RegistrationInfo : "+regInfo.toString());
			String query = "INSERT INTO doc.user (" + "`username`,"    + "`password`,"    + "`name`,"    + "`blood_group`,"    + "`type`,"    + "`dob`,"    + "`authorized` ) VALUES ("
				    + "?, ?, ?, ?, ?, ?, ?)";
			Connection con = getConnection();
		    PreparedStatement st = con.prepareStatement(query);
		    st.setString(1, regInfo.getUserName());
		    st.setString(2, regInfo.getPassWord());
		    st.setString(3, regInfo.getName());
		    st.setString(4, regInfo.getBloodGroup());
		    st.setString(5, regInfo.getType());
		    st.setString(6, regInfo.getDob());
		    st.setString(7, "no");
		    
		    retval = executeUpdate(st);
		    if(retval==1){
		    	return "success";
		    }else{
		    	return "fail";
		    }
		  } 
		  catch (Exception se)
		  {
		    se.printStackTrace();
		    return "fail";
		  }
	}

}
