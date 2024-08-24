package application.scenes.solo_play;

import java.awt.GraphicsEnvironment;
import java.io.IOException;

import application.lib.StringSettings;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SPGController {
	
	@FXML
	private VBox vboxMain;
	
	@FXML
	private TextArea draftArea;
	
	@FXML 
	private TextField answerField;
	
	@FXML
	private Text equation;
	
	@FXML
	private StackPane titleLocation;
	
	
	@FXML
    public void initialize() {
		draftArea.setOnKeyPressed(this::handleKeyForDraft);
		answerField.setOnKeyPressed(this::handleKeyForAnswer);
		System.out.println(SoloPlayModeHandler.mode + "SPG");
		setEquationText();
		
		
    }
	
	public void showToast(Stage ownerStage, String message, double x, double y) {
        Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        
        Label label = new Label(message);
        label.setStyle("-fx-background-color: #776544; -fx-text-fill: #ffdfa3; -fx-padding: 15px;");
        
        popup.getContent().add(label);
        
        popup.setX(x);
        popup.setY(y);
        
        popup.show(ownerStage);
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> popup.hide());
        delay.play();
    }
	
	private void handleKeyForDraft(KeyEvent event) {
		if (event.isAltDown() && (event.getCode() == KeyCode.E)) {
			System.out.println("alt + E");
			answerField.requestFocus();
		}
		
	}
	
	private void handleKeyForAnswer(KeyEvent event) {
		if (event.isAltDown() && (event.getCode() == KeyCode.Q)) {
			System.out.println("alt + Q");
			draftArea.requestFocus();
		}
		if (event.getCode() == KeyCode.ENTER) {
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			String text = answerField.getText();
			double answer = Double.parseDouble(text);
			if (answer == result) {
				text = "True";
			}
			else {
				text = "False, true is " + result;
			}
			
			showToast((Stage) source.getScene().getWindow(), text, stage.getX() + 275, stage.getY() + 25);
			String[] arr = StringSettings.generate_equation(1, 100, 1, 100, "+", lastNum1, lastNum2);
			lastNum1 = Integer.parseInt(arr[2]);
			lastNum1 = Integer.parseInt(arr[3]);
			result = Double.parseDouble(arr[1]);
			equation.setText(arr[0]);
		}
	}
	
	private int lastNum1 = 0;
	private int lastNum2 = 0;
	private double result = 0;
	
	private void setEquationText() {
		switch (SoloPlayModeHandler.mode) {
			case "m1":
				String[] arr = StringSettings.generate_equation(1, 100, 1, 100, "+", lastNum1, lastNum2);
				lastNum1 = Integer.parseInt(arr[2]);
				lastNum1 = Integer.parseInt(arr[3]);
				result = Double.parseDouble(arr[1]);
				equation.setText(arr[0]);
				break;
			case "m2":
				String[] arr1 = StringSettings.generate_equation(1, 100, 100, 1000, "+", lastNum1, lastNum2);
				lastNum1 = Integer.parseInt(arr1[2]);
				lastNum1 = Integer.parseInt(arr1[3]);
				result = Double.parseDouble(arr1[1]);
				System.out.println("Nigga");
				equation.setText(arr1[0]);
				break;
		}
	}
	
	/*
	 * start buttons
	 */
	
	@FXML
	private void btnDelReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			draftArea.setText(StringSettings.removeLastFromText(text));
			break;
		case "answerField":
			text = answerField.getText();
			answerField.setText(StringSettings.removeLastFromText(text));
			break;
			
		}
	}
	
	
	
	@FXML
	private void btnDivReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "/";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "/";
			answerField.setText(text);
			break;
			
		}
		
	}
	@FXML
	private void btnMultReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "*";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "*";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnSubReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "-";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "-";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnSumReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "+";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "+";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnSevenReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "7";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "7";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnEightReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "8";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "8";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnNineReleased(MouseEvent event) {
		System.out.println("9");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "9";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "9";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnFourReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "4";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "4";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnFiveReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "5";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "5";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnSixReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "6";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "6";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnOneReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "1";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "1";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnTwoReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "2";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "2";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnThreeReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "3";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "3";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnZeroReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += "0";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += "0";
			answerField.setText(text);
			break;
		}
	}
	@FXML
	private void btnEnterReleased(MouseEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		String text = answerField.getText();
		double answer = Double.parseDouble(text);
		if (answer == result) {
			text = "True";
		}
		else {
			text = "False, true is " + result;
		}
		showToast((Stage) source.getScene().getWindow(), text, stage.getX() + 275, stage.getY() + 25);
		String[] arr = StringSettings.generate_equation(1, 100, 1, 100, "+", lastNum1, lastNum2);
		lastNum1 = Integer.parseInt(arr[2]);
		lastNum1 = Integer.parseInt(arr[3]);
		result = Double.parseDouble(arr[1]);
		equation.setText(arr[0]);
	}
	@FXML
	private void btnDotReleased(MouseEvent event) {
		System.out.println("text");
		Node source = (Node) event.getSource();
		Scene scene = source.getScene();
		String focus = scene.getFocusOwner().getId();
		
		String text = "";
		switch (focus) {
		case "draftArea":
			text = draftArea.getText();
			text += ".";
			draftArea.setText(text);
			break;
		case "answerField":
			text = answerField.getText();
			text += ".";
			answerField.setText(text);
			break;
		}
	}
	
	/*
	 * finish of buttons
	 */
	
	
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
		System.out.println("test2");
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
		System.out.println("test2");
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
	
	@FXML
	private void btnBackReleased(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
		Parent root = loader.load();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	
}
