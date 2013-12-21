package Algorithm;

import java.util.Iterator;

public class FitnessCalc {

	// Calculate individuals fitness by comparing it to our candidate solution
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
		int HardConstraints = 0;
		int lecID;
		int classID;
		int courseID;
		int counter;
		/// check how many times a lecturer teaches in an hour.
		Iterator<Integer> LecItr = MainGA.collageDB.getLecturersKeys().iterator();
				
		while (LecItr.hasNext()) {
			lecID = LecItr.next().intValue();
			int lecturerIndex = MainGA.collageDB.getMapping().getLecturerIndex(
					lecID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) 
			{
				counter = 0;
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++) 
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++) 
					{
						if (individual.getGeneByIndex(Hours, lecturerIndex,	ClassIndex, CourseIndex).isGene()) 
						{
							counter++;
						}
					}
				}
				HardConstraints += counter-1;
				
			}
		}
		
		/// check how many times a class is taken in an hour
		Iterator<Integer> classItr = MainGA.collageDB.getClassesKeys().iterator();
		while (classItr.hasNext()) {
			classID = classItr.next().intValue();
			int classIndex = MainGA.collageDB.getMapping().getClassIndex(classID);
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) 
			{
				counter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++) 
				{
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++) 
					{
						if (individual.getGeneByIndex(Hours, LecturerIndex,	classIndex, CourseIndex).isGene()) 
						{
							counter++;
						}
					}
				}
				HardConstraints += counter-1;
				
			}
		}
		
		/// check how many times a course has been assigned in an hour 
				Iterator<Integer> courseItr = MainGA.collageDB.getCoursesKeys().iterator();
				while (courseItr.hasNext()) {
					courseID = courseItr.next().intValue();
					int courseIndex = MainGA.collageDB.getMapping().getCourseIndex(courseID);
					for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) 
					{
						counter = 0;
						for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++) 
						{
							for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++) 
							{
								if (individual.getGeneByIndex(Hours, LecturerIndex,	ClassIndex, courseIndex).isGene()) 
								{
									counter++;
								}
							}
						}
						HardConstraints += counter-1;
						
					}
				}
		return HardConstraints;
	}

}