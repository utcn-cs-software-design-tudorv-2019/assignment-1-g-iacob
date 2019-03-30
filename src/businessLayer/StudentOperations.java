package businessLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import crossCuttingConcerns.Course;
import crossCuttingConcerns.Enrolment;
import crossCuttingConcerns.Student;
import dataLayer.CourseDAO;
import dataLayer.EnrolmentDAO;
import dataLayer.StudentDAO;

public class StudentOperations {

	StudentDAO studentOperations = new StudentDAO();
	EnrolmentDAO enrolmentOperations = new EnrolmentDAO();
	CourseDAO courseOperations = new CourseDAO();
	OperationLogger logger = new OperationLogger();
	

	public void insertStudent(String name, String cnp, String gr) {
		if (name.isEmpty()) {
			System.out.println("Error - Empty name.");
			return;
		} else {
			Student toBeInserted = new Student(name);
			if (!cnp.isEmpty())
				try {
					toBeInserted.cnp = Integer.parseInt(cnp);
				} catch (Exception e) {
					System.out.println(e);
					return;
				}
			if (!gr.isEmpty())
				try {
					toBeInserted.gr = Integer.parseInt(gr);
				} catch (Exception e) {
					System.out.println(e);
					return;
				}
			studentOperations.insertStudent(toBeInserted);
		}
	}
	
	public void enrolStudent(Integer studentID, String courseID) {
		if (courseID.isEmpty())
			return;
		try {
			if (logger.getLog().get(studentID) != null)
				if (logger.getLog().get(studentID).contains("Enroled to course with id " + courseID))
					return; // no duplicates
			enrolmentOperations.insertEnrolment(studentID, Integer.parseInt(courseID), ThreadLocalRandom.current().nextInt(4, 9));
			logger.logAction(studentID, "Enroled to course with id " + courseID);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public void deleteStudent(String id) {
		int idInt;
		if (id.isEmpty())
			return;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
		studentOperations.deleteStudent(idInt);
	}
	
	public void updateStudent(String id, String name, String cnp, String gr) {
		int idInt;
		if (id.isEmpty())
			return;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
		
		if (!name.isEmpty())
			try {
				studentOperations.updateName(idInt, name);
				logger.logAction(idInt, "Updated name to " + name);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!cnp.isEmpty())
			try {
				studentOperations.updateCNP(idInt, Integer.parseInt(cnp));
				logger.logAction(idInt, "Updated cnp to " + cnp);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!gr.isEmpty())
			try {
				studentOperations.updateGroup(idInt, Integer.parseInt(gr));
				logger.logAction(idInt, "Updated group to " + gr);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
	}
	
	public ArrayList<Enrolment> getEnrolments(Integer studentID) {
		return enrolmentOperations.getEnrolments(studentID);
	}
	
	public ArrayList<Course> getCourses() {
        return courseOperations.getCourses();
    }
	public Student getStudent(int id) {
		return studentOperations.getStudent(id);
	}
    
}
