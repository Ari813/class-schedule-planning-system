package Algorithm;

import java.util.Iterator;

import common.Settings;

public class FitnessCalc {

	// Calculate inidividuals fittness by comparing it to our candidate solution
	public static double getFitness(Individual individual) {
		double fitness = 0;
		int hard = 0;
		double soft = 0;

		hard = calcHardConstraints(individual);
		if (hard == 0) {
			soft = calcSoftConstraints(individual);
			fitness = (1.0 / (1.0 + hard)) + (1.0 / (1.0 + soft));
		} else {
			fitness = (1.0 / (1.0 + hard));
		}

		return fitness;
	}

	private static double calcSoftConstraints(Individual individual) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int calcHardConstraints(Individual individual) {
		double HardConstraints=0;
		int lecID;
		Iterator<Integer> LecItr = MainGA.collageDB.getLecturersKeys().iterator();
		while (LecItr.hasNext())
		{
			lecID= LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(lecID);
			for(int Hours=0; Hours<Individual.weeklyHours;Hours++)
					for (int ClssIndex=0; ClssIndex<Individual.NumOfClasses;ClssIndex++)
						for (int CourseIndex=0; CourseIndex<Individual.NumOfCourses;CourseIndex++){
						
							
						}
		}
		return 0;
	}

}