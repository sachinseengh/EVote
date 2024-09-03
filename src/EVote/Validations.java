package EVote;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

class Validations {

	public Boolean letteronlyregex(String txt) {
		String regex = "^[a-zA-Z\\s]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean hasDigit(String txt) {
		String regex = ".*\\d.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean hasAlphabet(String txt) {
		String regex = ".*[a-zA-Z].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean hasSpecialCharacter(String txt) {
		String regex = ".*[_#@#].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean PasswordValidation(String txt) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d_#@$]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean numberonly(String txt) {
		String regex = "^(98|96|97)\\d{8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean digitsonly(String txt) {
		String regex = "^\\d+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean emailvalidation(String txt) {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public Boolean citizenshipnovalidator(String txt) {
		String regex = "^(\\d+/\\d+)|(\\d+-\\d+-\\d+-\\d+)|(\\d){8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	public void showNewPass(PasswordField passwordfield) {
		passwordfield.setPromptText(passwordfield.getText());
		passwordfield.setText("");
	}

	public void hideNewPass(PasswordField passwordfield) {
		passwordfield.setText(passwordfield.getPromptText());
		passwordfield.setPromptText("");
	}

	public void showConfirmPass(PasswordField passwordfield) {
		passwordfield.setPromptText(passwordfield.getText());
		passwordfield.setText("");
	}

	public void hideConfirmPass(PasswordField passwordfield) {
		passwordfield.setText(passwordfield.getPromptText());
		passwordfield.setPromptText("");
	}
	
	 public  String getMD5Hash(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());

	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);

	            // Convert message digest into hex value
	            String hashText = no.toString(16);
	            while (hashText.length() < 32) {
	                hashText = "0" + hashText;
	            }
	            return hashText;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

}