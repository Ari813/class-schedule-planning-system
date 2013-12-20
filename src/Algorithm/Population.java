package Algorithm;

public class Population {
	private Individual JumpStartIndividual ;
	
	Individual[] individuals;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialise) {
		individuals = new Individual[populationSize];
		// Initialise population
		if (initialise) {
			// Loop and create individuals
			JumpStartIndividual = new Individual(
					MainGA.collageDB.getLecturersSize(),
					MainGA.collageDB.getClassesSize(), MainGA.collageDB.getCoursesSize());
			JumpStart();
			for (int i = 0; i < size(); i++) {
				Individual newIndividual = new Individual(
						MainGA.collageDB.getLecturersSize(),
						MainGA.collageDB.getClassesSize(), MainGA.collageDB.getCoursesSize());
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	private void JumpStart() {
		// TODO Auto-generated method stub
		for ()
	}

	/* Getters */
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public Individual getFittest() {
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest
		for (int i = 0; i < size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	/* Public methods */
	// Get population size
	public int size() {
		return individuals.length;
	}

	// Save individual
	public void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}
}