import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private List<Individual> individualList;
    private int populationSize;

    public Population(int populationSize) {
        this.populationSize = populationSize;
        individualList = new ArrayList<>();
    }

    public Population(Population population) {
        this.individualList = new ArrayList<>();
        this.populationSize = population.getPopulationSize();
        for (int i = 0; i < population.getPopulationSize(); i++) {
            this.individualList.add(new Individual(population.getIndividualList().get(i)));
        }
    }

    public List<Individual> getIndividualList() {
        return individualList;
    }

    public void setIndividualList(List<Individual> individualList) {
        this.individualList.clear();
        for (int i = 0; i < individualList.size(); i++) {
            this.individualList.add(new Individual(individualList.get(i)));
        }
        this.populationSize = individualList.size();
    }

    public int getPopulationSize() {
        return populationSize;
    }

//    public void setPopulationSize(int populationSize) {
//        this.populationSize = populationSize;
//    }

    public void populateRandom(PCB pcb) {
        individualList.clear();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(pcb.getConnectionTab());
            individualList.add(individual.generateRandomIndividual());
        }
    }

//    public void populateGreedy(Problem problem) {
//        Random random = new Random();
//        int startLocation;
//        for (int i = 0; i < populationSize; i++) {
//            startLocation = random.nextInt(problem.getDimension() - 1) + 1;
//            Individual individual = new Individual(problem.getDimension());
//            individualList.add(GreedyAlgorithm.generateGreedyIndividual(problem, startLocation));
//        }
//    }

//    public void printPopulation() {
//        for (Individual individual : individualList) {
//            individual.printRouteArray();
//        }
//    }

    public void calculatePopulationFitness(PCB pcb) {
        for (int i = 0; i < populationSize; i++) {
            individualList.get(i).setFitness(pcb.calculateFitness(individualList.get(i)));
        }
    }

    public Individual findBestIndividual() {
        float bestFitness = Float.MAX_VALUE;
        int bestPosition = -1;
        for (int i = 0; i < individualList.size(); i++) {
            if (individualList.get(i).getFitness() < bestFitness) {
                bestFitness = individualList.get(i).getFitness();
                bestPosition = i;
            }
        }
        return individualList.get(bestPosition);
    }

    public Individual findWorstIndividual() {
        float worstFitness = Float.MIN_VALUE;
        int worstPosition = -1;
        for (int i = 0; i < individualList.size(); i++) {
            if (individualList.get(i).getFitness() > worstFitness) {
                worstFitness = individualList.get(i).getFitness();
                worstPosition = i;
            }
        }
        return individualList.get(worstPosition);
    }

    public float averageFitness() {
        float average = 0;
        for (int i = 0; i < populationSize; i++) {
            average += individualList.get(i).getFitness();
        }
        average = average / populationSize;
        return average;
    }

    public Individual tournament(int tournamentSize) {
        List<Individual> allIndividuals = new ArrayList<>(individualList);
        List<Individual> drawnIndividuals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < tournamentSize; i++) {
            int randomPosition = random.nextInt(allIndividuals.size());
            drawnIndividuals.add(allIndividuals.remove(randomPosition));
        }
        Individual bestIndividual = new Individual(drawnIndividuals.get(0));
        float bestFitness = drawnIndividuals.get(0).getFitness();
        for (int i = 1; i < tournamentSize; i++) {
            if (drawnIndividuals.get(i).getFitness() < bestFitness) {
                bestIndividual = new Individual(drawnIndividuals.get(i));
                bestFitness = drawnIndividuals.get(i).getFitness();
            }
        }
        return bestIndividual;
    }

    public Individual roulette() {
        float wholeQuality = 0;
        float bestFitness = findBestIndividual().getFitness();
        float worstFitness = findWorstIndividual().getFitness();
        float[] individualQuality = new float[populationSize];

        for (int i = 0; i < populationSize; i++) {
            individualQuality[i] = worstFitness - individualList.get(i).getFitness() + bestFitness;
        }

        for (int i = 0; i < populationSize; i++) {
            wholeQuality += individualQuality[i];
        }
        Random random = new Random();
        int randomNumber = random.nextInt((int) wholeQuality - 1);
        float currentQualitySum = 0;

        for (int i = 0; i < populationSize; i++) {
            currentQualitySum += individualQuality[i];
            if (randomNumber < currentQualitySum) {
                return individualList.get(i);
            }
        }
        return individualList.get(populationSize - 1);
    }
}
