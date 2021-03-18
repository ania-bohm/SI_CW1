import java.util.Random;

public class GeneticAlgorithm {
    private int populationSize;
    private int numberOfGenerations;
    private float px;
    private float pm;
    private int tournamentSize;
    public PCB pcb;

    public GeneticAlgorithm(int populationSize, int numberOfGenerations, float px, float pm, int tournamentSize, PCB pcb) {
        this.populationSize = populationSize;
        this.numberOfGenerations = numberOfGenerations;
        this.px = px;
        this.pm = pm;
        this.tournamentSize = tournamentSize;
        this.pcb = pcb;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }

    public float getPx() {
        return px;
    }

    public void setPx(float px) {
        this.px = px;
    }

    public float getPm() {
        return pm;
    }

    public void setPm(float pm) {
        this.pm = pm;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public Population initializePopulation() {
        Population population = new Population(populationSize);
        population.populateRandom(pcb);
        return population;
    }

    // selection type - 0 - tournament, 1 - roulette
    public Individual startEvolution(int selectionType, int[] bestFitnessInGeneration, float[] globalBestFitness) {
        int iteration;
        Population population;
        Individual bestIndividual;
        Random random;

        if (selectionType != 0 && selectionType != 1) {
            return null;
        }

        iteration = 0;
        population = initializePopulation();
        population.calculatePopulationFitness(pcb);
        bestIndividual = new Individual(population.findBestIndividual());
        globalBestFitness[0] = bestIndividual.getFitness();
        random = new Random();

        while (iteration < numberOfGenerations) {
            Population nextPopulation = new Population(populationSize);

            // finding new best individual in population
            if (population.findBestIndividual().getFitness() < bestIndividual.getFitness()) {
                bestIndividual = new Individual(population.findBestIndividual());
            }
            globalBestFitness[iteration] = bestIndividual.getFitness();

            for (int i = 0; i < populationSize; i++) {
                Individual firstParent;
                Individual secondParent;
                Individual child;
                float probability;

                // selection
                if (selectionType == 0) {
                    firstParent = new Individual(population.tournament(tournamentSize));
                    secondParent = new Individual(population.tournament(tournamentSize));
                } else {
                    firstParent = new Individual(population.roulette());
                    secondParent = new Individual(population.roulette());
                }

                // crossover
                probability = random.nextFloat();
                if (probability < px) {
                    child = new Individual(firstParent.crossover(secondParent));
                } else {
                    child = new Individual(firstParent);
                }

                // mutation
                child = new Individual(child.mutation(pm));

                nextPopulation.getIndividualList().add(new Individual(child));
            }
            population = new Population(nextPopulation);
            population.calculatePopulationFitness(pcb);

            bestFitnessInGeneration[iteration] = population.findBestIndividual().getFitness();

            iteration++;
        }
        return bestIndividual;
    }
}
