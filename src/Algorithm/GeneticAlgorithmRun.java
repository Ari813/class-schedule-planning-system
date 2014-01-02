package Algorithm;

import javax.security.auth.login.FailedLoginException;

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
		collagePop.setPopIter(0);
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
			
			System.out.println("running... ("+ loop++ +") popIter ="+collagePop.getPopIter());
			collagePop.printfitness();
			if (collagePop.getFittest().getFitness() >= 1)
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
