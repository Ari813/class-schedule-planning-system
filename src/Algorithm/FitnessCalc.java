package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import common.Settings;
import entities.StudyAids;
import Controllers.*;

public class FitnessCalc implements Runnable
{
	private Individual indiv;
	private Population population;
	private int indivIndex;
	private static boolean debug = false;

	public FitnessCalc(Population population, int i)
	{
		indiv = population.getIndividual(i);
		indivIndex = i;
		this.population = population;

	}

	// Calculate individuals fitness by comparing it to our candidate solution
	public static double getFitness(Individual individual)
	{
		double fitness = 0;
		double hard = 0;
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
			fitness = (1.0 / (1.0 + (double) hard));
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
							if (ManagerController.collageDB.getClass(classID).getCapcity() < (ManagerController.collageDB.getCourse(ManagerController.collageDB.getMapping().getCourseID(CourseIndex))
									.getStudentNumber()))
							{
								SoftConstraints++;
							}
						}
					}
				}
			}
		}

		// / check studyaids
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
							ArrayList<StudyAids> classStudyAids = ManagerController.collageDB.getClass(ManagerController.collageDB.getMapping().getClassID(ClassIndex)).getStudyAids();

							for (int courseStudyAidIndex = 0; courseStudyAidIndex < courseStudyAids.size(); courseStudyAidIndex++)
							{
								for (int classStudyAidIndex = 0; classStudyAidIndex < classStudyAids.size(); classStudyAidIndex++)
								{
									int courseAid = courseStudyAids.get(courseStudyAidIndex).getAidsID();
									int classAid = classStudyAids.get(classStudyAidIndex).getAidsID();
									if (courseAid == classAid)
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

	private static double calcHardConstraints(Individual individual)
	{

		double HardConstraints = 0;
		int tempCounter = 0;
		int lecID;
		int classID;
		int courseID;
		int counter;
		int courseCounter;

		// check all course are assigned. + check how many times a course has
		// been assigned in an hour
		Iterator<Integer> courseItr = ManagerController.collageDB.getCoursesKeys().iterator();
		tempCounter = 0;
		while (courseItr.hasNext())
		{
			counter = 0;
			courseID = courseItr.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				courseCounter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						if ((individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).isGene())
								&& (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).getIndex() == 0))
						{
							// check how many times a course has been assigned
							// in an hour
							courseCounter++;
							if (ManagerController.collageDB.getMapping().getCourseID(courseIndex) == courseID)
							{
								// check all course are assigned
								counter++;
							}
						}
					}
				}
				if (courseCounter > 1)
				{
					tempCounter += courseCounter;
				}
			}
			if (counter == 0)
			{
				tempCounter++;
			} else
			{
				tempCounter += (counter - 1);
			}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out.println("check all course are assigned:" + HardConstraints);

		// check how many times a lecturer teaches in an hour.
		tempCounter = 0;
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
				if (counter > 0)
					tempCounter += counter - 1;

			}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out.println("check how many times a lecturer teaches in an hour:" + HardConstraints);

		// check how many times a class is taken in an hour
		tempCounter = 0;
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
				if (counter > 0)
					tempCounter += counter - 1;

			}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out.println("check how many times a class is taken in an hour:" + HardConstraints);
		/*
		 * // / check how many times a course has been assigned in an hour
		 * tempCounter = 0; courseItr =
		 * ManagerController.collageDB.getCoursesKeys().iterator(); while
		 * (courseItr.hasNext()) { courseID = courseItr.next().intValue(); int
		 * courseIndex =
		 * ManagerController.collageDB.getMapping().getCourseIndex(courseID);
		 * for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) {
		 * counter = 0; for (int LecturerIndex = 0; LecturerIndex <
		 * Individual.NumOfLecturers; LecturerIndex++) { for (int ClassIndex =
		 * 0; ClassIndex < Individual.NumOfClasses; ClassIndex++) { if
		 * (individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex,
		 * courseIndex).isGene()) { counter++; } } } if (counter > 0)
		 * tempCounter += counter - 1;
		 * 
		 * } } HardConstraints += tempCounter;
		 * 
		 * if (debug) System.out.println(
		 * "check how many times a course has been assigned in an hour:" +
		 * tempCounter);
		 */

		// check courses of same semester not overlapping.
		tempCounter = 0;

		Map<Integer, ArrayList<Integer>> semesterCoursesMap = ManagerController.collageDB.getCoursesBySemester();
		Iterator<Integer> semesterItr = semesterCoursesMap.keySet().iterator();
		ArrayList<Integer> tempArrayList;
		ArrayList<Integer> courseArrayList;
		while (semesterItr.hasNext())
		{
			int semsterNum = semesterItr.next().intValue();
			courseArrayList = semesterCoursesMap.get(semsterNum);
			for (int CourseIndex = 0; CourseIndex < courseArrayList.size(); CourseIndex++)
			{
				int courseDBIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseArrayList.get(CourseIndex));
				tempArrayList = semesterCoursesMap.get(semsterNum);//????
				for (int TempCourseIndex = CourseIndex; TempCourseIndex < tempArrayList.size(); TempCourseIndex++)
				{
					int tempCourseDBIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseArrayList.get(TempCourseIndex));
					for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
					{
						//counter = 0;
						for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
						{
							for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
							{
								if ((individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseDBIndex).isGene() && individual.getGeneByIndex(Hours, LecturerIndex, ClassIndex,
										tempCourseDBIndex).isGene())
										&& (ManagerController.collageDB.getCourseByIndex(tempCourseDBIndex).getCourseRelativeKey() != ManagerController.collageDB.getCourseByIndex(courseDBIndex)
												.getCourseRelativeKey())
										&& (ManagerController.collageDB.getCourseByIndex(tempCourseDBIndex).getFaculty() == ManagerController.collageDB.getCourseByIndex(courseDBIndex).getFaculty()))
								
								{
								//	counter++;
									tempCounter ++;
								}

							}
						}
						//tempCounter += counter;

					}

				}
			}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out.println("check courses of same semester not overlapping:" + tempCounter+"-"+HardConstraints);
		
		/*
		 * // check total hours a day for 1 lecturer is not above 8 hours.
		 * LecItr = ManagerController.collageDB.getLecturersKeys().iterator();
		 * 
		 * while (LecItr.hasNext()) { lecID = LecItr.next().intValue(); int
		 * lecturerIndex =
		 * ManagerController.collageDB.getMapping().getLecturerIndex(lecID); for
		 * (int days = 1; days <= Individual.workingDays; days++) { counter = 0;
		 * for (int hours = 0; hours < Individual.dailyHours; hours++) { int
		 * Hours = ManagerController.collageDB.getMapping().getTime(days,
		 * hours);
		 * 
		 * for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses;
		 * ClassIndex++) { for (int CourseIndex = 0; CourseIndex <
		 * Individual.NumOfCourses; CourseIndex++) { if
		 * (individual.getGeneByIndex(Hours, lecturerIndex, ClassIndex,
		 * CourseIndex).isGene()) { counter++; } } } } if (counter > 8)
		 * HardConstraints += counter - 8;
		 * 
		 * } }
		 */
		return HardConstraints;
	}

	@Override
	public void run()
	{
		double fitness = getFitness(indiv);
		indiv.setFitness(fitness);
		saveIndividual();

	}

	private synchronized void saveIndividual()
	{
		population.saveIndividual(indivIndex, indiv);

	}

	public static double getdebugFitness(Individual individual)
	{
		debug = true;
		double debugFitness = getFitness(individual);
		debug = false;
		return debugFitness;
	}
}