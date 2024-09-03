package EVote;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class Utility_Functions{
	// ----------------------minimize
	// screen------------------------------------------
	public void minimize(Button minimize_btn) {
		Stage stage = (Stage) minimize_btn.getScene().getWindow();
		stage.setIconified(true);
	}

	// --------------------toggle
	// fullscreen---------------------------------------------

	public void togglefullscreen(Button fullscreen_btn) {
		Stage stage = (Stage) fullscreen_btn.getScene().getWindow();

		stage.setFullScreen(!stage.isFullScreen());
	}

	// ------------------ close the
	// program----------------------------------------------
	public void close() {
		System.exit(0);
	}
	
	
	private double x = 0;
	private double y = 0;
	public void logout(Button nav_logout_btn) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm logout");
		alert.setHeaderText(null);
		alert.setContentText("Do you want to logout?");

		Optional<ButtonType> option = alert.showAndWait();

		if (option.get().equals(ButtonType.OK)) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			

				MouseMovableScene(root);
				nav_logout_btn.getScene().getWindow().hide();
			} catch (Exception e) {

			}
		}
	}
	
	
	public void MouseMovableScene(Parent root) {
   	 Stage stage = new Stage();
        
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
		           @Override
		           public void handle(MouseEvent event) {
		               x= event.getSceneX();
		               y= event.getSceneY();
		           }

		         });

		         root.setOnMouseDragged(new EventHandler<MouseEvent>(){
		           @Override
		           public void handle(MouseEvent event) {
		              stage.setX(event.getScreenX()-x);
		              stage.setY(event.getScreenY()-y);
		              
		              stage.setOpacity(1);
		           }

		         });
		         
		         root.setOnMouseReleased(new EventHandler<MouseEvent>(){
		           @Override
		           public void handle(MouseEvent event) {
		              stage.setOpacity(1);
		           }

		         });
		         
		         
		         
		         
		         
				
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        stage.setFullScreenExitHint("");
        
       stage.setScene(scene);
        stage.show();
   }
}