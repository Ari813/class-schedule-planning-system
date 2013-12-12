package Algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IndexMapping {

	private Map<Integer, Integer> CoursesMapping;// Courses;
	private Map<Integer, Integer> LecturersMapping; // Lecturers;
	private Map<Integer, Integer> ClassesMapping; // Classes;

	public IndexMapping() {
		CoursesMapping = new HashMap<Integer, Integer>();// Courses;
		LecturersMapping = new HashMap<Integer, Integer>(); // Lecturers;
		ClassesMapping = new HashMap<Integer, Integer>(); // Classes;
	}
	
	public IndexMapping(Set<Integer> courses,Set<Integer> Lecturers,Set<Integer> Classes) {
		super();
		setCoursesMapping(courses);
		setLecturersMapping(Lecturers);
		setClassesMapping(Classes);
		
	}

	public int getCourseID(int index) {
		return CoursesMapping.get(index);
	}

	public int getLecturerID(int index) {
		return LecturersMapping.get(index);
	}

	public int getClassID(int index) {
		return ClassesMapping.get(index);
	}

	public int getTime(int day, int hour) {
		return (day - 1) * Individual.dailyHours + hour;
	}

	public void setCoursesMapping(Set<Integer> courses) {
		Iterator<Integer> itr =courses.iterator();
		int i = 0;
		while (itr.hasNext())
		{
			CoursesMapping.put(i, itr.next().intValue());
			i++;
		}
			
	}

	public void setLecturersMapping(Set<Integer> Lecturers) {
		Iterator<Integer> itr =Lecturers.iterator();
		int i = 0;
		while (itr.hasNext())
		{
			LecturersMapping.put(i, itr.next().intValue());
			i++;
		}
	}

	public void setClassesMapping(Set<Integer> Classes) {
		Iterator<Integer> itr =Classes.iterator();
		int i = 0;
		while (itr.hasNext())
		{
			ClassesMapping.put(i, itr.next().intValue());
			i++;
		}
	}

}