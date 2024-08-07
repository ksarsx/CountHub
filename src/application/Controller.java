package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {
	
	/*
	 * title buttons
	 */
	
	@FXML
	private void btnCloseReleased(MouseEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void btnFullScreenReleased(MouseEvent event) {
		System.out.println("````");
	}
	
	@FXML
	private void btnHideReleased(MouseEvent event) {
		System.out.println("````");
	}
	
	/*
	 * finish of title buttons
	 */
	
}
