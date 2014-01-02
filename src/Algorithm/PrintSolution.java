package Algorithm;

public class PrintSolution extends Thread
{

	Individual solution;
	
	public PrintSolution(Individual solution)
	{
		this.solution = solution;
	}
	
	public void run ()
	{
		System.out.println("found!!!!");
	}
}
