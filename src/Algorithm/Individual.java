package Algorithm;

public class Individual {

	static int workingDays = 6;
	static int dailyHours = 14;
	static int weeklyHours = workingDays * dailyHours; // =

	static int NumOfLecturers = 0;
	static int NumOfClasses = 0;
	static int NumOfCourses = 0;

	private Gene[][][][] genes;
	// Cache
	private double fitness = 0;
	private double selection = 0;

	public Individual() {
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		resetIndividual();
	}

	public Individual(Individual indv) {
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		copyIndividual(indv);
	}

	public Individual(int NumOfLecturers, int NumOfClasses, int NumOfCourses) {
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		Individual.NumOfLecturers = NumOfLecturers;
		Individual.NumOfClasses = NumOfClasses;
		Individual.NumOfCourses = NumOfCourses;
		resetIndividual();

	}

	/*
	 * / Create a random individual public void generateIndividual() { for (int
	 * i = 0; i < size(); i++) { byte gene = (byte) Math.round(Math.random());
	 * genes[i] = gene; } } /
	 */
	// ?

	public void resetIndividual() {
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++) { // NumOfCourses
						{
							genes[H][L][R][C].clrGene();
							genes[H][L][R][C].setEditable();
						}

					}
	}

	public void copyIndividual(Individual indv) {
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++) { // NumOfCourses
						{
							if (indv.getGeneByIndex(H, L, R, C).isEditable())
								genes[H][L][R][C].setEditable();
							else {
								genes[H][L][R][C].setUnEditable();
							}
							if (indv.getGeneByIndex(H, L, R, C).isGene())
								genes[H][L][R][C].setGene();
							else {
								genes[H][L][R][C].clrGene();
							}

						}

					}
	}

	public void generateIndividual() {
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++) { // NumOfCourses
						int gene = (int) Math.round(Math.random());
						if (genes[H][L][R][C].isEditable()) {
							if (gene == 0)
								genes[H][L][R][C].setGene();
						}

					}
	}

	// Use this if you want to create individuals with different gene lengths
	public static void setDefaultWeekHours(int newWorkingDays, int newDailyHours) {
		workingDays = newWorkingDays;
		dailyHours = newDailyHours;
		weeklyHours = workingDays * dailyHours;

	}

	public Gene getGeneByID(int day, int hour, int LecturerID, int ClassID,
			int CourseID) {

		return genes[MainGA.collageDB.getMapping().getTime(day, hour)][MainGA.collageDB
				.getMapping().getLecturerIndex(LecturerID)][MainGA.collageDB
				.getMapping().getClassIndex(ClassID)][MainGA.collageDB
				.getMapping().getCourseIndex(CourseID)];
	}

	public Gene getGeneByID(int weekHour, int LecturerID, int ClassID,
			int CourseID) {

		return genes[weekHour][MainGA.collageDB.getMapping().getLecturerIndex(
				LecturerID)][MainGA.collageDB.getMapping().getClassIndex(
				ClassID)][MainGA.collageDB.getMapping()
				.getCourseIndex(CourseID)];
	}

	public Gene getGeneByIndex(int weeklyHour, int LecturerIndex,
			int ClassIndex, int CourseIndex) {

		return genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex];
	}

	public void setGeneByID(int day, int hour, int LecturerID, int ClassID,
			int CourseID) {

		genes[MainGA.collageDB.getMapping().getTime(day, hour)][MainGA.collageDB
				.getMapping().getLecturerIndex(LecturerID)][MainGA.collageDB
				.getMapping().getClassIndex(ClassID)][MainGA.collageDB
				.getMapping().getCourseIndex(CourseID)].setGene();
	}

	public void setGeneByIndex(int weeklyHour, int LecturerIndex,
			int ClassIndex, int CourseIndex) {

		genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex].setGene();
	}

	public void clrGeneByID(int day, int hour, int LecturerID, int ClassID,
			int CourseID) {

		genes[MainGA.collageDB.getMapping().getTime(day, hour)][MainGA.collageDB
				.getMapping().getLecturerIndex(LecturerID)][MainGA.collageDB
				.getMapping().getClassIndex(ClassID)][MainGA.collageDB
				.getMapping().getCourseIndex(CourseID)].clrGene();
	}

	public void clrGeneByIndex(int weeklyHour, int LecturerIndex,
			int ClassIndex, int CourseIndex) {

		genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex].clrGene();
	}

	/*
	 * public void setGene(int index, byte value) { genes[index] = value;
	 * fitness = 0; }
	 */

	/* Public methods */
	public int[] size() {
		int[] sizeArr = new int[4];

		sizeArr[0] = weeklyHours;
		sizeArr[1] = NumOfLecturers;
		sizeArr[2] = NumOfClasses;
		sizeArr[3] = NumOfCourses;

		return sizeArr;
	}

	public double getFitness() {
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	public void SetSelection(double select) {
		selection = select;

	}

	public double getSelection() {

		return selection;
	}

}