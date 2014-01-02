package Algorithm;

import Controllers.ManagerController;

public class MutateAlgorithm implements Runnable
{
	Individual indiv;
	Population population;
	int indivIndex;

	public MutateAlgorithm(Population population, int i)
	{
		indiv = population.getIndividual(i);
		indivIndex = i;
		this.population = population;
	}

	public void run()
	{
		int i;
		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
					{ // NumOfCourses
						if (Math.random() <= Algorithm.mutationRate)
						{
							// Create random gene
							// check if gene can be mutate
							if (indiv.getGeneByIndex(H, L, R, C).isEditable())
								if (Math.random() <= Algorithm.uniformRate)
								{
									if (indiv.getGeneByIndex(H, L, R, C).getIndex() == 0)
									{
										for (i = 0; i < ManagerController.collageDB.getCourseByIndex(C).getAcademicHours(); i++)
											if (indiv.getGeneByIndex(H, L, R, C).isGene())
											{
												indiv.clrGeneByIndex(H + i, L, R, C);
											} else
											{
												indiv.setGeneByIndex(H + i, L, R, C);
											}
									}
								}
						}
					}
		saveIndividual();

	}

	private synchronized void saveIndividual()
	{
		population.saveIndividual(indivIndex, indiv);

	}

}
