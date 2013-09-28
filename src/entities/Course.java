package entities;

public class Course {

	private int studentNumber;
	private int courseID;
	private String description;
	private Faculty faculty;
	private int semester;
	
	



	public Course(int studentNumber, int courseID, String description,
			Faculty faculty, int semester) {
		super();
		this.studentNumber = studentNumber;
		this.courseID = courseID;
		this.description = description;
		this.faculty = faculty;
		this.semester = semester;
	}

	public Faculty getFaculty() {
		return faculty;
	}



	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}



	public int getSemester() {
		return semester;
	}



	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getStudentNumber() {
		return studentNumber;
	}



	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

}
