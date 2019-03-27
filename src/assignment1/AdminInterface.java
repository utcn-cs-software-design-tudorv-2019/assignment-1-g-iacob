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



public class AdminInterface {

	public AdminInterface() {
		OperationRepo repo = new OperationRepo();
		
		Stage secondStage = new Stage();
		
		BorderPane pane = new BorderPane();
		
		VBox menu = new VBox();
		menu.setSpacing(8);
		menu.setPrefWidth(250);
		menu.setPrefHeight(150);
		Button btnCreateStudent = new Button("Create Student");
		Button btnViewStudents = new Button("View Students");
		Button btnUpdateStudent = new Button("Update Student");
		Button btnDeleteStudent = new Button("Delete Student");
		Button btnReport = new Button("Generate Report");
		Button btnReset = new Button("Reset DB");
		btnCreateStudent.setMinWidth(menu.getPrefWidth());
		btnViewStudents.setMinWidth(menu.getPrefWidth());
		btnUpdateStudent.setMinWidth(menu.getPrefWidth());
		btnDeleteStudent.setMinWidth(menu.getPrefWidth());
		btnReport.setMinWidth(menu.getPrefWidth());
		btnReset.setMinWidth(menu.getPrefWidth());
		menu.getChildren().addAll(btnCreateStudent, btnViewStudents, btnUpdateStudent, btnDeleteStudent, btnReport, btnReset);
		
		pane.setLeft(menu);
		
		// Create Button
		
		GridPane menuCreate = new GridPane();
		menuCreate.setAlignment(Pos.CENTER);
		menuCreate.setHgap(10);
		menuCreate.setVgap(10);
		menuCreate.setPadding(new Insets(25, 25, 25, 25));
		
		menuCreate.add(new Label("Name*:"), 0, 1);

		TextField nameCreateTextField = new TextField();
		menuCreate.add(nameCreateTextField, 1, 1);

		menuCreate.add(new Label("CNP:"), 0, 2);

		TextField cnpCreateTextField = new TextField();
		menuCreate.add(cnpCreateTextField, 1, 2);
		
		menuCreate.add(new Label("Group:"), 0, 3);

		TextField groupCreateTextField = new TextField();
		menuCreate.add(groupCreateTextField, 1, 3);
		
		menuCreate.add(new Label("* is mandatory"), 0, 4);

		Button btnCreateConfirm = new Button("GO");
		menuCreate.add(btnCreateConfirm, 2, 4);
		
		btnCreateConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	repo.insertStudent(nameCreateTextField.getText(), cnpCreateTextField.getText(), groupCreateTextField.getText());
		    }
		});
		
		btnCreateStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuCreate);
		    }
		});
		
		// View Button
		
		btnViewStudents.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	TableView viewMenu = new TableView();
		    	TableColumn viewID = new TableColumn("ID");
		    	viewID.setCellValueFactory(new PropertyValueFactory<>("id"));
		    	TableColumn viewName = new TableColumn("Name");
		    	viewName.setCellValueFactory(new PropertyValueFactory<>("name"));
		    	TableColumn viewCNP = new TableColumn("CNP");
		    	viewCNP.setCellValueFactory(new PropertyValueFactory<>("cnp"));
		    	TableColumn viewGroup = new TableColumn("Group");
		    	viewGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
		    	
		    	viewMenu.getColumns().addAll(viewID, viewName, viewCNP, viewGroup);
		    	
		    	ArrayList<Student> students = repo.getStudents();
		    	for (Student student : students) {
		    		viewMenu.getItems().add(student);
		    	}
		    	
		    	pane.setCenter(viewMenu);
		    }
		});
		
		// Update Button
		
		GridPane menuUpdate = new GridPane();
		menuUpdate.setAlignment(Pos.CENTER);
		menuUpdate.setHgap(10);
		menuUpdate.setVgap(10);
		menuUpdate.setPadding(new Insets(25, 25, 25, 25));
		
		menuUpdate.add(new Label("ID*:"), 0, 1);

		TextField idUpdateTextField = new TextField();
		menuUpdate.add(idUpdateTextField, 1, 1);
		
		menuUpdate.add(new Label("Name:"), 0, 2);

		TextField nameUpdateTextField = new TextField();
		menuUpdate.add(nameUpdateTextField, 1, 2);

		menuUpdate.add(new Label("CNP:"), 0, 3);

		TextField cnpUpdateTextField = new TextField();
		menuUpdate.add(cnpUpdateTextField, 1, 3);
		
		menuUpdate.add(new Label("Group:"), 0, 4);

		TextField groupUpdateTextField = new TextField();
		menuUpdate.add(groupUpdateTextField, 1, 4);
		
		menuUpdate.add(new Label("* is mandatory"), 0, 5);

		Button btnUpdateConfirm = new Button("GO");
		menuUpdate.add(btnUpdateConfirm, 2, 5);
		
		btnUpdateConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	repo.updateStudent(idUpdateTextField.getText(), nameUpdateTextField.getText(), cnpUpdateTextField.getText(), groupUpdateTextField.getText());
		    }
		});
		
		btnUpdateStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuUpdate);
		    }
		});
		
		// Delete Button
		
		GridPane menuDelete = new GridPane();
		menuDelete.setAlignment(Pos.CENTER);
		menuDelete.setHgap(10);
		menuDelete.setVgap(10);
		menuDelete.setPadding(new Insets(25, 25, 25, 25));
		
		menuDelete.add(new Label("Delete ID:"), 0, 1);

		TextField deleteTextField = new TextField();
		menuDelete.add(deleteTextField, 1, 1);
		
		Button btnDeleteConfirm = new Button("GO");
		
		btnDeleteConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	repo.deleteStudent(deleteTextField.getText());
		    }
		});
		
		menuDelete.add(btnDeleteConfirm, 2, 1);
		
		btnDeleteStudent.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuDelete);
		    }
		});
		
		// Generate Report
		
		GridPane menuReport = new GridPane();
		menuReport.setAlignment(Pos.CENTER);
		menuReport.setHgap(10);
		menuReport.setVgap(10);
		menuReport.setPadding(new Insets(25, 25, 25, 25));
		
		menuReport.add(new Label("Student ID:"), 0, 1);

		TextField reportTextField = new TextField();
		menuReport.add(reportTextField, 1, 1);
		
		Button btnReportConfirm = new Button("GO");
		
		btnReportConfirm.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	int cnt = 1;
		    	GridPane log = new GridPane();
	    		log.setAlignment(Pos.CENTER);
	    		log.setHgap(10);
	    		log.setVgap(10);
	    		log.setPadding(new Insets(25, 25, 25, 25));
		    	for (String action : repo.generateLog(reportTextField.getText())) {
		    		log.add(new Label(action), 1, cnt);
		    		cnt++;
		    	}
		    	pane.setCenter(log);
		    		
		    }
		});
		
		menuReport.add(btnReportConfirm, 2, 1);
		
		btnReport.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(menuReport);
		    }
		});
		
		// Reset DB
		
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	pane.setCenter(new VBox());
		    	repo.resetDB();
		    }
		});
		
		Scene scene = new Scene(pane, 1280, 960);
		secondStage.setScene(scene);
        secondStage.show();
	}

}
