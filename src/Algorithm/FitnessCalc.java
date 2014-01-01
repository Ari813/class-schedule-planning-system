package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import common.Settings;

public class FitnessCalc
{

	// Calculate individuals fitness by comparing it to our candidate solution
	public static double getFitness(Individual individual)
	{
		double fitness = 0;
		int hard = 0;
		double soft = 0;

		hard = calcHardConstraints(individual);
		if (hard == 0)
		{
			soft = calcSoftConstraints(individual);
			fitness = 1 + (1.0 / (1.0 + soft)); // fitness = (1.0 / (1.0 +
												// hard)) + (1.0 / (1.0 +
												// soft));
		} else
		{
			fitness = (1.0 / (1.0 + hard));
		}

		return fitness;
	}

	private static double calcSoftConstraints(Individual individual)
	{
		int SoftConstraints = 0;
		int tempCounter = 0;
		int lecID;
		int classID;
		int courseID;
		int counter;
		// / check lecturer's preferences
		Iterator<Integer> LecItr = MainGA.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				counter = 0;
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene())
						{
							if (MainGA.collageDB.getLecturer(lecID).getPreferedSchedualArray()[Hours] == Settings.selection_available)
								counter++;

						}
					}
				}
				SoftConstraints += counter - 1;

			}
		}

		// check a lecturer is not teaching in 2 different campuses in near
		// hours.
		LecItr = MainGA.collageDB.getLecturersKeys().iterator();
		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours - 1; Hours++)
			{
				counter = 0;
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene())
						{
							for (int tempClassIndex = 0; tempClassIndex < Individual.NumOfClasses; tempClassIndex++)
							{
								for (int tempCourseIndex = 0; tempCourseIndex < Individual.NumOfCourses; tempCourseIndex++)
								{
									if (individual.getGeneByIndex(Hours + 1, lecturerIndex, tempClassIndex, tempCourseIndex).isGene())
									{
										if (MainGA.collageDB.getClass(MainGA.collageDB.getMapping().getClassID(ClassIndex)).getCampus() != MainGA.collageDB.getClass(
												MainGA.collageDB.getMapping().getClassID(tempClassIndex)).getCampus())
										{
											counter++;
										}
									}
								}
							}
						}
					}
					

				}
				SoftConstraints += counter;
			}
		}
		return SoftConstraints;
	}

	private static int calcHardConstraints(Individual individual)
	{
		int HardConstraints = 0;
		int tempCounter = 0;
		int lecID;
		int classID;
		int courseID;
		int counter;
		// / check how many times a lecturer teaches in an hour.
		Iterator<Integer> LecItr = MainGA.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				counter = 0;
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene())
						{
							counter++;
						}
					}
				}
				HardConstraints += counter - 1;

			}
		}

		// / check how many times a class is taken in an hour
		Iterator<Integer> classItr = MainGA.collageDB.getClassesKeys().iterator();
		while (classItr.hasNext())
		{
			classID = classItr.next().intValue();
			int classIndex = MainGA.collageDB.getMapping().getClassIndex(classID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				counter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex, classIndex, CourseIndex).isGene())
						{
							counter++;
						}
					}
				}
				HardConstraints += counter - 1;

			}
		}

		// / check how many times a course has been assigned in an hour
		Iterator<Integer> courseItr = MainGA.collageDB.getCoursesKeys().iterator();
		while (courseItr.hasNext())
		{
			courseID = courseItr.next().intValue();
			int courseIndex = MainGA.collageDB.getMapping().getCourseIndex(courseID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				counter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).isGene())
						{
							counter++;
						}
					}
				}
				HardConstraints += counter - 1;

			}
		}

		// check course, lecture and lab not assign at the same time
		tempCounter = 0;
		Map<Integer, ArrayList<Integer>> relatedCoursesMap = MainGA.collageDB.getRelatedCourses();
		courseItr = relatedCoursesMap.keySet().iterator();

		while (courseItr.hasNext())
		{
			courseID = courseItr.next().intValue();
			int courseIndex = MainGA.collageDB.getMapping().getCourseIndex(courseID);
			ArrayList<Integer> tempArrayList = relatedCoursesMap.get(courseID);
			for (int TempCourseIndex = 0; TempCourseIndex < tempArrayList.size(); TempCourseIndex++)
			{
				int relatedCourseIndex = MainGA.collageDB.getMapping().getCourseIndex(tempArrayList.get(TempCourseIndex));
				for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
				{
					counter = 0;
					for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
					{
						for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
						{
							if (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).isGene()
									&& individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, relatedCourseIndex).isGene())
							{
								counter++;
							}
						}
					}
					tempCounter += counter;

				}
			}
		}
		HardConstraints = tempCounter / 2;
		// check courses of same semester not overlapping.
		tempCounter = 0;

		Map<Integer, ArrayList<Integer>> semesterCoursesMap = MainGA.collageDB.getCoursesBySemester();
		Iterator<Integer> semesterItr = semesterCoursesMap.keySet().iterator();

		while (semesterItr.hasNext())
		{
			int semsterNum = semesterItr.next().intValue();
			ArrayList<Integer> courseArrayList = semesterCoursesMap.get(semsterNum);
			for (int CourseIndex = 0; CourseIndex < courseArrayList.size(); CourseIndex++)
			{
				int courseDBIndex = MainGA.collageDB.getMapping().getCourseIndex(courseArrayList.get(CourseIndex));
				ArrayList<Integer> tempArrayList = semesterCoursesMap.get(semsterNum);
				for (int TempCourseIndex = CourseIndex; TempCourseIndex < tempArrayList.size(); TempCourseIndex++)
				{
					int tempCourseDBIndex = MainGA.collageDB.getMapping().getCourseIndex(courseArrayList.get(TempCourseIndex));
					for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
					{
						counter = 0;
						for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
						{
							for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
							{
								if (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseDBIndex).isGene()
										&& individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, tempCourseDBIndex).isGene())
								{
									counter++;
								}
							}
						}

					}

				}
			}
		}

		// check total hours a day for 1 lecturer is not above 8 hours.
		LecItr = MainGA.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(lecID);
			for (int days = 0; days < Individual.workingDays; days++)
			{
				counter = 0;
				for (int hours = 0; hours < Individual.dailyHours; hours++)
				{
					int Hours = MainGA.collageDB.getMapping().getTime(days, hours);

					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
						{
							if (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene())
							{
								counter++;
							}
						}
					}
				}
				if (counter > 8)
					HardConstraints += counter - 8;

			}
		}

		return HardConstraints;
	}

}