package Algorithm;

import java.util.Iterator;

import entities.Course;
import Controllers.ManagerController;

public class RepairStrategy implements Runnable
{
	private Individual indiv;
	private Population population;
	private int indivIndex;

	public RepairStrategy(Population population, int i)
	{
		indiv = population.getIndividual(i);
		indivIndex = i;
		this.population = population;

	}

	@Override
	public void run()
	{
		repair();
		saveIndividual();

	}

	private synchronized void saveIndividual()
	{
		population.saveIndividual(indivIndex, indiv);

	}

	private void repair()
	{
		Iterator<Integer> courseItr = ManagerController.collageDB.getCoursesKeys().iterator();
		Iterator<Integer> LecItr;
		int courseID;
		int lecID;
		int CourseH, CourseL, CourseC, CourseR;
		int i;
		Course crs;
		int courseHours;
		boolean found;
		boolean canRepair = false;

		// repair multi-assignment of the same course
		while (courseItr.hasNext())
		{

			// init course run
			found = false;
			CourseH = -1;
			CourseL = -1;
			CourseR = -1;
			courseID = courseItr.next().intValue();
			int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseID);
			crs = ManagerController.collageDB.getCourse(courseID);
			courseHours = crs.getAcademicHours();
			// =====

			for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
			{
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
					{
						if ((indiv.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).isGene()) && (indiv.getGeneByIndex(Hours, LecturerIndex, ClassIndex, courseIndex).getIndex() == 0))
						{

							if (!found)
							{
								CourseH = Hours;
								CourseL = LecturerIndex;
								CourseR = ClassIndex;
								found = true;
							} else
							{

								if (Math.random() > 0.5)
								{
									for (i = 0; i < courseHours; i++)
										indiv.getGeneByIndex(Hours + i, LecturerIndex, ClassIndex, courseIndex).clrGene();
								} else
								{
									for (i = 0; i < courseHours; i++)
										indiv.getGeneByIndex(CourseH + i, CourseL, CourseR, courseIndex).clrGene();
									CourseH = Hours;
									CourseL = LecturerIndex;
									CourseC = ClassIndex;

								}

							}
							Hours += courseHours;
						}
					}

				}
			}
			if (!found)
			{
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++)
				{
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
					{
						for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
						{
							if (courseHours + Hours < Individual.weeklyHours)
							{
								canRepair = true;

								for (i = 0; i < courseHours; i++)
									if (indiv.getGeneByIndex(Hours + i, LecturerIndex, ClassIndex, courseIndex).isGene()
											|| !indiv.getGeneByIndex(Hours + i, LecturerIndex, ClassIndex, courseIndex).isEditable())
										canRepair = false;

								if (canRepair)
								{
									for (i = 0; i < courseHours; i++)
										indiv.setGeneByIndex(Hours + i, LecturerIndex, ClassIndex, courseIndex, i);
									break;
								}
							}
						}
						if (canRepair)
							break;
					}
					if (canRepair)
						break;

				}
			}
		}
		// repair lecturer's course assignment at the same time
		LecItr = ManagerController.collageDB.getLecturersKeys().iterator();

		while (LecItr.hasNext())
		{
			// init course run
			found = false;
			CourseH = -1;
			CourseR = -1;
			CourseC = -1;
			lecID = LecItr.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(lecID);
			// -----------------

			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++)
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
					{
						if ((indiv.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).isGene()) && (indiv.getGeneByIndex(Hours, lecturerIndex, ClassIndex, CourseIndex).getIndex() == 0))
						{
							courseHours = ManagerController.collageDB.getCourseByIndex(CourseIndex).getAcademicHours();
							if (!found)
							{
								CourseH = Hours;
								CourseC = CourseIndex;
								CourseR = ClassIndex;
								found = true;
							} else
							{
								if (Math.random() > 0.5)
								{
									for (i = 0; i < courseHours; i++)
										indiv.getGeneByIndex(Hours + i, lecturerIndex, ClassIndex, CourseIndex).clrGene();
								} else
								{
									for (i = 0; i < courseHours; i++)
										indiv.getGeneByIndex(CourseH + i, lecturerIndex, CourseR, CourseC).clrGene();
									CourseH = Hours;
									CourseC = CourseIndex;
									CourseR = ClassIndex;
								}
							}
							Hours += courseHours-1;
						}
					}
				}

			}
		}

	}
}
