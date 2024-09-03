package EVote;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;


class ChangePassword{
	Validations validation = new Validations();
	Adminsql as = new Adminsql();
  
	public void changepassword(TextField current,TextField newp ,TextField confirm,Label currenterror,Label
			newerror,Label confimerror) {
		
		if (current.getText().equals("") && current.getText().trim().isEmpty()
				&& current.getText().isEmpty()) {

			currenterror.setText("Current Password is required");

		}

		if (newp.getText().equals("") && newp.getText().trim().isEmpty()
				&& newp.getText().isEmpty()) {
			newerror.setText("New Password is required");  
			
		} else {
			String txt = newp.getText();

			if (!validation.hasDigit(txt)) {
				newerror.setText("At least one digit is required");
			} else if (!validation.hasAlphabet(txt)) {
				newerror.setText("At least one alphabet is required");
			} else if (!validation.hasSpecialCharacter(txt)) {
				newerror.setText("Special Character( _,@,#) is required");
			}else if (!validation.PasswordValidation(txt)) {
				newerror.setText("Invalid characters");
			}

		}

		if (confirm.getText().equals("") && confirm.getText().trim().isEmpty()
				&& confirm.getText().isEmpty()) {
			confimerror.setText("password required");
		}
		if(!newp.getText().trim().isEmpty()&& !newp.getText().trim().isEmpty()) {
			
			if(!newp.getText().equals(confirm.getText())) {
				confimerror.setText("Passwords not matched");
			}else {
			
			
			
			
			
			
			
			//---------------check current password is correct or not-----------//
			if(!current.getText().trim().isEmpty()) {
		
			String ogpassword =  as.checkPassword();
			String mdfied_current = validation.getMD5Hash(current.getText());
			
			
			
			if(!mdfied_current.equals(ogpassword)) {
				currenterror.setText("Incorrect Password");
			}
			}
			}
		}
	
	
		
	if(newerror.getText().equals("")&& currenterror.getText().equals("")&& confimerror.getText().equals("")) {
		
		
		as.changePassword(newp.getText());
		
		newp.setText("");
		current.setText("");
		confirm.setText("");
	}
	}
}