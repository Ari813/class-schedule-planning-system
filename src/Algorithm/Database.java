package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import entities.Class;
import entities.Course;
import entities.Lecturer;

public class Database
{
	private Map<Integer, Course> Courses;// Courses;
	private Map<Integer, Lecturer> Lecturers; // Lecturers;
	private Map<Integer, Class> Classes; // Classes;

	private Map<Integer, ArrayList<Integer>> relatedCoursesMap;
	private Map<Integer, ArrayList<Integer>> SemesterCoursesMap;

	private IndexMapping mapping;

	private int length;

	public Database(ArrayList<Course> courses, ArrayList<Lecturer> lecturers, ArrayList<Class> rooms)
	{
		// get all database
		this.Courses = new HashMap<Integer, Course>();
		this.Lecturers = new HashMap<Integer, Lecturer>();
		this.Classes = new HashMap<Integer, Class>();

		for (int courseIndex = 0; courseIndex < courses.size(); courseIndex++)
			this.Courses.put(courses.get(courseIndex).getCourseID(), courses.get(courseIndex));

		for (int lecturersIndex = 0; lecturersIndex < lecturers.size(); lecturersIndex++)
			this.Lecturers.put(lecturers.get(lecturersIndex).getID(), lecturers.get(lecturersIndex));

		for (int ClassesIndex = 0; ClassesIndex < rooms.size(); ClassesIndex++)
			this.Classes.put(rooms.get(ClassesIndex).getClassID(), rooms.get(ClassesIndex));

		mapping = new IndexMapping(Courses, Lecturers, Classes);

		length = Courses.size() * Lecturers.size() * Classes.size() * Individual.weeklyHours;
	}

	/**
	 * @return the courses
	 */
	public Course getCourse(int courseID)
	{
		Course crs = Courses.get(courseID);
		return crs;
	}

	public Course getCourseByIndex(int index)
	{
		Course crs = Courses.get(mapping.getClassID(index));
		return crs;
	}

	/**
	 * @return the lecturers
	 */
	public Lecturer getLecturer(int LecturerID)
	{
		Lecturer lct = Lecturers.get(LecturerID);
		return lct;
	}

	/**
	 * @return the classes
	 */
	public Class getClass(int ClassID)
	{
		Class cls = Classes.get(ClassID);
		return cls;
	}

	/**
	 * @return the courses
	 */
	public Map<Integer, Course> getCourses()
	{
		return Courses;
	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public void setCourses(Map<Integer, Course> courses)
	{
		Courses = courses;
	}

	/**
	 * @return the lecturers
	 */
	public Map<Integer, Lecturer> getLecturers()
	{
		return Lecturers;
	}

	/**
	 * @param lecturers
	 *            the lecturers to set
	 */
	public void setLecturers(Map<Integer, Lecturer> lecturers)
	{
		Lecturers = lecturers;
	}

	/**
	 * @return the classes
	 */
	public Map<Integer, Class> getClasses()
	{
		return Classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(Map<Integer, Class> classes)
	{
		Classes = classes;
	}

	/**
	 * @return the length
	 */
	public int getLength()
	{
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
	public IndexMapping getMapping()
	{
		return mapping;
	}

	/**
	 * @return the relatedCoursesMap
	 */
	public Map<Integer, ArrayList<Integer>> getRelatedCourses()
	{
		if (relatedCoursesMap == null || relatedCoursesMap.isEmpty())
		{
			Iterator<Integer> courseItr = MainGA.collageDB.getCoursesKeys().iterator();
			Map<Integer, ArrayList<Integer>> relatedCoursesMap = new HashMap<Integer, ArrayList<Integer>>();

			while (courseItr.hasNext())
			{
				int courseID = courseItr.next().intValue();
				int courseRelatedKey = getCourse(courseID).getCourseRelativeKey();
				if (courseRelatedKey != -1)
				{
					if (!relatedCoursesMap.containsKey(courseRelatedKey))
					{
						ArrayList<Integer> newGroup = new ArrayList<Integer>();
						relatedCoursesMap.put(courseRelatedKey, newGroup);
						relatedCoursesMap.get(courseRelatedKey).add(courseRelatedKey);
					}
					relatedCoursesMap.get(courseRelatedKey).add(courseID);
				}
			}
		}

		return relatedCoursesMap;
	}

	public Map<Integer, ArrayList<Integer>> getCoursesBySemester()
	{
		if (SemesterCoursesMap == null || SemesterCoursesMap.isEmpty())
		{
			Iterator<Integer> courseItr = MainGA.collageDB.getCoursesKeys().iterator();
			Map<Integer, ArrayList<Integer>> SemesterCoursesMap = new HashMap<Integer, ArrayList<Integer>>();

			while (courseItr.hasNext())
			{
				int courseID = courseItr.next().intValue();
				int courseSemester = getCourse(courseID).getSemester();
				if (!SemesterCoursesMap.containsKey(courseSemester))
				{
					ArrayList<Integer> newGroup = new ArrayList<Integer>();
					SemesterCoursesMap.put(courseSemester, newGroup);
				}
				SemesterCoursesMap.get(courseSemester).add(courseID);
			}
		}

		return SemesterCoursesMap;
	}

}
