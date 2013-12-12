package Algorithm;

public class Individual {

	static int workingDays = 6;
	static int dailyHours = 14;
	static int weeklyHours = workingDays * dailyHours; // =
														// workingDays*dailyHours

	private Gene[][][][] genes;
	// Cache
	private int fitness = 0;

	public Individual(int NumOfLecturers, int NumOfClasses, int NumOfCourses) {
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
	}

	// Create a random individual
	public void generateIndividual() {
		for (int i = 0; i < size(); i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}

	/* Getters and setters */
	// Use this if you want to create individuals with different gene lengths
	public static void setDefaultWeekHours(int newWorkingDays, int newDailyHours) {
		workingDays = newWorkingDays;
		dailyHours = newDailyHours;
		weeklyHours = workingDays * dailyHours;

	}

	public byte getGene(int index) {
		return genes[index];
	}

	public void setGene(int index, byte value) {
		genes[index] = value;
		fitness = 0;
	}

	/* Public methods */
	public int size() {
		return genes.length;
	}

	public int getFitness() {
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	@Override
	public String toString() {
		String geneString = "";
		for (int i = 0; i < size(); i++) {
			geneString += getGene(i);
		}
		return geneString;
	}
}