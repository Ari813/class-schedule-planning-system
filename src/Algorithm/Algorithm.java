package Algorithm;

import java.util.Random;

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    //private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = rouletteSelection(pop); // change to roulette selection
            Individual indiv2 = rouletteSelection(pop); // change to roulette selection
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int H = 0; H < Individual.weeklyHours; H++)
            // weeklyHours
            for (int L = 0; L < Individual.NumOfLecturers; L++)
             // NumOfLecturers
             for (int R = 0; R < Individual.NumOfClasses; R++)
              // NumOfClasses
              for (int C = 0; C < Individual.NumOfCourses; C++) { // NumOfCourses
            // Crossover
            if (Math.random() <= uniformRate) {
             if (indiv1.getGeneByIndex(H, L, R, C).isGene())
              newSol.setGeneByIndex(H,L,R,C);
             else {
              newSol.clrGeneByIndex(H,L,R,C);
       }
              
            } else {
             if (indiv2.getGeneByIndex(H, L, R, C).isGene())
              newSol.setGeneByIndex(H,L,R,C);
             else {
              newSol.clrGeneByIndex(H,L,R,C);
            }
        }}
        return newSol;
    }
     /*        
    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) { // 4d for run
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i)); // 4d indexing
            } else {
                newSol.setGene(i, indiv2.getGene(i)); // 4d indexing
            }
        }
        return newSol;
    }*/

    
 // Mutate an individual
    private static void mutate(Individual indiv) {
     // Loop through genes
     for (int H = 0; H < Individual.weeklyHours; H++)
      // weeklyHours
      for (int L = 0; L < Individual.NumOfLecturers; L++)
       // NumOfLecturers
       for (int R = 0; R < Individual.NumOfClasses; R++)
        // NumOfClasses
        for (int C = 0; C < Individual.NumOfCourses; C++) { // NumOfCourses
         if (Math.random() <= mutationRate) {
          // Create random gene
          // check if gene can be mutate
          if (indiv.getGeneByIndex(H, L, R, C).isEditable())
           if (indiv.getGeneByIndex(H, L, R, C).isGene())
            indiv.clrGeneByIndex(H, L, R, C);
           else {
            indiv.setGeneByIndex(H, L, R, C);
           }
         }
        }
    }
    
    /*/ Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) { // 4d for run
            if (Math.random() <= mutationRate) {
                // Create random gene
            	// check if gene can be mutate
                byte gene = (byte) Math.round(Math.random()); // invert gene
                indiv.setGene(i, gene); // 4d indexing
            }
        }
    }
 /*/
    // Select individuals for crossover
    
    
    
    /*/
    private Individual rouletteWheelSelection(Population pop){
    	  Random m_rand = new Random();
    	  double totalfitness =0;
      	for (int i = 0; i < pop.size() ; i++){
      		totalfitness+= pop.individuals[i].getFitness();}
        double randNum = Math.abs(m_rand.nextDouble() * totalfitness);
        int idx;
        for (idx=0;idx<pop.size() && randNum>0;++idx){
            randNum -= Math.abs(pop.individuals[idx].getFitness());
        }
        return pop.individuals[pop.size()-idx];
    } 
    
    
    
    
    /*/
    
    private static Individual rouletteSelection(Population pop) {
    	//Calculate the total fitness 
    	Random m_rand = new Random();
    	
    	double randNum = Math.abs(m_rand.nextDouble());
    	double totalfitness =0;
    	for (int i = 0; i < pop.size() ; i++){
    		totalfitness+= pop.individuals[i].getFitness();
    		    	}		
    	for (int i = 0; i < pop.size() ; i++){
    		double tmp=pop.individuals[i].getFitness()/(totalfitness);
    		pop.individuals[i].SetSelection(tmp);
    		  
    	}
    	
    	for (int i = 0; i < pop.size()  ; i++){
    		randNum-=pop.individuals[i].getSelection();
    		if(randNum<0)
    			return(pop.individuals[i]);
    	}
return(pop.individuals[pop.size()]);
					}
    	
    	
    	/*/
    	 * 
    
    	
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }	 */
}