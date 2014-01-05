package Algorithm;


import Controllers.ManagerController;

public class MutateAlgorithm implements Runnable
{
	private Individual indiv;
	private Population population;
	private int indivIndex;

	public MutateAlgorithm(Population population, int i)
	{
		indiv = population.getIndividual(i);
		indivIndex = i;
		this.population = population;

	}

	public void run()
	{
		boolean canMutate;
		int i;
		int courseHours;
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
							{

								if (Math.random() <= Algorithm.uniformRate)
								{
									courseHours = ManagerController.collageDB.getCourseByIndex(C).getAcademicHours();
									if (indiv.getGeneByIndex(H, L, R, C).getIndex() == 0)
									{
										
										for (i = courseHours-1; i >=0; i--)
											if (indiv.getGeneByIndex(H, L, R, C).isGene())
											{
												indiv.clrGeneByIndex(H + i, L, R, C);
											} else
											{
												
												indiv.setGeneByIndex(H + i, L, R, C, i);
											}
									} else if (indiv.getGeneByIndex(H, L, R, C).getIndex() == -1)
									{
										
										if (courseHours+H < Individual.weeklyHours)
										{
											canMutate = true;	
										
											for (i = 0; i < courseHours; i++)
												if (indiv.getGeneByIndex(H+i, L, R, C).isGene())
													canMutate = false;
											
											if (canMutate)
											{
												for (i = 0; i < courseHours; i++)
													indiv.setGeneByIndex(H + i, L, R, C,i);
											}
										}
												
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
