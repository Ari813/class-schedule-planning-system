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
	private boolean hasadditionalInfo;

	public Course(int studentNumber, int courseID, String description,
			int faculty, int semester) {
		this.studentNumber = studentNumber;
		this.courseID = courseID;
		this.description = description;
		this.faculty = faculty;
		this.semester = semester;

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
	public void setHasadditionalInfo(Boolean additionalInfo) {
		this.hasadditionalInfo = additionalInfo;
	}

}
