package EVote;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	 private double x =0;
	 private double y=0;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			String css= this.getClass().getResource("Main.css").toExternalForm();
			
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
		              primaryStage.setX(event.getScreenX()-x);
		              primaryStage.setY(event.getScreenY()-y);
		              
		              primaryStage.setOpacity(1);
		           }

		         });
		         
		         root.setOnMouseReleased(new EventHandler<MouseEvent>(){
		           @Override
		           public void handle(MouseEvent event) {
		              primaryStage.setOpacity(1);
		           }

		         });
			
			
			
			Scene scene = new Scene(root);
	
			scene.getStylesheets().add(css);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			primaryStage.setFullScreenExitHint("");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
