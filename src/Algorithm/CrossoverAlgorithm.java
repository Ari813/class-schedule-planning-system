package Algorithm;

import java.util.Random;

import Controllers.ManagerController;

public class CrossoverAlgorithm implements Runnable
{
	Population population;
	Population newPopulation;
	int indivNewIndex;

	public CrossoverAlgorithm(Population population, Population newPopulation, int indivNewIndex)
	{
		this.indivNewIndex = indivNewIndex;
		this.population = population;
		this.newPopulation = newPopulation;
	}

	@Override
	public void run()
	{
		Individual indiv1 = rouletteSelection(population);
		Individual indiv2 = rouletteSelection(population);
		Individual newSol = new Individual();
		int i;

		// Loop through genes

		for (int L = 0; L < Individual.NumOfLecturers; L++)
			// NumOfLecturers
			for (int R = 0; R < Individual.NumOfClasses; R++)
				// NumOfClasses
				for (int C = 0; C < Individual.NumOfCourses; C++)
					// NumOfCourses
					for (int H = 0; H < Individual.weeklyHours; H++)
					// weeklyHours
					{
						// Crossover
						if (Math.random() <= Algorithm.uniformRate)
						{
							if (indiv1.getGeneByIndex(H, L, R, C).getIndex() == 0)
							{

								for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
								{
									if (!indiv1.getGeneByIndex(H + i, L, R, C).isEditable())
										newSol.getGeneByIndex(H + i, L, R, C).setUnEditable();
									newSol.setGeneByIndex(H + i, L, R, C, i);
								}
								H += (i-1);
							}

						} else
						{
							if (indiv2.getGeneByIndex(H, L, R, C).getIndex() == 0)
							{
								for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
								{
									if (!indiv2.getGeneByIndex(H + i, L, R, C).isEditable())
										newSol.getGeneByIndex(H + i, L, R, C).setUnEditable();
									newSol.setGeneByIndex(H + i, L, R, C, i);
								}
								H += (i-1);

							}
						}

					}
		newPopulation.saveIndividual(indivNewIndex, newSol);
	}

	private Individual rouletteSelection(Population pop)
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
}
