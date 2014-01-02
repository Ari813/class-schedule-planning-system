package Algorithm;

import Controllers.*;

public class Individual
{

	static int workingDays = 6;
	static int dailyHours = 14;
	static int weeklyHours = workingDays * dailyHours; // =

	static int NumOfLecturers = 0;
	static int NumOfClasses = 0;
	static int NumOfCourses = 0;

	private Gene[][][][] genes;
	// Cache
	private double fitness = -1;
	private double selection = 0;

	public Individual()
	{
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		resetIndividual();
	}

	public Individual(Individual indv)
	{
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		copyIndividual(indv);
	}

	public Individual(int NumOfLecturers, int NumOfClasses, int NumOfCourses)
	{
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		Individual.NumOfLecturers = NumOfLecturers;
		Individual.NumOfClasses = NumOfClasses;
		Individual.NumOfCourses = NumOfCourses;
		resetIndividual();

	}

	public void resetIndividual()
	{
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++)
					{ // NumOfCourses
						genes[H][L][R][C] = new Gene();

					}
	}

	public void copyIndividual(Individual indv)
	{
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++)
					{ // NumOfCourses
						{
							genes[H][L][R][C] = new Gene();
							genes[H][L][R][C].setIndex(indv.getGeneByIndex(H, L, R, C).getIndex());

							if (indv.getGeneByIndex(H, L, R, C).isEditable())
								genes[H][L][R][C].setEditable();
							else
							{
								genes[H][L][R][C].setUnEditable();
							}
							if (indv.getGeneByIndex(H, L, R, C).isGene())
								genes[H][L][R][C].setGene(indv.getGeneByIndex(H, L, R, C).getIndex());
							else
							{
								genes[H][L][R][C].clrGene();
							}

						}

					}
	}

	public void generateIndividual()
	{
		boolean geneSet = false;
		int i;
		for (int C = 0; C < NumOfCourses; C++)
		{
			geneSet = false;
			// NumOfCourses
			for (int L = 0; L < NumOfLecturers; L++)
			{
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
				{
					// NumOfClasses
					for (int H = 0; H < weeklyHours; H++)
					// weeklyHours
					{
						double gene = Math.round(Math.random());
						int editableHours = 0;
						int hoursForCourse = ManagerController.collageDB.getCourseByIndex(C).getAcademicHours();
						if (gene > 0.5)
						{
							if ((H + hoursForCourse) < Individual.weeklyHours)
							{
								for (i = 0; i < hoursForCourse; i++)
									if (genes[H + i][L][R][C].isEditable())
										editableHours++;
								if (editableHours == hoursForCourse)
									for (i = 0; i < hoursForCourse; i++)
									{
										genes[H + i][L][R][C].setGene(i);
										geneSet = true;

									}
							}
							if (geneSet)
								break;

						}

					}
					if (geneSet)
						break;
				}
				if (geneSet)
					break;
			}
		}
	}

	// Use this if you want to create individuals with different gene lengths
	public static void setDefaultWeekHours(int newWorkingDays, int newDailyHours)
	{
		workingDays = newWorkingDays;
		dailyHours = newDailyHours;
		weeklyHours = workingDays * dailyHours;

	}

	public Gene getGeneByID(int day, int hour, int LecturerID, int ClassID, int CourseID)
	{

		return genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
				.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)];
	}

	public Gene getGeneByID(int weekHour, int LecturerID, int ClassID, int CourseID)
	{

		return genes[weekHour][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping().getClassIndex(ClassID)][ManagerController.collageDB
				.getMapping().getCourseIndex(CourseID)];

	}

	public Gene getGeneByIndex(int weeklyHour, int LecturerIndex, int ClassIndex, int CourseIndex)
	{

		return genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex];
	}

	public void setGeneByID(int day, int hour, int LecturerID, int ClassID, int CourseID, int index)
	{

		genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
				.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setGene(index);

	}

	public void setGeneByID(int day, int hour, int LecturerID, int ClassID, int CourseID, int index, boolean isEditable)
	{

		genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
				.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setGene(index);
		if (isEditable)
			genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
					.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setEditable();
		else
			genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
					.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setUnEditable();

	}

	public void setGeneByIndex(int weeklyHour, int LecturerIndex, int ClassIndex, int CourseIndex, int geneIndex)
	{

		genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex].setGene(geneIndex);
	}

	public void clrGeneByID(int day, int hour, int LecturerID, int ClassID, int CourseID)
	{

		genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
				.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].clrGene();
	}

	public void clrGeneByID(int day, int hour, int LecturerID, int ClassID, int CourseID, boolean isEditable)
	{

		genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
				.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].clrGene();
		if (isEditable)
			genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
					.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setEditable();
		else
			genes[ManagerController.collageDB.getMapping().getTime(day, hour)][ManagerController.collageDB.getMapping().getLecturerIndex(LecturerID)][ManagerController.collageDB.getMapping()
					.getClassIndex(ClassID)][ManagerController.collageDB.getMapping().getCourseIndex(CourseID)].setUnEditable();
	}

	public void clrGeneByIndex(int weeklyHour, int LecturerIndex, int ClassIndex, int CourseIndex)
	{

		genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex].clrGene();
	}

	/*
	 * public void setGene(int index, byte value) { genes[index] = value;
	 * fitness = 0; }
	 */

	/* Public methods */
	public int[] size()
	{
		int[] sizeArr = new int[4];

		sizeArr[0] = weeklyHours;
		sizeArr[1] = NumOfLecturers;
		sizeArr[2] = NumOfClasses;
		sizeArr[3] = NumOfCourses;

		return sizeArr;
	}

	public double getFitness()
	{
		if (fitness == -1)
		{
			fitness = FitnessCalc.getFitness(this);
		}
		
		return fitness;
	}

	public void SetSelection(double select)
	{
		selection = select;

	}

	public double getSelection()
	{

		return selection;
	}

}