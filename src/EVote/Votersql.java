package EVote;

import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Votersql {
	Validations validation  = new Validations();
	
	/*--------------------Register voter----------*/
	
	public void RegisterVoter(String org_code, String fullname, String email, String phone, String localDate,
			String address, String voter_password, String citizenshipno, String passportsize_photo, String employee_id,
			String citizenship_front, String citizenship_back) {
		
		
		
		
		String mdfied_pass = validation.getMD5Hash(voter_password);
	

		String sql = "insert into unverified_voters(org_code,fullname,email,phone,dob,address,citizenshipno,photo,employee_id,citizenship_front,citizenship_back,password) values ("
				+ "'" + org_code + "','" + fullname + "','" + email + "'," + "'" + phone + "','" + localDate + "','"
				+ address + "','" + citizenshipno + "','" + passportsize_photo + "','" + employee_id + "','"
				+ citizenship_front + "','" + citizenship_back + "','" +mdfied_pass+ "')";

		try {
			Conn c = new Conn();
			int affectedrow = c.s.executeUpdate(sql);

			if (affectedrow > 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Successfull");
				alert.setHeaderText(null);
				alert.setContentText(
						"Registration form submitted successfully. "
						+ "You can check your Registration Status ");
				alert.show();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setHeaderText(null);
				alert.setContentText("Registration form submission failed");
				alert.show();
			}

		} catch (Exception e) {

		}

	}

	/*--------------------set organization name----------*/
	public String setOrganizationName(String code) {
		Conn c = new Conn();
		String name = null;
		try {

			String sql = "select org_name from admin where org_code ='" + code + "'";
			ResultSet rs = c.s.executeQuery(sql);

			if (rs.next()) {
				name = rs.getString("org_name");
			}

		} catch (Exception e) {

		}
		return name;
	}

	/*--------------------change password----------*/
	public void changePassword(String newpassword) {
		
		String mdfied_pass = validation.getMD5Hash(newpassword);
	
		
		try {
			Conn c = new Conn();
			String sql = "update voters set password = '" + mdfied_pass + "' where phone = '" + getDetails.phone
					+ "'  and org_code='" + getDetails.org_code + "'";

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

	/*--------------------check existing password----------*/
	public String checkPassword() {
		String password = null;
		try {
			Conn c = new Conn();
			String sql = "select password from voters where phone='" + getDetails.phone + "' and org_code='"
					+ getDetails.org_code + "'";
			
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

	/*--------------------show candidate----------*/
	public void showcandidate(ImageView candidate_one, RadioButton candidate_one_radio, ImageView candidate_two,
			RadioButton candidate_two_radio, Label position, Label electiondate, Label org_name) {
		String sql = "select * from election where org_code='" + getDetails.org_code + "'";

		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);

			if (rs.next()) {
				Image c_one = new Image(
						"file:" + "candidateimage/" + getDetails.org_code + rs.getString("candidate_one_img"), 188, 160,
						false, true);
				candidate_one.setImage(c_one);

				Image c_two = new Image(
						"file:" + "candidateimage/" + getDetails.org_code + rs.getString("candidate_two_img"), 188, 160,
						false, true);
				candidate_two.setImage(c_two);

				candidate_one_radio.setText(rs.getString("candidate_one_name"));
				candidate_two_radio.setText(rs.getString("candidate_two_name"));
				position.setText(rs.getString("position"));
				electiondate.setText(String.valueOf(rs.getDate("Election_date")));
				org_name.setText(getDetails.org_name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*--------------------casting vote----------*/
	public void vote(String candidate) {
		String sql = "insert into votes(citizenshipno,org_code,votefor) values ('" + getDetails.citizenshipno + "','"
				+ getDetails.org_code + "','" + candidate + "')";
		try {
			Conn c = new Conn();
			int affected_row = c.s.executeUpdate(sql);

			if (affected_row > 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("successful");
				alert.setHeaderText(null);
				alert.setContentText("Voted Successfully");
				alert.show();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed");
				alert.setHeaderText(null);
				alert.setContentText("Failed to vote");
				alert.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*--------------------Check number for registration----------*/

	public boolean checkNumber(String number, String org_code) {
		String sql = "Select phone from voters where phone='" + number + "' and  org_code='" + org_code + "'";
		String sql2 = "Select phone from unverified_voters where phone='" + number + "' and  org_code='" + org_code
				+ "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;
			} else {
				ResultSet rs2 = c.s.executeQuery(sql2);
				if (rs2.next()) {
					count++;
				}
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

	/*--------------------check email for registration----------*/
	public boolean checkEmail(String email, String org_code) {
		String sql = "Select email from voters where email='" + email + "' and  org_code='" + org_code + "'";
		String sql2 = "Select email from unverified_voters where email='" + email + "' and  org_code='" + org_code
				+ "'";
		int count = 0;
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				count++;

			} else {
				ResultSet rs2 = c.s.executeQuery(sql2);
				if (rs2.next()) {
					count++;
				}
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

	/*--------------------check citizenship for registration----------*/
	public boolean checkCitizenshipno(String citizenshipno, String org_code) {
		String sql = "Select citizenshipno from voters where citizenshipno='" + citizenshipno + "' and org_code='"
				+ org_code + "'";

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

	/*--------------------check organization code----------*/
	public boolean checkOrgCode(String code) {
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

}
