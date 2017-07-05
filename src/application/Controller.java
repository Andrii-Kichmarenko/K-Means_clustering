package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import application.Model;

public class Controller {

	Model model = new Model();

	@FXML
	Pane plotPane;
	@FXML
	TextField groupRadiusTextField;
	@FXML
	TextField pointsNumberTextField;
	@FXML
	TextField groupsNumberTextField;

	@FXML
	Button startButton;
	@FXML
	Button nextButton;
	@FXML
	Button clearButton;


	@FXML
	public void mouseClickAction(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			model.addGroup(event.getX(), event.getY(), Integer.valueOf(groupRadiusTextField.getText()),
					Integer.valueOf(pointsNumberTextField.getText()), plotPane);
			startButton.setDisable(false);
		}
	}
	
	@FXML
	public void startButtonAction(MouseEvent event) throws NumberFormatException, Exception {
		model.addRectangles(Integer.valueOf(groupsNumberTextField.getText()), plotPane);
		model.colorPoints();
		mode1();
	}
	
	@FXML
	public void nextButtonAction(MouseEvent event) {
		boolean refreshed = model.countCentroids(Integer.valueOf(groupsNumberTextField.getText()));
		if(!refreshed){
			mode2();
		}
	}
	
	@FXML
	public void clearButtonAction(MouseEvent event){
		model.clearAll(plotPane);
		mode3();
	}
	
	public void mode1(){
		//set disable
		groupRadiusTextField.setDisable(true);
		pointsNumberTextField.setDisable(true);
		groupsNumberTextField.setDisable(true);
		startButton.setDisable(true);
		//set active
		nextButton.setDisable(false);
		clearButton.setDisable(false);
	}
	public void mode2(){
		//set disable
		groupRadiusTextField.setDisable(true);
		pointsNumberTextField.setDisable(true);
		groupsNumberTextField.setDisable(true);
		startButton.setDisable(true);
		nextButton.setDisable(true);
		//set active
		clearButton.setDisable(false);
	}
	public void mode3(){
		//set disable
		nextButton.setDisable(true);
		clearButton.setDisable(true);
		startButton.setDisable(true);
		//set active
		groupRadiusTextField.setDisable(false);
		pointsNumberTextField.setDisable(false);
		groupsNumberTextField.setDisable(false);
	}

}
