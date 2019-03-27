package assignment1;

public class Enrolment {
	String courseName;
	Integer grade;
	
	public Enrolment(String courseName, Integer grade) {
		this.courseName = courseName;
		this.grade = grade;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public Integer getGrade() {
		return grade;
	}
	
}
