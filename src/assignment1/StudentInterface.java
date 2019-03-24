package assignment1;

import javafx.application.*;

import javafx.stage.*;

import javafx.geometry.*;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class StudentInterface {

	public StudentInterface(String name) {
		Stage secondStage = new Stage();
		
		BorderPane pane = new BorderPane();
		
		VBox menu = new VBox();
		menu.setSpacing(8);
		Button btnViewStudent = new Button("View Profile");
		Button btnViewCourses = new Button("View Courses");
		Button btnUpdateStudent = new Button("Update Profile");
		Button btnEnrol = new Button("Enrol");
		menu.getChildren().add(btnViewStudent);
		menu.getChildren().add(btnViewCourses);
		menu.getChildren().add(btnUpdateStudent);
		menu.getChildren().add(btnEnrol);
		pane.setLeft(menu);
		
		Scene scene = new Scene(pane, 300, 275);
		secondStage.setScene(scene);
        secondStage.show();
	}

}
