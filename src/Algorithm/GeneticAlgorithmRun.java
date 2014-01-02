package Algorithm;

import Controllers.ManagerController;

public class GeneticAlgorithmRun extends Thread
{
	private Population collagePop;
	private boolean keepRunning;
	private boolean isRunning;
	private ManagerController manager;

	public GeneticAlgorithmRun()
	{
		collagePop = new Population(50, true);
		keepRunning = true;
		setRunning(false);
	}
	
	public GeneticAlgorithmRun(Individual indv,int popSize, ManagerController managerController)
	{
		collagePop = new Population(popSize, indv);
		keepRunning = true;
		setRunning(false);
		this.manager =managerController;
	}

	public void run()
	{
		setRunning(true);
		runAlgorithm();
	}

	private void runAlgorithm()
	{
		int loop = 0;
		double fittest;
		while (keepRunning)
		{
			
			fittest = collagePop.getFittest().getFitness();
			//collagePop.printfitness();
			if (!(loop==0))
				manager.updateProgressBar(fittest);
			manager.updatePopCounter(loop++);
			if (fittest >= 1)
				printSolution(collagePop.getFittest());

			try
			{
				collagePop = Algorithm.evolvePopulation(collagePop);
			} catch (InterruptedException e)
			{
				setRunning(false);
				System.out.println("failed to process algorithm!");
			}
			
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
