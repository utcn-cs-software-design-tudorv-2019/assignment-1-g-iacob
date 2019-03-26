package assignment1;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
	
	private Connection con = null;
	private Statement stat = null;
	private PreparedStatement pStat = null;
	private ResultSet resSet = null;
	
	public DatabaseController() {
		connect();
	}
	
	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/assignment1", "root", "1234");
		} catch (Exception e) {
			System.out.println(e);
			close();
		}
	}
	
	public void close() {
		System.out.println("Closing DB connection.");
		try {
			if (con != null)
				con.close();
			if (stat != null)
				stat.close();
			if (resSet != null)
				resSet.close();
			pStat = null;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void insertStudent(Student student) {
		try {
			pStat = con.prepareStatement("insert into student (name, cnp, gr) values (?, ?, ?)");
			
			pStat.setString(1, student.name);
			if (student.cnp != null)
				pStat.setInt(2, student.cnp);
			else
				pStat.setNull(2, Types.INTEGER);
			if (student.gr != null)
				pStat.setInt(3, student.gr);
			else
				pStat.setNull(3, Types.INTEGER);
			
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteStudent(int id) {
		try {
			pStat = con.prepareStatement("delete from student where student_id = ?;");
			pStat.setInt(1, id);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void updateName(int id, String name) {
		try {
			pStat = con.prepareStatement("update student set name = ? where student_id = ?;");
			pStat.setString(1, name);
			pStat.setInt(2, id);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateCNP(int id, int cnp) {
		try {
			pStat = con.prepareStatement("update student set cnp = ? where student_id = ?;");
			pStat.setInt(1, cnp);
			pStat.setInt(2, id);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateGroup(int id, int gr) {
		try {
			pStat = con.prepareStatement("update student set gr = ? where student_id = ?;");
			pStat.setInt(1, gr);
			pStat.setInt(2, id);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Student> getStudents(){
		ArrayList<Student> rtn = new ArrayList<Student>();
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select * from student;");
			while (resSet.next()) {
				rtn.add(new Student(resSet.getInt("student_id"), resSet.getString("name"),
						resSet.getInt("cnp"), resSet.getInt("gr")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return rtn;
	}
	
	public Student getStudent(Integer id) {
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select * from student where student_id = " + id.toString() + ";");
			if (resSet.next())
				return new Student(resSet.getString("name"), resSet.getInt("cnp"), resSet.getInt("gr"));
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void initDB() {
		try {
			stat = con.createStatement();
			stat.execute("delete from enrolment;");
			stat.execute("delete from student;");
			stat.execute("delete from course;");
			
			stat.execute("insert into student(student_id, name, cnp, gr) values (1, \"Andrei\", 101, 3);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (2, \"Ana\", 102, 2);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (3, \"Vlad\", 103, 1);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (4, \"Robert\", 104, 5);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (5, \"Liviu\", 105, 4);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (6, \"Andreea\", 106, 3);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (7, \"Laura\", 107, 2);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (8, \"Maria\", 108, 1);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (9, \"Ionut\", 109, 5);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (10, \"Ciprian\", 110, 4);");
			stat.execute("insert into student(student_id, name, cnp, gr) values (11, \"Alexandra\", 111, 3);");
			
			stat.execute("insert into course (course_id, course_name, teacher_name) values (1, \"Legislatie economica\", \"R. Cordos\");");
			stat.execute("insert into course (course_id, course_name, teacher_name) values (2, \"Limba engleza I\", \"S. Munteanu\");");
			stat.execute("insert into course (course_id, course_name, teacher_name) values (3, \"Limba engleza II\", \"S. Munteanu\");");
			stat.execute("insert into course (course_id, course_name, teacher_name) values (4, \"Sport\", \"M. Dumitrescu\");");
			stat.execute("insert into course (course_id, course_name, teacher_name) values (5, \"PRISM\", \"E. Todoran\");");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}


