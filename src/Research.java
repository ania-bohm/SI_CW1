import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Research {

    public void populationSizeResearch(PCB pcb) throws IOException {
        for (int i = 100; i < 1001; i += 100) {
            System.out.println("Population size: " + i);
            File file;
            PrintWriter average;
            file = new File("average_popSize_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(i, 100, 0.7f, 0.1f, (int) (5 * i / 100), pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Population size research done.");
        }
    }

    public void numberOfGenerationsResearch(PCB pcb) throws IOException {
        for (int i = 100; i < 1001; i += 100) {
            System.out.println("Number of generations: " + i);
            File file;
            PrintWriter average;
            file = new File("average_numberOfGenerations_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(100, i, 0.7f, 0.1f, 5, pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Number of generations research done.");
        }
    }

    public void tournamentSizeResearch(PCB pcb) throws IOException {
        for (int i = 1; i < 101; i++) {
            System.out.println("Tournament size: " + i);
            File file;
            PrintWriter average;
            file = new File("average_tournamentSizeAdditional_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(100, 100, 0.7f, 0.3f, i, pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = new Individual(GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness));
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Tournament research done.");
        }
    }

    public void crossoverPxResearch(PCB pcb) throws IOException {
        for (float i = 0.5f; i < 1.05f; i += 0.05f) {
            System.out.println("Px: " + i);
            File file;
            PrintWriter average;
            file = new File("average_px_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(100, 100, i, 0.1f, 5, pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Crossover research done.");
        }
    }

    public void mutationPmResearch(PCB pcb) throws IOException {
        for (float i = 0.1f; i < 1.05f; i += 0.05f) {
            System.out.println("Pm: " + i);
            File file;
            PrintWriter average;
            file = new File("average_pm_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(100, 100, 0.7f, i, 5, pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Mutation research done.");
        }
    }


    public void crossoverPxAdditionalResearch(PCB pcb) throws IOException {
        for (float i = 0.7f; i < 0.76f; i += 0.01f) {
            System.out.println("Px: " + i);
            File file;
            PrintWriter average;
            file = new File("average_px_additional_" + i + ".csv");
            file.createNewFile();
            average = new PrintWriter(file);

            // generating random individual
            Individual bestIndividual = new Individual(pcb.getConnectionTab());
            bestIndividual = bestIndividual.generateRandomIndividual();

            // running evolution 5 times to calculate avgFitness in each generation (graph)
            GeneticAlgorithm GA = new GeneticAlgorithm(100, 100, i, 0.1f, 5, pcb);

            // iterations - number of examined and averaged evolutions
            int iterations = 5;
            int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
            float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
            int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
            float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
            for (int j = 0; j < 5; j++) {
                // selectionType: 0 - tournament, 1 - roulette
                bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
                average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
            }
            average.close();
            System.out.println("Crossover research done.");
        }
    }

    public void tournamentRouletteComparisonResearch(PCB pcb) throws IOException {
        File file;
        PrintWriter average;
        file = new File("average_tournamentVsRoulette.csv");
        file.createNewFile();
        average = new PrintWriter(file);

        // generating random individual
        Individual bestIndividual = new Individual(pcb.getConnectionTab());
        bestIndividual = bestIndividual.generateRandomIndividual();

        // running evolution 5 times to calculate avgFitness in each generation (graph)
        GeneticAlgorithm GA = new GeneticAlgorithm(100, 200, 0.72f, 0.3f, 10, pcb);

        // iterations - number of examined and averaged evolutions
        int iterations = 5;
        int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
        float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
        int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
        float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
        for (int j = 0; j < 5; j++) {
            // selectionType: 0 - tournament, 1 - roulette
            System.out.println("tournament: " + j);
            bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
            average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
        }
        average.close();
        System.out.println("Tournament research done.");

        File file1;
        PrintWriter average1;
        file1 = new File("average_rouletteVsTournament.csv");
        file1.createNewFile();
        average1 = new PrintWriter(file1);

        // generating random individual
        Individual bestIndividual1 = new Individual(pcb.getConnectionTab());
        bestIndividual1 = bestIndividual1.generateRandomIndividual();

        // running evolution 5 times to calculate avgFitness in each generation (graph)
        GeneticAlgorithm GA1 = new GeneticAlgorithm(100, 200, 0.72f, 0.3f, 10, pcb);

        for (int j = 0; j < 5; j++) {
            // selectionType: 0 - tournament, 1 - roulette
            System.out.println("roulette: " + j);
            bestIndividual1 = GA1.startEvolution(1, bestFitnessInGeneration, bestGlobalFitness);
            average1.println(j + "; " + GA1.getPopulationSize() + "_" + GA1.getNumberOfGenerations() + "_" + GA1.getPx() + "_" + GA1.getPm() + "_" + GA1.getTournamentSize() + "; " + bestIndividual1.getFitness());
        }
        average1.close();
        System.out.println("Roulette research done.");

    }

    public void gaRandomComparisonResearch(PCB pcb) throws IOException {
        File file;
        PrintWriter average;
        file = new File("average_gaVsRandom_" + pcb.getWidth() + ".csv");
        file.createNewFile();
        average = new PrintWriter(file);

        // generating random individual
        Individual bestIndividual = new Individual(pcb.getConnectionTab());
        bestIndividual = bestIndividual.generateRandomIndividual();

        // running evolution 10 times to calculate avgFitness in each generation (graph)
        GeneticAlgorithm GA = new GeneticAlgorithm(600, 800, 0.72f, 0.3f, 60, pcb);

        // iterations - number of examined and averaged evolutions
        int iterations = 5;
        int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
        float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
        int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
        float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
        for (int j = 0; j < 10; j++) {
            // selectionType: 0 - tournament, 1 - roulette
            System.out.println("ga: " + j);
            bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
            average.println(j + "; " + GA.getPopulationSize() + "_" + GA.getNumberOfGenerations() + "_" + GA.getPx() + "_" + GA.getPm() + "_" + GA.getTournamentSize() + "; " + bestIndividual.getFitness());
        }
        average.close();
        System.out.println("GA research done.");

        File file1;
        PrintWriter average1;
        file1 = new File("average_randomVsGa_" + pcb.getWidth() + ".csv");
        file1.createNewFile();
        average1 = new PrintWriter(file1);

        for (int j = 0; j < 10; j++) {
            // selectionType: 0 - tournament, 1 - roulette
            System.out.println("random: " + j);
            // generating random individual
            Individual bestIndividual1 = new Individual(pcb.getConnectionTab());
            bestIndividual1 = bestIndividual1.generateRandomIndividual();
            average1.println(j + "; " + pcb.calculateFitness(bestIndividual1));
        }
        average1.close();
        System.out.println("Random research done.");
    }

    public void tournamentComparisonResearch(PCB pcb, int tournamentSize) throws IOException {
        // generating random individual
        Individual bestIndividual = new Individual(pcb.getConnectionTab());
        bestIndividual = bestIndividual.generateRandomIndividual();

        GeneticAlgorithm GA = new GeneticAlgorithm(300, 400, 0.72f, 0.3f, tournamentSize, pcb);

        // iterations - number of examined and averaged evolutions
        int iterations = 5;
        int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
        float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
        int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
        float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];

        // selectionType: 0 - tournament, 1 - roulette
        bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);

        System.out.println("GA research done.");
    }

    public void mutationComparisonResearch(PCB pcb, float pm) throws IOException {
        // generating random individual
        Individual bestIndividual = new Individual(pcb.getConnectionTab());
        bestIndividual = bestIndividual.generateRandomIndividual();

        GeneticAlgorithm GA = new GeneticAlgorithm(300, 400, 0.72f, pm, 30, pcb);

        // iterations - number of examined and averaged evolutions
        int iterations = 5;
        int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
        float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
        int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
        float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];

        // selectionType: 0 - tournament, 1 - roulette
        bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);

        System.out.println("GA research done.");
    }

    public void crossoverComparisonResearch(PCB pcb, float px) throws IOException {
        // generating random individual
        Individual bestIndividual = new Individual(pcb.getConnectionTab());
        bestIndividual = bestIndividual.generateRandomIndividual();

        GeneticAlgorithm GA = new GeneticAlgorithm(300, 400, px, 0.3f, 30, pcb);

        // iterations - number of examined and averaged evolutions
        int iterations = 5;
        int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
        float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
        int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
        float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];

        // selectionType: 0 - tournament, 1 - roulette
        bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);

        System.out.println("GA research done.");
    }


    public void runTest(PCB pcb) throws IOException {
//        crossoverComparisonResearch(pcb, 0);
//        crossoverComparisonResearch(pcb, 1);
//        crossoverComparisonResearch(pcb, 0.72f);
//        mutationComparisonResearch(pcb, 0);
//        mutationComparisonResearch(pcb, 1);
//        mutationComparisonResearch(pcb, 0.3f);
//        tournamentComparisonResearch(pcb, 300);
//        tournamentComparisonResearch(pcb, 1);
//        tournamentComparisonResearch(pcb, 30);
//        gaRandomComparisonResearch(pcb);
//        crossoverPxAdditionalResearch(pcb);
        tournamentRouletteComparisonResearch(pcb);
        populationSizeResearch(pcb);
        numberOfGenerationsResearch(pcb);
        tournamentSizeResearch(pcb);
        crossoverPxResearch(pcb);
        mutationPmResearch(pcb);
    }
}
