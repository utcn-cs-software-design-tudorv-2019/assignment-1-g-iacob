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
	public Student(Integer id, String name, Integer cnp, Integer gr) {
		this.id = id;
		this.name = name;
		this.cnp = cnp;
		this.gr = gr;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getCnp() {
		return cnp;
	}
	public Integer getGroup() {
		return gr;
	}
	public String getName() {
		return name;
	}
	
}
