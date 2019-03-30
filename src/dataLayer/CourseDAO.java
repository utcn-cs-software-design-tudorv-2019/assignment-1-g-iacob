package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import crossCuttingConcerns.Course;

public class CourseDAO {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet resSet = null;
	
	public CourseDAO() {
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Course> getCourses(){
        ArrayList<Course> rtn = new ArrayList<Course>();
        try {
            stat = con.createStatement();
            resSet = stat.executeQuery("select * from course;");
            while (resSet.next()) {
                rtn.add(new Course(resSet.getInt("course_id"), resSet.getString("course_name"),
                        resSet.getString("teacher_name"), resSet.getDate("exam_date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rtn;
    }
}
