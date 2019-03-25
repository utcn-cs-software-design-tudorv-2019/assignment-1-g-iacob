package assignment1;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		menu.setPrefWidth(250);
		menu.setPrefHeight(150);
		Button btnViewStudent = new Button("View Profile");
		Button btnUpdateStudent = new Button("Update Profile");
		Button btnViewCourses = new Button("View Courses");
		Button btnViewGrades = new Button("View Grades");
		Button btnEnroll = new Button("Enroll");
		
		btnViewStudent.setMinWidth(menu.getPrefWidth());
		btnUpdateStudent.setMinWidth(menu.getPrefWidth());
		btnViewCourses.setMinWidth(menu.getPrefWidth());
		btnViewGrades.setMinWidth(menu.getPrefWidth());
		btnEnroll.setMinWidth(menu.getPrefWidth());
		menu.getChildren().addAll(btnViewStudent, btnUpdateStudent, btnViewCourses, btnViewGrades, btnEnroll);
		
		pane.setLeft(menu);
		
		// View self
		
		btnViewStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: get info from business
		    }
		});
		
		// Update self
		
		GridPane menuUpdate = new GridPane();
		menuUpdate.setAlignment(Pos.CENTER);
		menuUpdate.setHgap(10);
		menuUpdate.setVgap(10);
		menuUpdate.setPadding(new Insets(25, 25, 25, 25));
		
		menuUpdate.add(new Label("Name:"), 0, 1);

		TextField nameUpdateTextField = new TextField();
		menuUpdate.add(nameUpdateTextField, 1, 1);

		menuUpdate.add(new Label("CNP:"), 0, 2);

		TextField cnpUpdateTextField = new TextField();
		menuUpdate.add(cnpUpdateTextField, 1, 2);
		
		menuUpdate.add(new Label("Group:"), 0, 3);

		TextField groupUpdateTextField = new TextField();
		menuUpdate.add(groupUpdateTextField, 1, 3);

		Button btnUpdateConfirm = new Button("GO");
		menuUpdate.add(btnUpdateConfirm, 2, 4);
		
		btnUpdateConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: send text fields to business layer
		    }
		});
		
		btnUpdateStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuUpdate);
		    }
		});
		
		// View Courses
		
		btnViewStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: get info from business
		    }
		});
		
		// View Grades
		
		btnViewStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: get info from business
		    }
		});
		
		// Enroll
		
		GridPane menuEnroll = new GridPane();
		menuEnroll.setAlignment(Pos.CENTER);
		menuEnroll.setHgap(10);
		menuEnroll.setVgap(10);
		menuEnroll.setPadding(new Insets(25, 25, 25, 25));
		
		menuEnroll.add(new Label("Course ID:"), 0, 1);

		TextField enrollTextField = new TextField();
		menuEnroll.add(enrollTextField, 1, 1);
		
		Button btnEnrollConfirm = new Button("GO");
		
		btnEnrollConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: send enrollTextField.getText() to business layer
		    }
		});
		
		menuEnroll.add(btnEnrollConfirm, 2, 1);
		
		btnEnroll.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuEnroll);
		    }
		});
		
		Scene scene = new Scene(pane, 1280, 960);
		secondStage.setScene(scene);
        secondStage.show();
	}

}
