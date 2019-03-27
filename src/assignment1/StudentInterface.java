package assignment1;

import java.util.ArrayList;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;

import javafx.geometry.*;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class StudentInterface {

	public StudentInterface(Integer id) {
		OperationRepo repo = new OperationRepo();
		
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
		
    	Student self = repo.getStudent(id);
    	if (self == null)
    		System.out.println("Null student");
    	GridPane viewSelf = new GridPane();
    	viewSelf.setAlignment(Pos.CENTER);
    	viewSelf.setHgap(10);
    	viewSelf.setVgap(10);
    	viewSelf.setPadding(new Insets(25, 25, 25, 25));
    	
    	viewSelf.add(new Label("Name: " + self.name), 1, 1);
    	viewSelf.add(new Label("CNP: " + self.cnp.toString()), 1, 2);
    	viewSelf.add(new Label("Group: " + self.gr.toString()), 1, 3);
		
    	btnViewStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(viewSelf);
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
		    	repo.updateStudent(id.toString(), nameUpdateTextField.getText(), cnpUpdateTextField.getText(), groupUpdateTextField.getText());
		    }
		});
		
		btnUpdateStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuUpdate);
		    }
		});
		
		// View Courses
		
		btnViewCourses.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	TableView viewMenu = new TableView();
                TableColumn viewID = new TableColumn("ID");
                viewID.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn viewCourseName = new TableColumn("Course Name");
                viewCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
                TableColumn viewTeacherName = new TableColumn("Teacher Name");
                viewTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
                TableColumn viewExamDate = new TableColumn("Exam Date");
                viewExamDate.setCellValueFactory(new PropertyValueFactory<>("examDate"));
               
                viewMenu.getColumns().addAll(viewID, viewCourseName, viewTeacherName, viewExamDate);
               
                ArrayList<Course> courses = repo.getCourses();
                for (Course course : courses) {
                    viewMenu.getItems().add(course);
                }
               
                pane.setCenter(viewMenu);
		    }
		});
		
		// View Grades
		
		btnViewGrades.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	TableView viewMenu = new TableView();
                TableColumn viewCourseName = new TableColumn("Course Name");
                viewCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
                TableColumn viewGrade = new TableColumn("Grade");
                viewGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
               
                viewMenu.getColumns().addAll(viewCourseName, viewGrade);
               
                ArrayList<Enrolment> enrolments = repo.getEnrolments(id);
                for (Enrolment enrolment : enrolments) {
                    viewMenu.getItems().add(enrolment);
                }
               
                pane.setCenter(viewMenu);
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
		    	repo.enrolStudent(id, enrollTextField.getText());
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
