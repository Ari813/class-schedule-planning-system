package Algorithm;

import java.util.Map;
import java.util.Set;

import entities.Course;
import entities.Class;
import entities.Lecturer;


public class Database {
	private Map<Integer, Course> Courses;// Courses;
	private Map<Integer, Lecturer> Lecturers; // Lecturers;
	private Map<Integer, Class> Classes; // Classes;
	
	private IndexMapping mapping;
	
	private int length;
	
	public Database()
	{
		//get all database
		length = Courses.size() * Lecturers.size() * Classes.size() * Individual.weeklyHours;
	}

	/**
	 * @return the courses
	 */
	public Course getCourse(int courseID) {
		Course crs = Courses.get(courseID);
		return crs;
	}
	
	public Course getCourseByIndex(int index) {
		Course crs = Courses.get(mapping.getClassID(index));
		return crs;
	}

	/**
	 * @return the lecturers
	 */
	public Lecturer getLecturer(int LecturerID) {
		Lecturer lct = Lecturers.get(LecturerID);
		return lct;
	}



	/**
	 * @return the classes
	 */
	public Class getClass(int ClassID) {
		Class cls = Classes.get(ClassID);
		return cls;
	}


	/**
	 * @return the courses
	 */
	public Map<Integer, Course> getCourses() {
		return Courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Map<Integer, Course> courses) {
		Courses = courses;
	}

	/**
	 * @return the lecturers
	 */
	public Map<Integer, Lecturer> getLecturers() {
		return Lecturers;
	}

	/**
	 * @param lecturers the lecturers to set
	 */
	public void setLecturers(Map<Integer, Lecturer> lecturers) {
		Lecturers = lecturers;
	}

	/**
	 * @return the classes
	 */
	public Map<Integer, Class> getClasses() {
		return Classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(Map<Integer, Class> classes) {
		Classes = classes;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
	public int getCoursesSize()
	{
		return Courses.size();
	}
	
	public int getLecturersSize()
	{
		return Lecturers.size();
	}
	
	public int getClassesSize()
	{
		return Classes.size();
	}
	
	public Set<Integer> getClassesKeys()
	{
		return Classes.keySet();
	}
	
	public Set<Integer> getCoursesKeys()
	{
		return Courses.keySet();
	}
	
	public Set<Integer> getLecturersKeys()
	{
		return Lecturers.keySet();
	}

	/**
	 * @return the mapping
	 */
	public IndexMapping getMapping() {
		return mapping;
	}
}
