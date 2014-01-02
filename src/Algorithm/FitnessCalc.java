package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import common.Settings;
import entities.StudyAids;
import Controllers.*;

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
		int lecID;
		int classID;
		int courseID;
		int counter;
		// / check lecturer's preferences
		Iterator<Integer> LecItr = ManagerController.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene())
						{
							if (ManagerController.collageDB.getLecturer(lecID).getPreferedSchedualArray()[Hours] == Settings.selection_available)
								SoftConstraints++;

						}
					}
				}

			}
		}
		
		// check a lecturer is not teaching in 2 different campuses in near
		// hours.
		LecItr = ManagerController.collageDB.getLecturersKeys().iterator();
		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours - 1; Hours++)
			{
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
										if (ManagerController.collageDB.getClass(ManagerController.collageDB.getMapping().getClassID(ClassIndex)).getCampus() != ManagerController.collageDB.getClass(
												ManagerController.collageDB.getMapping().getClassID(tempClassIndex)).getCampus())
										{
											SoftConstraints++;
										}
									}
								}
							}
						}
					}

				}
			}
		}

		// check class capacity vs course capacity
		Iterator<Integer> classItr = ManagerController.collageDB.getClassesKeys().iterator();
		while (classItr.hasNext())
		{
			classID = classItr.next().intValue();
			int classIndex = ManagerController.collageDB.getMapping().getClassIndex(classID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex, classIndex, CourseIndex).isGene())
						{
							if (ManagerController.collageDB.getClass(classID).getCapcity() < (ManagerController.collageDB.getCourse(ManagerController.collageDB.getMapping().getCourseID(CourseIndex)).getStudentNumber()))
							{
								SoftConstraints++;
							}
						}
					}
				}
			}
		}

		// / check how many times a course has been assigned in an hour
		Iterator<Integer> courseItr = ManagerController.collageDB.getCoursesKeys().iterator();
		while (courseItr.hasNext())
		{
			courseID = courseItr.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).isGene())
						{
							counter = 0;
							ArrayList<StudyAids> courseStudyAids = ManagerController.collageDB.getCourse(courseID).getStudyAids();
							ArrayList<StudyAids> classStudyAids =ManagerController.collageDB.getClass(ManagerController.collageDB.getMapping().getClassID(ClassIndex)).getStudyAids();
							
							for (int courseStudyAidIndex = 0; courseStudyAidIndex < courseStudyAids.size(); courseStudyAidIndex++)
							{
								for (int classStudyAidIndex = 0; classStudyAidIndex < classStudyAids.size(); classStudyAidIndex++)
								{
									int courseAid = courseStudyAids.get(courseStudyAidIndex).getAidsID();
									int classAid = classStudyAids.get(classStudyAidIndex).getAidsID();
									if (courseAid ==classAid )
									{
										counter++;
										break;
									}
								}
							}
							if (counter < courseStudyAids.size())
								SoftConstraints++;
						}
					}
				}
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
		
		int courseCounter=0; 
		// / check how many times a lecturer teaches in an hour.
		
		Iterator<Integer> courseItr1 = ManagerController.collageDB.getCoursesKeys().iterator();

		while (courseItr1.hasNext())
		{
			counter = 0;
			courseID = courseItr1.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).getCourseID()==courseID)
						{
							counter=1;
						}
					}
				}
				
				
				//HardConstraints += counter - 1;

			}
			courseCounter+=counter;
		
		}
		if (courseCounter<ManagerController.collageDB.getClasses().size()){
			HardConstraints +=ManagerController.collageDB.getClasses().size()-courseCounter;
		}
		
	/////////////////////////	
		
		Iterator<Integer> LecItr = ManagerController.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(lecID);
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
		Iterator<Integer> classItr = ManagerController.collageDB.getClassesKeys().iterator();
		while (classItr.hasNext())
		{
			classID = classItr.next().intValue();
			int classIndex = ManagerController.collageDB.getMapping().getClassIndex(classID);
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
		Iterator<Integer> courseItr = ManagerController.collageDB.getCoursesKeys().iterator();
		while (courseItr.hasNext())
		{
			courseID = courseItr.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
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
		Map<Integer, ArrayList<Integer>> relatedCoursesMap = ManagerController.collageDB.getRelatedCourses();
		courseItr = relatedCoursesMap.keySet().iterator();

		while (courseItr.hasNext())
		{
			courseID = courseItr.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
			ArrayList<Integer> tempArrayList = relatedCoursesMap.get(courseID);
			for (int TempCourseIndex = 0; TempCourseIndex < tempArrayList.size(); TempCourseIndex++)
			{
				int relatedCourseIndex = ManagerController.collageDB.getMapping().getCourseIndex(tempArrayList.get(TempCourseIndex));
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

		Map<Integer, ArrayList<Integer>> semesterCoursesMap = ManagerController.collageDB.getCoursesBySemester();
		Iterator<Integer> semesterItr = semesterCoursesMap.keySet().iterator();

		while (semesterItr.hasNext())
		{
			int semsterNum = semesterItr.next().intValue();
			ArrayList<Integer> courseArrayList = semesterCoursesMap.get(semsterNum);
			for (int CourseIndex = 0; CourseIndex < courseArrayList.size(); CourseIndex++)
			{
				int courseDBIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseArrayList.get(CourseIndex));
				ArrayList<Integer> tempArrayList = semesterCoursesMap.get(semsterNum);
				for (int TempCourseIndex = CourseIndex; TempCourseIndex < tempArrayList.size(); TempCourseIndex++)
				{
					int tempCourseDBIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseArrayList.get(TempCourseIndex));
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
		LecItr = ManagerController.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			lecID = LecItr.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(lecID);
			for (int days = 1; days <= Individual.workingDays; days++)
			{
				counter = 0;
				for (int hours = 0; hours < Individual.dailyHours; hours++)
				{
					int Hours = ManagerController.collageDB.getMapping().getTime(days, hours);

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