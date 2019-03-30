package crossCuttingConcerns;

import java.util.Date;

public class Course {
	public Integer id;
	public String courseName, teacherName;
	public Date examDate;
	
	public Course(Integer id, String courseName, String teacherName){
        this.id = id;
        this.courseName = courseName;
        this.teacherName = teacherName;
        examDate = null;
    }  
   
    public Course(Integer id, String courseName, String teacherName, Date examDate){
        this.id = id;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.examDate = examDate;
    }
	
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
