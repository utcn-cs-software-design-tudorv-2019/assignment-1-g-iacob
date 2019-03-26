package assignment1;

import java.util.ArrayList;

public class OperationRepo {
	
	//TODO: HashMap<Student, String> ---> student logs
	
	DatabaseController dbController = new DatabaseController();
	
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
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!cnp.isEmpty())
			try {
				dbController.updateCNP(idInt, Integer.parseInt(cnp));
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		if (!gr.isEmpty())
			try {
				dbController.updateGroup(idInt, Integer.parseInt(gr));
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
	}
	
	public ArrayList<Student> getStudents() {
		return dbController.getStudents();
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
		dbController.initDB();
	}

}
