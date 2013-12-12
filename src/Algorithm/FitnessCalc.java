package Algorithm;

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
		// TODO Auto-generated method stub
		return 0;
	}

}