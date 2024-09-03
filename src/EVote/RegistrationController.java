package EVote;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegistrationController implements Initializable {

	@FXML
	private Label address_error;

	@FXML
	private TextField address_txtfield;

	@FXML
	private AnchorPane center_form;

	@FXML
	private ImageView citizenback_imageview;

	@FXML
	private ImageView citizenfront_imageview;

	@FXML
	private Button citizenshipback_addbtn;

	@FXML
	private Label citizenshipback_error;

	@FXML
	private Label citizenshipback_name;

	@FXML
	private Button citizenshipfront_addbtn;

	@FXML
	private Label citizenshipfront_error;

	@FXML
	private Label citizenshipfront_img;

	@FXML
	private Label citizenshipno_error;

	@FXML
	private TextField citizenshipno_txtfield;

	@FXML
	private Label dateofbirth_error;

	@FXML
	private DatePicker datepicker_txtfield;

	@FXML
	private Label email_error;

	@FXML
	private TextField email_txt;

	@FXML
	private Label employee_id_error;

	@FXML
	private Label employee_id_img;

	@FXML
	private Button employeeid_addbtn;

	@FXML
	private ImageView employeeid_imageview;

	@FXML
	private Label fullname_error;

	@FXML
	private TextField fullname_txtfield;

	@FXML
	private AnchorPane left_form;

	@FXML
	private Label passportphoto_img;

	@FXML
	private Button passportsizephoto_btn;

	@FXML
	private Label passpost_error;

	@FXML
	private Label password_error;

	@FXML
	private TextField password_textfield;

	@FXML
	private Label phone_error;

	@FXML
	private TextField phone_txtfield;

	@FXML
	private ImageView photo_imageview;

	@FXML
	private FontAwesomeIcon register_closebtn;

	@FXML
	private Button register_fullscreenbtn;

	@FXML
	private Button register_minimize_btn;

	@FXML
	private FontAwesomeIcon register_minimizebtn;

	@FXML
	private Button register_voter_login;

	@FXML
	private Button reset_btn;

	@FXML
	private Button submit_btn;

	@FXML
	private TextField org_code;
	@FXML
	private Label orgcode_error;

	@FXML
	private Label organization_name;

	// ----------------utility function------------------------//
	Utility_Functions uf = new Utility_Functions();

	public void minimize() {
		uf.minimize(register_minimize_btn);
	}

	public void togglefullscreen() {
		uf.togglefullscreen(register_fullscreenbtn);
	}

	public void close() {
		uf.close();
	}

	// ------------------switch_user------------------------------//
	// toggle function to switch between admin and voter

	public void switchUser(ActionEvent e) throws IOException {

		if (e.getSource() == register_voter_login) {

			register_voter_login.getScene().getWindow().hide();

			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

			Utility_Functions utility = new Utility_Functions();

			utility.MouseMovableScene(root);

		}
	}

	File passportsizephoto;
	File employeeid;
	File citizenshipfront;
	File citizenshipback;

	// ---------------passportsizephot-------------------------//
	public void insertpassportsizeimage() {
		FileChooser can1chooser = new FileChooser();
		Stage stage = (Stage) center_form.getScene().getWindow();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
				"*.png");
		can1chooser.getExtensionFilters().add(extFilter);

		passportsizephoto = can1chooser.showOpenDialog(stage);

		votersimgadd(passportsizephoto, photo_imageview, passportphoto_img);
	}

	// --------------------employeeid--------------------------------//
	public void insertemployeeid() {
		FileChooser can1chooser = new FileChooser();
		Stage stage = (Stage) center_form.getScene().getWindow();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
				"*.png");
		can1chooser.getExtensionFilters().add(extFilter);

		employeeid = can1chooser.showOpenDialog(stage);

		votersimgadd(employeeid, employeeid_imageview, employee_id_img);
	}

	// --------------------citizenship front--------------------------------//
	public void insertcitizenfront() {
		FileChooser can1chooser = new FileChooser();
		Stage stage = (Stage) center_form.getScene().getWindow();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
				"*.png");
		can1chooser.getExtensionFilters().add(extFilter);

		citizenshipfront = can1chooser.showOpenDialog(stage);

		votersimgadd(citizenshipfront, citizenfront_imageview, citizenshipfront_img);
	}

	// --------------------employeeid--------------------------------//
	public void insertcitizenback() {
		FileChooser can1chooser = new FileChooser();
		Stage stage = (Stage) center_form.getScene().getWindow();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
				"*.png");
		can1chooser.getExtensionFilters().add(extFilter);

		citizenshipback = can1chooser.showOpenDialog(stage);

		votersimgadd(citizenshipback, citizenback_imageview, citizenshipback_name);
	}

	// ------save the image to the imageview and set label with
	// imagename--------------//
	public void votersimgadd(File selectedFile, ImageView imageview, Label label) {

		if (selectedFile != null) {
			try {
				Image image = new Image(selectedFile.toURI().toString(), 162, 85, false, true);
				imageview.setImage(image);
				label.setText(selectedFile.getName());

				// make all error null
				seterrornull();

			} catch (Exception e) {
			}
		}
	}

	// -------------save candidates image into folder on
	// submit-------------------------
	public void votersimgmoveintofolder(File file) throws IOException {
		Path imagePath = Paths.get("votersimages");
		if (!Files.exists(imagePath)) {
			Files.createDirectories(imagePath);

		}

		String originalFileName = getFileNameWithoutExtension(file);

		String fileExtension = getFileExtension(file);

		String newFileNameWithExtension = org_code.getText() + phone_txtfield.getText() + originalFileName
				+ fileExtension;

		// Copy the selected file to the "votersimages" folder with the new name

		Path destinationPath = Paths.get("votersimages", newFileNameWithExtension);
		Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);


	}

	private String getFileNameWithoutExtension(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1) {
			return fileName; // No extension found
		}
		return fileName.substring(0, dotIndex);
	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
			return ""; // No extension found or extension is at the end without a name
		}
		return fileName.substring(dotIndex);
	}
	// -----------set-Register's-error--------------------------//

	public void seterrornull() {
		orgcode_error.setText("");
		fullname_error.setText("");
		citizenshipback_error.setText("");
		citizenshipfront_error.setText("");
		employee_id_error.setText("");
		passpost_error.setText("");
		citizenshipno_error.setText("");
		password_error.setText("");
		email_error.setText("");
		phone_error.setText("");
		dateofbirth_error.setText("");
		address_error.setText("");

	}

	public void clearallfield() {
		org_code.setText("");
		fullname_txtfield.setText("");
		email_txt.setText("");
		phone_txtfield.setText("");
		datepicker_txtfield.setValue(null);
		address_txtfield.setText("");
		password_textfield.setText("");
		citizenshipno_txtfield.setText("");
		photo_imageview.setImage(null);
		employeeid_imageview.setImage(null);
		citizenfront_imageview.setImage(null);
		citizenback_imageview.setImage(null);
		passportphoto_img.setText("No file selected");
		employee_id_img.setText("No file selected");
		citizenshipfront_img.setText("No file selected");
		citizenshipback_name.setText("No file selected");
		organization_name.setText("");
	}

	/*--------------------show organization while typing----------*/
	public void setOrganization() {
		Votersql vs = new Votersql();
		String name = vs.setOrganizationName(org_code.getText());
		organization_name.setText(name);

	}

	private int calculateAge(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();

		if ((birthDate != null) && (currentDate != null)) {

			return Period.between(birthDate, currentDate).getYears();

		} else {
			return 0;
		}

	}

	
	/*--------------------voter registration----------*/
	public void register() {

		Votersql vs = new Votersql();
		Validations validation = new Validations();

		if (org_code.getText().equals("") || org_code.getText().trim().isEmpty() || org_code.getText() == null) {
			orgcode_error.setText("Organization code  is required");
		} else {

			if (!validation.digitsonly(org_code.getText())) {
				orgcode_error.setText("Invalid organization code");

			} else {
				try {
					if (!vs.checkOrgCode(org_code.getText())) {
						orgcode_error.setText("Incorrect organization code");
					}

				} catch (Exception e) {
					System.out.print(e);
				}
			}
		}

		if (fullname_txtfield.getText().equals("") || fullname_txtfield.getText().trim().isEmpty()
				|| fullname_txtfield.getText() == null) {
			fullname_error.setText("Name is required");
		} else {
			if (!validation.letteronlyregex(fullname_txtfield.getText())) {
				fullname_error.setText("Invalid name");
			}
		}

		if (email_txt.getText().equals("") || email_txt.getText().trim().isEmpty()
				|| fullname_txtfield.getText() == null) {
			email_error.setText("Email is required");
		} else {
			if (!validation.emailvalidation(email_txt.getText())) {
				email_error.setText("Invalid email");
			} else if (vs.checkEmail(email_txt.getText(), org_code.getText())) {
				email_error.setText("Email already Exists");
			}
		}

		if (phone_txtfield.getText().equals("") || phone_txtfield.getText().trim().isEmpty()
				|| phone_txtfield.getText() == null) {
			phone_error.setText("Phone is required");
		} else {

			if (!validation.numberonly(phone_txtfield.getText())) {
				phone_error.setText("Invalid phone number");

			} else if (vs.checkNumber(phone_txtfield.getText(), org_code.getText())) {
				phone_error.setText("Phone number already exists");
			}

		}

		String dateInput = datepicker_txtfield.getEditor().getText();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		if (datepicker_txtfield.getValue() == null || dateInput.isEmpty()) {
			dateofbirth_error.setText("DOB is required");
		} else {
			try {
				LocalDate date = LocalDate.parse(dateInput, dateFormatter);
				if (date != null) {
					int age = calculateAge(date);

					if (age <= 0) {
						dateofbirth_error.setText("Invalid age");
					}
					if (age > 0 && age < 18) {
						dateofbirth_error.setText("Voter's age should be atleast 18");
					}
				}
			} catch (DateTimeParseException e) {
				dateofbirth_error.setText("Invalid DOB format. Please use dd/MM/yyyy.");
				System.err.println("Invalid date input: " + dateInput);
			}
		}

		if (address_txtfield.getText().equals("") || address_txtfield.getText().trim().isEmpty()
				|| address_txtfield.getText() == null) {
			address_error.setText("address is required");
		} else {
			// Phone Validation's code
		}

		if (password_textfield.getText().equals("") || password_textfield.getText().trim().isEmpty()
				|| password_textfield.getText() == null) {
			password_error.setText("Password is required");
		} else {
			String password = password_textfield.getText();

			if (password.length() < 6) {
				password_error.setText("Minimum password length is 6");

			} else if (!validation.hasAlphabet(password)) {
				password_error.setText("At least an alphabet is required");
			} else if (!validation.hasDigit(password)) {
				password_error.setText("At least one digit is required");
			} else if (!validation.hasSpecialCharacter(password)) {
				password_error.setText("At least one special character(_,@,#) is required");
			} else if (!validation.PasswordValidation(password)) {
				password_error.setText("Invalid password");
			}

		}

		if (citizenshipno_txtfield.getText().equals("") || citizenshipno_txtfield.getText().trim().isEmpty()
				|| citizenshipno_txtfield.getText() == null) {
			citizenshipno_error.setText("Citizenshipno is required");
		} else {
			if (!validation.citizenshipnovalidator(citizenshipno_txtfield.getText())) {
				citizenshipno_error.setText("Invalid Citizenshipno");
			} else if (vs.checkCitizenshipno(citizenshipno_txtfield.getText(), org_code.getText())) {
				citizenshipno_error.setText("Citizenshipno already exists");
			}
		}

		if (passportphoto_img.getText().equals("No file selected")) {
			passpost_error.setText("Passport size image Required");
		}
		if (employee_id_img.getText().equals("No file selected")) {
			employee_id_error.setText("Employee id Required");
		}
		if (citizenshipfront_img.getText().equals("No file selected")) {
			citizenshipfront_error.setText("Citizenshipfront image Required");
		}
		if (citizenshipback_name.getText().equals("No file selected")) {
			citizenshipback_error.setText("Citizenshipback image Required");
		}

		if (orgcode_error.getText().equals("") && fullname_error.getText().equals("")
				&& citizenshipback_error.getText().equals("") && citizenshipfront_error.getText().equals("")
				&& employee_id_error.getText().equals("") && employee_id_error.getText().equals("")
				&& citizenshipno_error.getText().equals("") && password_error.getText().equals("")
				&& email_error.getText().equals("") && phone_error.getText().equals("")
				&& dateofbirth_error.getText().equals("") && address_error.getText().equals("")) {

			try {

				votersimgmoveintofolder(passportsizephoto);
				votersimgmoveintofolder(employeeid);
				votersimgmoveintofolder(citizenshipfront);
				votersimgmoveintofolder(citizenshipback);

			} catch (Exception e) {
				e.getStackTrace();
			}

			vs.RegisterVoter(org_code.getText(), fullname_txtfield.getText(), email_txt.getText(),
					phone_txtfield.getText(), datepicker_txtfield.getValue().toString(), address_txtfield.getText(),
					password_textfield.getText(), citizenshipno_txtfield.getText(), passportphoto_img.getText(),
					employee_id_img.getText(), citizenshipfront_img.getText(), citizenshipback_name.getText());

			clearallfield();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// set error null at start
		seterrornull();

	}

}