package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import Controllers.*;
import common.Settings;
import entities.Course;

public class Population
{
	private Individual JumpStartIndividual;
	Individual[] individuals;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialise)
	{
		individuals = new Individual[populationSize];
		// Initialize population
		if (initialise)
		{
			// Loop and create individuals
			JumpStartIndividual = new Individual(ManagerController.collageDB.getLecturersSize(), ManagerController.collageDB.getClassesSize(), ManagerController.collageDB.getCoursesSize());
			JumpStart();
			for (int i = 0; i < size(); i++)
			{
				Individual newIndividual = new Individual(JumpStartIndividual);
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	public Population(int populationSize, Individual indv) throws InterruptedException
	{
		individuals = new Individual[populationSize];
		Thread[] GeneratePopulationAlgorithm = new Thread[populationSize];
		JumpStartIndividual = indv;
		JumpStart();
		for (int j = 0; j <= size() - 50; j += 50)
		{
			for (int i = j; i < j + 50; i++)
			{
				GeneratePopulationAlgorithm generateThread = new GeneratePopulationAlgorithm(this, i, JumpStartIndividual);

				GeneratePopulationAlgorithm[i] = new Thread(generateThread, "generate " + i);
				GeneratePopulationAlgorithm[i].start();
			}
			for (int i = j; i < j + 50; i++)
				GeneratePopulationAlgorithm[i].join();
		}
	}

	private void JumpStart()
	{

		Iterator<Integer> Leciter = ManagerController.collageDB.getLecturersKeys().iterator();
		Iterator<Integer> courseIter;
		while (Leciter.hasNext())
		{
			int Lecturerid = Leciter.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(Lecturerid);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
			{
				int[] preferedSchedualArray = ManagerController.collageDB.getLecturer(Lecturerid).getPreferedSchedualArray();
				if (preferedSchedualArray[Hours] == Settings.selection_not_available)
				{
					for (int ClssIndex = 0; ClssIndex < Individual.NumOfClasses; ClssIndex++)
						for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++)
						{
							JumpStartIndividual.getGeneByIndex(Hours, lecturerIndex, ClssIndex, CourseIndex).setUnEditable();
						}
				}
			}
		}

		Leciter = ManagerController.collageDB.getLecturersKeys().iterator();
		boolean found = false;
		while (Leciter.hasNext())
		{
			int Lecturerid = Leciter.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(Lecturerid);
			int relatedCourseKey;
			ArrayList<Course> LecturerCourses;
			courseIter = ManagerController.collageDB.getCoursesKeys().iterator();

			while (courseIter.hasNext())
			{
				found = false;
				int courseid = courseIter.next().intValue();
				int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseid);
				relatedCourseKey = ManagerController.collageDB.getCourse(courseid).getCourseRelativeKey();
				LecturerCourses = ManagerController.collageDB.getLecturer(Lecturerid).getLecturerCourses();
				for (int lecCourseIndex = 0; lecCourseIndex < LecturerCourses.size(); lecCourseIndex++)
				{
					if (relatedCourseKey == LecturerCourses.get(lecCourseIndex).getCourseID())

						found = true;

				}
				if (!found)
				{
					for (int Hours = 0; Hours < Individual.weeklyHours; Hours++)
						for (int ClssIndex = 0; ClssIndex < Individual.NumOfClasses; ClssIndex++)
							JumpStartIndividual.getGeneByIndex(Hours, lecturerIndex, ClssIndex, courseIndex).setUnEditable();
				}

			}
		}
	}

	/* Getters */
	public Individual getIndividual(int index)
	{
		return individuals[index];
	}

	public Individual getFittest()
	{
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest

		for (int i = 0; i < size(); i++)
		{
			if (fittest.getFitness() <= getIndividual(i).getFitness())
			{
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	/* Public methods */
	// Get population size
	public int size()
	{
		return individuals.length;
	}

	// Save individual
	public void saveIndividual(int index, Individual indiv)
	{
		individuals[index] = indiv;
	}

	public void printfitness()
	{
		/*
		 * for (int i = 0; i < size(); i++) { System.out.println("fitness[" + i
		 * + "] => " + getIndividual(i).getFitness()); }
		 */
	}

}