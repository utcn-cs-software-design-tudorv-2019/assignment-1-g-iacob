package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;

import crossCuttingConcerns.Course;
import crossCuttingConcerns.Enrolment;
import crossCuttingConcerns.Student;
import dataLayer.CourseDAO;
import dataLayer.EnrolmentDAO;
import dataLayer.StudentDAO;

public class AdminOperations {
	
	StudentDAO studentOperations = new StudentDAO();
	EnrolmentDAO enrolmentOperations = new EnrolmentDAO();
	CourseDAO courseOperations = new CourseDAO();
	OperationLogger logger = new OperationLogger();
	
	public ArrayList<String> generateLog(String id){
    	if (id.isEmpty())
    		return new ArrayList<String>();
    	try {
    		if (logger.getLog().get(Integer.parseInt(id)) == null)
    			return new ArrayList<String>();
    		else
    			return logger.getLog().get(Integer.parseInt(id));
    	} catch (Exception e) {
    		System.out.println(e);
    		return new ArrayList<String>();
    	}
    	
    }
	
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
	
	public ArrayList<Student> getStudents() {
		return studentOperations.getStudents();
	}
	
	public int isValidLogin(String name) {
		for (Student student : getStudents()) {
			if (student.name.equals(name))
				return student.id;
		}
		return 0;
	}
	
	public Student getStudent(int id) {
		return studentOperations.getStudent(id);
	}
	
	public void resetDB() {
		logger.reset();
		studentOperations.initDB();
	}
}
