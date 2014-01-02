package Algorithm;

public class GeneticAlgorithmRun extends Thread
{
	private Population collagePop;
	private boolean keepRunning;
	private boolean isRunning;

	public GeneticAlgorithmRun()
	{
		collagePop = new Population(50, true);
		keepRunning = true;
		setRunning(false);
	}

	public GeneticAlgorithmRun(Individual indv,int popSize)
	{
		collagePop = new Population(popSize, indv);
		keepRunning = true;
		setRunning(false);
	}

	public void run()
	{
		setRunning(true);
		runAlgorithm();
	}

	private void runAlgorithm()
	{
		int loop = 0;
		
		while (keepRunning)
		{
			System.out.println("running... ("+ loop++ +")");
			if (collagePop.getFittest().getFitness() > 1)
				printSolution(collagePop.getFittest());

			collagePop = Algorithm.evolvePopulation(collagePop);
			
		}
		setRunning(false);
	}

	private void printSolution(Individual individual)
	{
		PrintSolution ps = new PrintSolution(individual);
		ps.start();
	}

	public synchronized void stopRunning()
	{
		keepRunning = false;
	}

	/**
	 * @return the isRunning
	 */
	public boolean isRunning()
	{
		return isRunning;
	}

	/**
	 * @param isRunning the isRunning to set
	 */
	private void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}
}
