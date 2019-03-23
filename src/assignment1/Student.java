package assignment1;

public class Student {
	public Integer id, cnp, gr;
	public String name;
	
	public Student() {
		id = null;
		cnp = null;
		gr = null;
		name = null;
	}
	
	public Student (String name) {
		this();
		this.name = name;
	}
	
	public Student(String name, Integer cnp, Integer gr) {
		id = null;
		this.name = name;
		this.cnp = cnp;
		this.gr = gr;
	}
	
}
