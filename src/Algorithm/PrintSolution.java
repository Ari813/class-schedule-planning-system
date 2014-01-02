package Algorithm;

import Controllers.ManagerController;

public class PrintSolution extends Thread
{

	Individual solution;

	public PrintSolution(Individual solution)
	{
		this.solution = solution;
	}

	public void run()
	{
		System.out.println("fitness=(" + solution.getFitness() + ")");
		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
						if (solution.getGeneByIndex(H, L, R, C).isGene())
							System.out.println("hour = " + H + "| Lecturer = " + ManagerController.collageDB.getLecturerByIndex(L).getName() + "| Class = "
									+ ManagerController.collageDB.getClassByIndex(R).getDescription() + "| Course = " + ManagerController.collageDB.getCourseByIndex(C).getDescription());
		System.out.println("found!!!!");
	}
}
