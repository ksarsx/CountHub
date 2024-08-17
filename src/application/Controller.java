package application;



import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
	
	private double lastHeight = 400;
	private double lastWidth = 600;
	private double lastX = 0;
	private double lastY = 0;
	
	@FXML
	private void btnFullScreenReleased(MouseEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		
		System.out.println(stage.getWidth() + " " + stage.getHeight());
		double x = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getMaxX();
		double y = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getMaxY();
		System.out.println(x + " " + y);
		
		if ((stage.getHeight() == y) && (stage.getWidth() == x)) {
			stage.setHeight(lastHeight);
			stage.setWidth(lastWidth);
			stage.setX(lastX);
			stage.setY(lastY);
		}
		else {
			lastHeight = stage.getHeight();
			lastWidth = stage.getWidth();
			lastX = stage.getX();
			lastY = stage.getY();
			stage.setHeight(y);
			stage.setWidth(x);
			stage.setX(0);
			stage.setY(0);
		}
	}
	
	@FXML
	private void btnHideReleased(MouseEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setIconified(true);
	}
	
	/*
	 * finish of title buttons
	 */
	
	
	
	/*
	 * title bar drag label settings
	 */
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	@FXML
	private void onMouseEnteredToTitleBar(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		
		parent.setOnMousePressed(null);
	}
	
	@FXML
	public void onMousePressedToTitle(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});
	}
	
	@FXML
	public void onMouseReleasedFromTitle(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
			
		parent.setOnMousePressed(null);
		parent.setOnMouseDragged(null);
	}
	/*
	 * end of title bar drag label
	 */
	
	
	
	
	
	/*
	 * drag borders 
	 */
	
	@FXML
	private void onBorderReleased(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		System.out.println("out");
		parent.setOnMousePressed(null);
		parent.setOnMouseDragged(null);
	}
	
	private double Y = 0;
	private double deltaY = 0;
	private double X = 0;
	private double deltaX = 0;
	
	private double getWidth(Stage stage) {
		return stage.getWidth();
	}
	private double getHeight(Stage stage) {
		return stage.getHeight();
	}
	
	@FXML
	private void onBorderTopLeftPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Y = event.getSceneY();
				X = event.getSceneX();
			}
		});
		
		parent.setOnMouseDragged(new EventHandler<MouseEvent> () {
			@Override 
			public void handle(MouseEvent event) {
				deltaY = (stage.getY() - (event.getScreenY() - Y));
				deltaX = (stage.getX() - (event.getScreenX() - X));
				if ((getHeight(stage) + deltaY >= 300)) {
					
					System.out.println(deltaX);
					stage.setHeight(stage.getHeight() + deltaY);
					
					stage.setY(stage.getY() - deltaY);
					
					//System.out.println(newHeight + "new Height");
				}
				if (getWidth(stage) + deltaX >= 360) {
					stage.setWidth(stage.getWidth() + deltaX);
					stage.setX(stage.getX() - deltaX);
				}
			}
		});
	}
	

	
	@FXML
	private void onBorderTopPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				Y = event.getSceneY();
			}
		});
		
		parent.setOnMouseDragged(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				deltaY = stage.getY() - (event.getScreenY() - Y);
					if ((getHeight(stage) + deltaY >= 300)) {
						stage.setHeight(stage.getHeight() + deltaY);
						stage.setY(stage.getY() - deltaY);
						//System.out.println(newHeight + "new Height");
				}
			}
		});
	}
	
	@FXML 
	private void onBorderTopRightPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				X = event.getSceneX() - stage.getWidth();
				Y = event.getSceneY();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				deltaY = (stage.getY() - (event.getScreenY() - Y));
				deltaX = -(stage.getX() - (event.getScreenX() - X) + stage.getWidth());
				
				if (getHeight(stage) + deltaY >= 300) {
					stage.setHeight(stage.getHeight() + deltaY);
					stage.setY(stage.getY() - deltaY);
				}
				if (getWidth(stage) + deltaX >= 360) {
					System.out.println(deltaX);
					stage.setWidth(stage.getWidth() + deltaX);
				}
			}
		});
	}
	
	@FXML 
	private void onBorderLeftPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				X = event.getSceneX();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				deltaX = (stage.getX() - (event.getScreenX() - X));
				if (getWidth(stage) + deltaX >= 360) {
					stage.setWidth(stage.getWidth() + deltaX);
					stage.setX(stage.getX() - deltaX);
				}
			}
		});
	}
	
	@FXML
	private void onBorderRightPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				X = event.getSceneX() - stage.getWidth();
				System.out.println(X);
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				deltaX = -(stage.getX() - (event.getScreenX() - X) + stage.getWidth());
				if (getWidth(stage) + deltaX >= 360) {
					System.out.println(deltaX);
					stage.setWidth(stage.getWidth() + deltaX);
					//stage.setX(stage.getX() - deltaX);
				}
			}
		});
	}
	
	
	@FXML
	private void onBorderBottomLeftPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				X = event.getSceneX();
				Y = event.getSceneY() - stage.getHeight();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				deltaY = -(stage.getY() - (event.getScreenY() - Y) + stage.getHeight());
				deltaX = (stage.getX() - (event.getScreenX() - X));
				
				if (getHeight(stage) + deltaY >= 300) {
					System.out.println(deltaY);
					stage.setHeight(stage.getHeight() + deltaY);
				}
				if (getWidth(stage) + deltaX >= 360) {
					System.out.println(deltaX);
					stage.setWidth(stage.getWidth() + deltaX);
					stage.setX(stage.getX() - deltaX);
				}
			}
		});
	}
	
	@FXML
	private void onBorderBottomPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Y = event.getSceneY() - stage.getHeight();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				deltaY = -(stage.getY() - (event.getScreenY() - Y) + stage.getHeight());
				if (getHeight(stage) + deltaY >= 300) {
					System.out.println(deltaY);
					stage.setHeight(stage.getHeight() + deltaY);
				}
			}
		});
	}
	
	@FXML
	private void onBorderBottomRightPressed(MouseEvent event) {
		Node source = (Node) event.getSource();
		Parent parent = source.getParent();
		Stage stage = (Stage) source.getScene().getWindow();
		
		parent.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				X = event.getSceneX() - stage.getWidth();
				Y = event.getSceneY() - stage.getHeight();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				deltaY = -(stage.getY() - (event.getScreenY() - Y) + stage.getHeight());
				deltaX = -(stage.getX() - (event.getScreenX() - X) + stage.getWidth());
				if (getHeight(stage) + deltaY >= 300) {
					System.out.println(deltaY);
					stage.setHeight(stage.getHeight() + deltaY);
					stage.setY(stage.getY());
				}
				if (getWidth(stage) + deltaX >= 360) {
					System.out.println(deltaX);
					stage.setWidth(stage.getWidth() + deltaX);
					
					stage.setX(stage.getX());
				}
			}
		});
	}
	/*
	 * finish of drag borders
	 */
	
	
	
	/*
	 * switch scene buttons
	 */
	@FXML
	private void btnSettingsReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settings/settings.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnMathFightReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/math_fight/math_fight.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnProfilReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/profil/profil.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnRatingReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/rating/rating.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnSoloPlayReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/solo_play/solo_play.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnSoloTimePlayReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/solo_time_play/solo_time_play.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void btnTutorialsReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/tutorials/tutorials.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		
		Scene scene = new Scene(root,stage.getWidth(), stage.getHeight()); // withaout it have a grafick error
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	/*
	 * finish of switch scene buttons
	 */
}
