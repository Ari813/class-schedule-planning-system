package Algorithm;

import java.util.Iterator;

import Controllers.*;
import common.Settings;

public class Population
{
	private Individual JumpStartIndividual;
	private int popIter;
	Individual[] individuals;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialise)
	{
		individuals = new Individual[populationSize];
		// Initialise population
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

	public Population(int populationSize, Individual indv)
	{
		individuals = new Individual[populationSize];
		JumpStartIndividual = indv;
		JumpStart();
		for (int i = 0; i < size(); i++)
		{
			Individual newIndividual = new Individual(JumpStartIndividual);
			newIndividual.generateIndividual();
			saveIndividual(i, newIndividual);
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
				if (ManagerController.collageDB.getLecturer(Lecturerid).getPreferedSchedualArray()[Hours] == Settings.selection_not_available)
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

		while (Leciter.hasNext())
		{
			int Lecturerid = Leciter.next().intValue();
			int lecturerIndex = ManagerController.collageDB.getMapping().getLecturerIndex(Lecturerid);
			courseIter = ManagerController.collageDB.getCoursesKeys().iterator();
			while (courseIter.hasNext())
			{
				int courseid = courseIter.next().intValue();
				int courseIndex = ManagerController.collageDB.getMapping().getCourseIndex(courseid);

				if (!ManagerController.collageDB.getLecturer(Lecturerid).getLecturerCourses().contains(courseid))
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
			System.out.println( "fitTest => " +fittest.getFitness() + " || fitness => " +getIndividual(i).getFitness());
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
		for (int i = 0; i < size(); i++)
		{
			System.out.println( "fitness["+i+"] => " +getIndividual(i).getFitness());
		}
	}

	/**
	 * @return the popIter
	 */
	public int getPopIter()
	{
		return popIter;
	}

	/**
	 * @param popIter the popIter to set
	 */
	public void setPopIter(int popIter)
	{
		this.popIter = popIter;
	}
}