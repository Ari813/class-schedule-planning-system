package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<StudyAids> StudyAids;
	private ArrayList<Lecturer> CourseLecturers;

	private int studentNumber;
	private int courseID;
	private String description;
	private int faculty;
	private int semester;
	private int academicHours;
	private boolean hasadditionalInfo;
	private int EstimationOfStudentsNum;
	private int CourseRelativeKey; 
	
	public Course(int studentNumber, int courseID, String description,
			int faculty, int semester, int academicHours) {
		this.studentNumber = studentNumber;
		this.courseID = courseID;
		this.description = description;
		this.faculty = faculty;
		this.semester = semester;
		this.setAcademicHours(academicHours);

		setStudyAids(new ArrayList<StudyAids>());
		CourseLecturers = new ArrayList<Lecturer>();
		hasadditionalInfo = false;
	}
	
	public Course(int studentNumber, int courseID, String description,
			int faculty, int semester, int academicHours, int EstimationOfStudentsNum) {
		this.studentNumber = studentNumber;
		this.courseID = courseID;
		this.description = description;
		this.faculty = faculty;
		this.semester = semester;
		this.setAcademicHours(academicHours);
		this.EstimationOfStudentsNum = EstimationOfStudentsNum;

		setStudyAids(new ArrayList<StudyAids>());
		CourseLecturers = new ArrayList<Lecturer>();
		hasadditionalInfo = false;
	}
	
	public Course() {

		setStudyAids(new ArrayList<StudyAids>());
		CourseLecturers = new ArrayList<Lecturer>();
		hasadditionalInfo = false;
	}

	public int getFaculty() {
		return faculty;
	}

	public void setFaculty(int i) {
		this.faculty = i;
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

	/**
	 * @return the CourseLecturers
	 */
	public ArrayList<Lecturer> getCourseLecturers() {
		return CourseLecturers;
	}

	/**
	 * @param courselecturers
	 *            the CourseLecturers to set
	 */
	public void setCourseLecturers(ArrayList<Lecturer> courselecturers) {
		CourseLecturers = courselecturers;
		hasadditionalInfo = !courselecturers.isEmpty();
	}

	public void addLecturer(Lecturer lec) {
		this.CourseLecturers.add(lec);
		hasadditionalInfo = true;
	}

	/**
	 * @return the studyAids
	 */
	public ArrayList<StudyAids> getStudyAids() {
		return StudyAids;
	}

	/**
	 * @param studyAids
	 *            the studyAids to set
	 */
	public void setStudyAids(ArrayList<StudyAids> studyAids) {
		StudyAids = studyAids;
		hasadditionalInfo = !studyAids.isEmpty();
	}

	public void addStudyAids(StudyAids studyAids) {
		StudyAids.add(studyAids);
		hasadditionalInfo = true;
		
	}

	/**
	 * @return the hasLecturersInfo
	 */
	public Boolean HasAdditionalInfo() {
		return hasadditionalInfo;
	}

	/**
	 * @param hasLecturersInfo the hasLecturersInfo to set
	 */
	public void setHasadditionalInfo() {
		this.hasadditionalInfo = true;
	}
	
	public void clrHasadditionalInfo() {
		this.hasadditionalInfo = false;
	}

	/**
	 * @return the academicHours
	 */
	public int getAcademicHours() {
		return academicHours;
	}

	/**
	 * @param academicHours the academicHours to set
	 */
	public void setAcademicHours(int academicHours) {
		this.academicHours = academicHours;
	}

	public int getCapacity() {
		return EstimationOfStudentsNum;
	}

	public void setCapacity(int EstimationOfStudentsNum) {
		this.EstimationOfStudentsNum = EstimationOfStudentsNum;
	}

	/**
	 * @return the courseKey
	 */
	public int getCourseRelativeKey() {
		return CourseRelativeKey;
	}

	/**
	 * @param courseKey the courseKey to set
	 */
	public void setCourseRelativeKey(int courseKey) {
		CourseRelativeKey = courseKey;
	}

}
