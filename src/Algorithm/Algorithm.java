package Algorithm;

import java.util.Random;

import Controllers.*;

public class Algorithm
{

	/* GA parameters */
	static double uniformRate = 0.5;
	static double mutationRate = 0.0015;
	
	// private static final int tournamentSize = 5;

	/* Public methods */

	// Evolve a population
	public static Population evolvePopulation(Population pop) throws InterruptedException
	{
		Population newPopulation = new Population(pop.size(), false);
		Population SavePopulation;
		Thread[] CrossoverAlgorithmArray = new Thread[pop.size()];
		Thread[] MutateAlgorithmArray = new Thread[pop.size()];
		// Keep our best individual

		// Crossover population

		// Loop over the population size and create new individuals with
		// crossover
		for (int i = 0; i < pop.size(); i++)
		{

			CrossoverAlgorithm crossOverThread = new CrossoverAlgorithm(pop,newPopulation, i);

			CrossoverAlgorithmArray[i] = new Thread(crossOverThread, "Crossover " + i);
			CrossoverAlgorithmArray[i].start();
		}
		for (int i = 0; i < pop.size(); i++)
			CrossoverAlgorithmArray[i].join();

		// Mutate population
		for (int i = 0; i < newPopulation.size(); i++)
		{
			MutateAlgorithm mutateThread = new MutateAlgorithm(newPopulation, i);
			
			MutateAlgorithmArray[i] = new Thread(mutateThread, "Mutate " + i);
			MutateAlgorithmArray[i].start();

			// mutate(newPopulation.getIndividual(i));
		}
		for (int i = 0; i < pop.size(); i++)
			MutateAlgorithmArray[i].join();

		SavePopulation = Replacement(newPopulation, pop);
		SavePopulation.setPopIter(pop.getPopIter()+1);
		return SavePopulation;
	}

	public static Population bubbleSort(Population pop)
	{
		Individual temp = new Individual();
		for (int a = 1; a < pop.size(); a++)
		{
			for (int b = 0; b < pop.size() - a; b++)
			{
				if ((pop.getIndividual(b).getFitness() > pop.getIndividual(b + 1).getFitness()))
				{

					// swap movies[b] with movies[b+1]
					temp = pop.getIndividual(b);
					pop.saveIndividual(b, pop.getIndividual(b + 1));
					pop.saveIndividual(b + 1, temp);
				}
			}
		}
		return pop;
	}

	private static Population Replacement(Population newPopulation, Population pop)
	{
		int oldpop = 0, newpop = 0;
		Population SavePopulation = new Population(pop.size(), false);
		pop = bubbleSort(pop);
		newPopulation = bubbleSort(newPopulation);
		for (int i = 0; i < pop.size(); i++)
		{
			if (pop.getIndividual(oldpop).getFitness() > newPopulation.getIndividual(newpop).getFitness())
			{
				SavePopulation.saveIndividual(i, pop.getIndividual(oldpop));
				oldpop++;
			} else
			{

				SavePopulation.saveIndividual(i, newPopulation.getIndividual(newpop));
				newpop++;
			}

		}
		return SavePopulation;

	}
	
@Deprecated
	private static Individual crossover(Individual indiv1, Individual indiv2)
	{
		Individual newSol = new Individual();
		int i;
		// Loop through genes
		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
					{ // NumOfCourses
						// Crossover
						if (Math.random() <= uniformRate)
						{
							if (indiv1.getGeneByIndex(H, L, R, C).getIndex() == 0)
							{

								for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
									if (indiv1.getGeneByIndex(H, L, R, C).isGene())
									{
										newSol.setGeneByIndex(H + i, L, R, C, i);
									} else
									{
										newSol.clrGeneByIndex(H + i, L, R, C);
									}
							}

						} else
						{
							if (indiv2.getGeneByIndex(H, L, R, C).getIndex() == 0)
							{

								for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
									if (indiv2.getGeneByIndex(H, L, R, C).isGene())
									{
										newSol.setGeneByIndex(H + i, L, R, C, i);
									} else
									{
										newSol.clrGeneByIndex(H + i, L, R, C);
									}
							}
						}
					}
		return newSol;
	}

	/*
	 * // Crossover individuals private static Individual crossover(Individual
	 * indiv1, Individual indiv2) { Individual newSol = new Individual(); //
	 * Loop through genes for (int i = 0; i < indiv1.size(); i++) { // 4d for
	 * run // Crossover if (Math.random() <= uniformRate) { newSol.setGene(i,
	 * indiv1.getGene(i)); // 4d indexing } else { newSol.setGene(i,
	 * indiv2.getGene(i)); // 4d indexing } } return newSol; }
	 */

	// Mutate an individual
	@Deprecated
	private static void mutate(Individual indiv)
	{
		// Loop through genes
		int i;
		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
					{ // NumOfCourses
						if (Math.random() <= mutationRate)
						{
							// Create random gene
							// check if gene can be mutate
							if (indiv.getGeneByIndex(H, L, R, C).isEditable())
								if (Math.random() <= uniformRate)
								{
									if (indiv.getGeneByIndex(H, L, R, C).getIndex() == 0)
									{
										for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
											if (indiv.getGeneByIndex(H, L, R, C).isGene())
											{
												indiv.clrGeneByIndex(H + i, L, R, C);
											} else
											{
												indiv.setGeneByIndex(H + i, L, R, C, i);
											}
									}
								}
						}
					}
	}

	@Deprecated
	private static Individual rouletteSelection(Population pop)
	{
		// Calculate the total fitness
		Random m_rand = new Random();

		double randNum = Math.abs(m_rand.nextDouble());
		double totalfitness = 0;
		for (int i = 0; i < pop.size(); i++)
		{
			totalfitness += pop.individuals[i].getFitness();
		}
		for (int i = 0; i < pop.size(); i++)
		{
			double tmp = pop.individuals[i].getFitness() / (totalfitness);
			pop.individuals[i].SetSelection(tmp);

		}

		for (int i = 0; i < pop.size(); i++)
		{
			randNum -= pop.individuals[i].getSelection();
			if (randNum < 0)
				return (pop.individuals[i]);
			
		}
		return (pop.individuals[pop.size()]);
	}

	/*
	 * /
	 * 
	 * 
	 * 
	 * // Create a tournament population Population tournament = new
	 * Population(tournamentSize, false); // For each place in the tournament
	 * get a random individual
	 * 
	 * for (int i = 0; i < tournamentSize; i++) { int randomId = (int)
	 * (Math.random() * pop.size()); tournament.saveIndividual(i,
	 * pop.getIndividual(randomId)); } // Get the fittest Individual fittest =
	 * tournament.getFittest(); return fittest; }
	 */
}