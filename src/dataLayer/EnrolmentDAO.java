package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import crossCuttingConcerns.Enrolment;

public class EnrolmentDAO {

	private Connection con = null;
	private Statement stat = null;
	private PreparedStatement pStat = null;
	private ResultSet resSet = null;
	
	public EnrolmentDAO() {
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
	
	public void insertEnrolment(Integer studentID, Integer courseID, Integer grade) {
		try {
			pStat = con.prepareStatement("insert into enrolment (student_id, course_id, grade) values (?, ?, ?)");
			
			pStat.setInt(1, studentID);
			pStat.setInt(2, courseID);
			pStat.setInt(3, grade);
			pStat.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Enrolment> getEnrolments(Integer studentID){
		ArrayList<Enrolment> enrolments = new ArrayList<Enrolment>();
		try {
			stat = con.createStatement();
			resSet = stat.executeQuery("select course_name, grade from student join enrolment on student.student_id = enrolment.student_id join course on enrolment.course_id = course.course_id where student.student_id = " + studentID.toString() + ";");
			while (resSet.next()) 
				enrolments.add(new Enrolment(resSet.getString("course_name"), resSet.getInt("grade")));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return enrolments;
	}
}
