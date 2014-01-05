package Algorithm;

public class GeneratePopulationAlgorithm implements Runnable
{
	private Individual newIndividual;
	private Population population;
	private int indivIndex;
	private Individual JumpStartIndividual;

	public GeneratePopulationAlgorithm(Population population, int i,Individual indiv )
	{
		indivIndex = i;
		JumpStartIndividual =indiv;
		this.population = population;

	}
	@Override
	public void run()
	{
		newIndividual = new Individual(JumpStartIndividual);
		newIndividual.generateIndividual();
		population.saveIndividual(indivIndex, newIndividual);
		
	}

}
