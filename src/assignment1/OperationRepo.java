package assignment1;

import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class OperationRepo {
	
	DatabaseController dbController = new DatabaseController();
	HashMap<Integer, ArrayList<String>> log = new HashMap<Integer, ArrayList<String>>();
	 
	private void serializeLog() {
		try {
			FileOutputStream file = new FileOutputStream("studentsLog");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(log);
			out.close(); 
            file.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace()[0].getLineNumber());
		}
	}
	
	private void deserializeLog() {
		try {
			FileInputStream file = new FileInputStream("studentsLog");
			ObjectInputStream in = new ObjectInputStream(file);
			log = (HashMap<Integer, ArrayList<String>>)in.readObject();
			in.close(); 
            file.close();
		} catch (Exception e) {
			System.out.println(e);
			log = new HashMap<Integer, ArrayList<String>>();
		}
	}
	
    public void logAction(Integer id, String action) {
        ArrayList<String> actionList = log.get(id);
        if (actionList == null){
            actionList = new ArrayList<String>();
            actionList.add(action);
            log.put(id, actionList);
        } else {
            actionList.add(action);
        }
        serializeLog();
    }
    
    public ArrayList<String> generateLog(String id){
    	if (id.isEmpty())
    		return new ArrayList<String>();
    	deserializeLog();
    	try {
    		if (log.get(Integer.parseInt(id)) == null)
    			return new ArrayList<String>();
    		else
    			return log.get(Integer.parseInt(id));
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
			dbController.insertStudent(toBeInserted);
		}
	}
	
	public void enrolStudent(Integer studentID, String courseID) {
		if (courseID.isEmpty())
			return;
		try {
			deserializeLog();
			if (log.get(studentID) != null)
				if (log.get(studentID).contains("Enroled to course with id " + courseID))
					return; // no duplicates
			dbController.insertEnrolment(studentID, Integer.parseInt(courseID), ThreadLocalRandom.current().nextInt(4, 9));
			logAction(studentID, "Enroled to course with id " + courseID);
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
		dbController.deleteStudent(idInt);
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
				dbController.updateName(idInt, name);
				logAction(idInt, "Updated name to " + name);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!cnp.isEmpty())
			try {
				dbController.updateCNP(idInt, Integer.parseInt(cnp));
				logAction(idInt, "Updated cnp to " + cnp);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!gr.isEmpty())
			try {
				dbController.updateGroup(idInt, Integer.parseInt(gr));
				logAction(idInt, "Updated group to " + gr);
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
	}
	
	public ArrayList<Student> getStudents() {
		return dbController.getStudents();
	}
	
	public ArrayList<Enrolment> getEnrolments(Integer studentID) {
		return dbController.getEnrolments(studentID);
	}
	
	public ArrayList<Course> getCourses() {
        return dbController.getCourses();
    }
	
	public int isValidLogin(String name) {
		for (Student student : getStudents()) {
			if (student.name.equals(name))
				return student.id;
		}
		return 0;
	}
	
	public Student getStudent(int id) {
		return dbController.getStudent(id);
	}
	
	public void resetDB() {
		log = new HashMap<Integer, ArrayList<String>>();
		serializeLog();
		dbController.initDB();
	}

}
