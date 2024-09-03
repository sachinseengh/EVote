package EVote;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.plaf.nimbus.State;

import java.util.Calendar;
import java.util.Date;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class Adminsql {
	Validations val = new Validations();


	public void adminRegister(String org_name, String phone, String password, String org_code, String logo) {
		
		
		String mdfied_pass= val.getMD5Hash(password);

		String sql = "insert into admin(org_name,phone,password,org_code,logo) " + "values('" + org_name
				+ "','" + phone + "','" + mdfied_pass + "','" + org_code + "','" + logo + "')";

	

		try {

			Conn c = new Conn();
			int affected_row = c.s.executeUpdate(sql);

			if (affected_row > 0) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText(null);
				alert.setContentText("Registered successfully ! You can Login with you phone and password");
				alert.show();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setHeaderText(null);
				alert.setContentText("Registration Failed!");
				alert.show();
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkNumber(String number) {
		String sql = "Select phone from admin where phone='" + number + "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	public boolean electionCode(String code) {
		String sql = "Select org_code from admin where org_code='" + code + "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	public void startElection(String position, String candidate_one_name, String candidate_one_img,
			String candidate_two_name, String candidate_two_img) {

	
		
		 Date date = new Date();
	            
			
			java.sql.Date votingdate = new java.sql.Date(date.getTime());
	        
	        // Create a Calendar instance and set the date
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(votingdate);
	        
	        // Add one day to the calendar
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
	        
	        // Get the new date
	        Date newDate = calendar.getTime();
	        
	        // Convert to java.sql.Date
	    	
	    	java.sql.Date  resultdate = new java.sql.Date(newDate.getTime());
	        
	      
		

		try {
			Conn c = new Conn();

			

			String sql = "INSERT INTO election (org_code, position, candidate_one_name, candidate_one_img, candidate_two_name, candidate_two_img, Election_date) VALUES ('"
					+ getAdminDetails.org_code + "','" + position + "','" + candidate_one_name + "','"
					+ candidate_one_img + "','" + candidate_two_name + "','" + candidate_two_img + "','" + votingdate
					+ "')";
			

			c.s.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkPassword() {
		String password = null;
		try {
			Conn c = new Conn();
			String sql = "select password from admin where phone='"+getAdminDetails.phone+"'";
		
			ResultSet rs = c.s.executeQuery(sql);

			if (rs.next()) {
				password = rs.getString("password");
			}
			return password;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void changePassword(String newpassword) {
		
		String mdfied_pass = val.getMD5Hash(newpassword);
		try {
			Conn c = new Conn();
			String sql = "update admin set password = '" + mdfied_pass + "' where phone = '"+getAdminDetails.phone+"'";

			int affectedrow = c.s.executeUpdate(sql);

			if (affectedrow > 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Successfull");
				alert.setHeaderText(null);
				alert.setContentText("Password Changed successfully");
				alert.show();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setHeaderText(null);
				alert.setContentText("Failed to change password");
				alert.show();
			}
		} catch (Exception e) {

		}

	}
	
	
	//for voter approval or rejection
	public boolean checkVoterNumber(String number) {
		String sql = "Select phone from voters where phone='" + number + "' and  org_code='" + getAdminDetails.org_code
				+ "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}
	

	public boolean checkCitizenshipno(String citizenshipno) {
		String sql = "Select citizenshipno from voters where citizenshipno='" + citizenshipno + "' and org_code='"
				+ getAdminDetails.org_code + "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}


}