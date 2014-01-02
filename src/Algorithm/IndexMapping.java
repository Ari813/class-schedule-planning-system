package Algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import entities.Class;
import entities.Course;
import entities.Lecturer;

public class IndexMapping
{

	private Map<Integer, Integer> CoursesMappingID;// Courses;
	private Map<Integer, Integer> LecturersMappingID; // Lecturers;
	private Map<Integer, Integer> ClassesMappingID; // Classes;

	private Map<Integer, Integer> CoursesMappingIndex;// Courses;
	private Map<Integer, Integer> LecturersMappingIndex; // Lecturers;
	private Map<Integer, Integer> ClassesMappingIndex; // Classes;

	public IndexMapping()
	{
		CoursesMappingID = new HashMap<Integer, Integer>();// Courses;
		LecturersMappingID = new HashMap<Integer, Integer>(); // Lecturers;
		ClassesMappingID = new HashMap<Integer, Integer>(); // Classes;

		CoursesMappingIndex = new HashMap<Integer, Integer>();// Courses;
		LecturersMappingIndex = new HashMap<Integer, Integer>(); // Lecturers;
		ClassesMappingIndex = new HashMap<Integer, Integer>(); // Classes;
	}

	public IndexMapping(Map<Integer, Course> courses, Map<Integer, Lecturer> lecturer, Map<Integer, Class> classes)
	{
		this();
		setCoursesMapping(courses.keySet());
		setLecturersMapping(lecturer.keySet());
		setClassesMapping(classes.keySet());
	}

	public int getCourseIndex(int ID)
	{
		return CoursesMappingID.get(ID);
	}

	public int getLecturerIndex(int ID)
	{
		return LecturersMappingID.get(ID);
	}

	public int getClassIndex(int ID)
	{
		return ClassesMappingID.get(ID);
	}

	public int getCourseID(int index)
	{
		return CoursesMappingIndex.get(index);
	}

	public int getLecturerID(int index)
	{
		return LecturersMappingIndex.get(index);
	}

	public int getClassID(int index)
	{
		return ClassesMappingIndex.get(index);
	}

	public int getTime(int day, int hour)
	{
		return (day - 1) * Individual.dailyHours + hour;
	}

	public void setCoursesMapping(Set<Integer> courses)
	{
		Iterator<Integer> itr = courses.iterator();
		int i = 0;
		int courseID;
	

		while (itr.hasNext())
		{
			courseID = itr.next().intValue();
			CoursesMappingID.put(courseID, i);
			CoursesMappingIndex.put(i, courseID);
			i++;
		}


	}

	public void setLecturersMapping(Set<Integer> Lecturers)
	{
		Iterator<Integer> itr = Lecturers.iterator();

		int i = 0;
		int lectureID;
		while (itr.hasNext())
		{
			lectureID = itr.next().intValue();
			LecturersMappingID.put(lectureID, i);
			LecturersMappingIndex.put(i, lectureID);
			i++;
		}
	}

	public void setClassesMapping(Set<Integer> Classes)
	{
		Iterator<Integer> itr = Classes.iterator();

		int i = 0;
		int classID;
		while (itr.hasNext())
		{
			classID = itr.next().intValue();
			ClassesMappingID.put(classID, i);
			ClassesMappingIndex.put(i, classID);
			i++;
		}
	}

}