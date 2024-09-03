package EVote;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class VoterDashboardController implements Initializable {

	@FXML
	private ToggleGroup candidate;

	@FXML
	private ImageView candidate_one_img;

	@FXML
	private RadioButton candidate_one_radio;

	@FXML
	private ImageView candidate_two_img;

	@FXML
	private RadioButton candidate_two_radio;

	@FXML
	private PasswordField changepassword_confirm;

	@FXML
	private Button changepassword_cpass_btn;

	@FXML
	private TextField changepassword_current;

	@FXML
	private PasswordField changepassword_new;

	@FXML
	private Button close_btn;

	@FXML
	private CheckBox cp_confpas_show_checkbox;

	@FXML
	private CheckBox cp_newpas_show_checkbox;

	@FXML
	private Label election_post_name;

	@FXML
	private Button fullscreen_btn;

	@FXML
	private Button minimize_btn;

	@FXML
	private Label mydetails_address_value;

	@FXML
	private ImageView mydetails_cb_image;

	@FXML
	private ImageView mydetails_cf_img;

	@FXML
	private Label mydetails_ctzshpno_value;

	@FXML
	private Label mydetails_dob_value;

	@FXML
	private Label mydetails_email_value;

	@FXML
	private Label mydetails_name_value;

	@FXML
	private Label mydetails_phone_value;

	@FXML
	private Label mydetails_username_value;

	@FXML
	private AnchorPane voter_changepass_form;

	@FXML
	private AnchorPane voter_mydetails_form;

	@FXML
	private Label voter_name_txt;

	@FXML
	private Button voter_nav_cpassword_btn;

	@FXML
	private Button voter_nav_details_btn;

	@FXML
	private Button voter_nav_logout_btn;

	@FXML
	private Circle voter_nav_profileimage;

	@FXML
	private Button voter_nav_vote_btn;

	@FXML
	private Button voter_vote_btn;

	@FXML
	private AnchorPane voter_vote_form;

	@FXML
	private AnchorPane Novoting_form;

	@FXML
	private AnchorPane Thanks_form;

	@FXML
	private Label confirm_pass_error;

	@FXML
	private Label current_pass_error;
	@FXML
	private Label new_pass_error;

	@FXML
	private ImageView voter_image;
	@FXML
	private Label election_date;

	@FXML
	private Label organization_name;

	@FXML
	private Label election_org_name;
	
	
    @FXML
    private FontAwesomeIcon show_conpass;

    @FXML
    private FontAwesomeIcon show_newpass;

	/*----------------utility functions--------------*/

	Utility_Functions uf = new Utility_Functions();

	public void close() {
		uf.close();
	}

	public void minimize() {
		uf.minimize(minimize_btn);
	}

	public void fullscreen() {
		uf.togglefullscreen(fullscreen_btn);
	}

	public void logout() {
		uf.logout(voter_nav_logout_btn);
	}

	/*----------------change scene------------*/

	public void switchScene(ActionEvent e) {
		if (e.getSource() == voter_nav_vote_btn) {
			showElectionorNoElectionorDone();

			voter_mydetails_form.setVisible(false);
			voter_changepass_form.setVisible(false);

			voter_nav_vote_btn.setStyle("-fx-background-color:rgb(15, 120, 149);");
			voter_nav_details_btn.setStyle("-fx-background-color:transparent;");
			voter_nav_cpassword_btn.setStyle("-fx-background-color:transparent;");

		} else if (e.getSource() == voter_nav_details_btn) {
			voter_vote_form.setVisible(false);
			voter_mydetails_form.setVisible(true);
			voter_changepass_form.setVisible(false);
			Novoting_form.setVisible(false);
			Thanks_form.setVisible(false);

			voter_nav_vote_btn.setStyle("-fx-background-color:transparent;");
			voter_nav_details_btn.setStyle("-fx-background-color:rgb(15, 120, 149);");
			voter_nav_cpassword_btn.setStyle("-fx-background-color:transparent;");

		} else if (e.getSource() == voter_nav_cpassword_btn) {
			voter_vote_form.setVisible(false);
			voter_mydetails_form.setVisible(false);
			voter_changepass_form.setVisible(true);
			Novoting_form.setVisible(false);
			Thanks_form.setVisible(false);

			voter_nav_vote_btn.setStyle("-fx-background-color:transparent;");
			voter_nav_details_btn.setStyle("-fx-background-color:transparent;");
			voter_nav_cpassword_btn.setStyle("-fx-background-color:rgb(15, 120, 149);");
		}
	}

	/*------------------Vote Section----------*/

	public void showElectionorNoElectionorDone() {

		ResultSet result;
		String checkvote = "select *from votes where citizenshipno='" + getDetails.citizenshipno + "'and org_code='"+getDetails.org_code+"'";

		try {
			Conn c = new Conn();
			result = c.s.executeQuery(checkvote);

			if (result.next()) {
				voter_vote_form.setVisible(false);
				Thanks_form.setVisible(true);
			} else {
				String sql = "select count(*) as row_count from election where org_code='"+getDetails.org_code+"'";
				try {

					ResultSet rs = c.s.executeQuery(sql);
					Integer row = null;
					while (rs.next()) {
						row = rs.getInt("row_count");
					}
					if (row == 0) {
						Novoting_form.setVisible(true);
					} else {
						voter_vote_form.setVisible(true);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*---------------profile picture and name on the top left corner---------*/

	public void voterDetailsatLeftCorner() {
		String sql = "select * from voters where phone ='" + getDetails.phone + "' and org_code='" + getDetails.org_code
				+ "'";
		
		
		try {
			Conn c = new Conn();

			ResultSet rs = c.s.executeQuery(sql);
			if (rs.next()) {
				voter_name_txt.setText(rs.getString("name"));

				// FETCHING ALL THE DETAILS AND SETTING IN THE getDetails file
				getDetails.id = rs.getInt("id");
				getDetails.phone = rs.getString("phone");
				getDetails.name = rs.getString("name");
				getDetails.email = rs.getString("email");
				getDetails.dob = rs.getString("dob");
				getDetails.citizenshipno = rs.getString("citizenshipno");
				getDetails.address = rs.getString("address");
				getDetails.citizenship_back = rs.getString("citizenshipback");
				getDetails.citizenship_front = rs.getString("citizenshipfront");

				try {

					Image profilepic = new Image("file:" + "votersimages/" +getDetails.org_code+getDetails.phone+ rs.getString("photo"), 105, 79, false,
							true);
					voter_image.setImage(profilepic);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*------------------------------organization name------------------------*/
	public void setOrgName() {
		Conn c = new Conn();
		String sql = "select * from admin where org_code='" + getDetails.org_code + "' ";

		try {
			ResultSet rs = c.s.executeQuery(sql);

			if (rs.next()) {
				getDetails.org_name = rs.getString("org_name");

				organization_name.setText(getDetails.org_name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------------------voting area--------------------------------*/

	public void showcandidates() {
		Votersql vs = new Votersql();
		vs.showcandidate(candidate_one_img, candidate_one_radio, candidate_two_img, candidate_two_radio,
				election_post_name, election_date, election_org_name);
	}

	public void select_can_one() {
		candidate_one_radio.setSelected(true);
	}

	public void select_can_two() {
		candidate_two_radio.setSelected(true);
	}

	public void vote() {
		if (!candidate_one_radio.isSelected() && !candidate_two_radio.isSelected()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Candidate not selected");
			alert.showAndWait();
		} else {
			String candidate;

			if (candidate_one_radio.isSelected()) {
				candidate = candidate_one_radio.getText();
			} else {
				candidate = candidate_two_radio.getText();
			}

			Votersql vs = new Votersql();
			vs.vote(candidate);
			showElectionorNoElectionorDone();
			candidate_one_radio.setSelected(false);
			candidate_two_radio.setSelected(false);
		}
	}

	/*------------------show Details------------------*/
	public void showDetails() {

		mydetails_name_value.setText(getDetails.name);
		mydetails_email_value.setText(getDetails.email);
		mydetails_phone_value.setText(getDetails.phone);
		mydetails_address_value.setText(getDetails.address);
		mydetails_ctzshpno_value.setText(getDetails.citizenshipno);
		mydetails_dob_value.setText(getDetails.dob);

		Image cf = new Image("file:" + "votersimages/" +getDetails.org_code+getDetails.phone+ getDetails.citizenship_front);
		mydetails_cf_img.setImage(cf);

		Image bf = new Image("file:" + "votersimages/" +getDetails.org_code+getDetails.phone+getDetails.citizenship_back);
		mydetails_cb_image.setImage(bf);
	}

	/*----------------------------change password----------------------*/
	Validations validation = new Validations();

	public void passworderrorsetnull() {
		current_pass_error.setText("");
		new_pass_error.setText("");
		confirm_pass_error.setText("");
	}


	/* ---------------------Eye Button to Show Password----------------*/
	public void showNewPass() {
		validation.showNewPass(changepassword_new);
	}
	public void hideNewPass() {
		validation.hideNewPass(changepassword_new);
	}

	public void showConfirmPass() {
		validation.showConfirmPass(changepassword_confirm);
	}
	public void hideConfirmPass() {
		validation.hideConfirmPass(changepassword_confirm);
	}
	

	/*--------------------Change password--------------------------*/
	public void changepassword() {
		Votersql vs = new Votersql();

		VoterChangePassword cp = new VoterChangePassword();
		cp.changepassword(changepassword_current, changepassword_new, changepassword_confirm, current_pass_error,
				new_pass_error, confirm_pass_error);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		voterDetailsatLeftCorner();
		setOrgName();

		showElectionorNoElectionorDone();

		showcandidates();

		// Voter Details
		showDetails();

		passworderrorsetnull();

	}

}
