package Algorithm;

public class Individual {

	static int workingDays = 6;
	static int dailyHours = 14;
	static int weeklyHours = workingDays * dailyHours; // =
														// workingDays*dailyHours
	static int NumOfLecturers= 0;
	static int NumOfClasses= 0;
	static int NumOfCourses= 0;
	
	private Gene[][][][] genes;
	// Cache
	private int fitness = 0;

	public Individual(int NumOfLecturers, int NumOfClasses, int NumOfCourses) {
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		this.NumOfLecturers=NumOfLecturers;
		this.NumOfClasses=NumOfClasses;
		this.NumOfCourses=NumOfCourses;
		
	}

	/*/ Create a random individual
	public void generateIndividual() {
		for (int i = 0; i < size(); i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}
/*/
	//?
	 public void generateIndividual() {
	 for (int H = 0; H < weeklyHours; H++) //weeklyHours
	 	for (int L = 0; L < NumOfLecturers; L++) //NumOfLecturers
	 		for (int R = 0; R < weeklyHours; R++) //NumOfClasses
	 			for (int C = 0; C < weeklyHours; C++){ //NumOfCourses
	 				int gene = (int) Math.round(Math.random());
	 				if (genes[H][L][R][C].isEditable()){
	 					if (gene==0)
	 						genes[H][L][R][C].setGene();
	 				}
	 				
	 				
	 			}
	 		}
	 
	 /*/
	
	
	/* Getters and setters */
	// Use this if you want to create individuals with different gene lengths
	public static void setDefaultWeekHours(int newWorkingDays, int newDailyHours) {
		workingDays = newWorkingDays;
		dailyHours = newDailyHours;
		weeklyHours = workingDays * dailyHours;

	}

	public Gene getGene(int day,int hour,int LecturersID, int ClassesID, int CoursesID) {
		
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