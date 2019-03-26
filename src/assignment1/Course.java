package assignment1;

import java.util.Date;

public class Course {
	public Integer id;
	public String courseName, teacherName;
	public Date examDate;
	
	public Integer getId() {
		return id;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public Date getExamDate() {
		return examDate;
	}
}
