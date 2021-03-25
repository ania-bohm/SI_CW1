import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // loading PCB instance from file
        Loader loader = new Loader();
        PCB pcb = loader.loadFromJar("zad3");
        Research research = new Research();
        try {
            research.runTest(pcb);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String[] instances = {"zad0", "zad1", "zad2", "zad3"};
//        for(String s : instances) {
//            // loading PCB instance from file
//            Loader loader = new Loader();
//            PCB pcb = loader.load(s);
//            Research research = new Research();
//            try {
//                research.runTest(pcb);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        System.out.println("Choose a file to load PCB. Press one of the digits {0, 1, 2, 3} or any non integer key to terminate program");
//        System.out.println("0 - zad0, 1 - zad1, 2 - zad2, 3 - zad3");
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//            int chosenFile = scanner.nextInt();
//            if (chosenFile >= 0 && chosenFile < 4) {
//
//                // loading PCB instance from file
//                Loader loader = new Loader();
//
//                // PCB pcb = loader.load("zad" + chosenFile);
//                PCB pcb = loader.loadFromJar("zad" + chosenFile);
//
//                // generating random individual
//                Individual bestIndividual = new Individual(pcb.getConnectionTab());
//                bestIndividual = bestIndividual.generateRandomIndividual();
//
//                // running evolution 5 times to calculate avgFitness in each generation (graph)
//                GeneticAlgorithm GA = new GeneticAlgorithm(1000, 100, 0.7f, 0.90f, 10, pcb);
//
//                // iterations - number of examined and averaged evolutions
//                int iterations = 1;
//                int[] bestFitnessInGeneration = new int[GA.getNumberOfGenerations()];
//                float[] bestGlobalFitness = new float[GA.getNumberOfGenerations()];
//                int[][] fitnessMatrix = new int[iterations][GA.getNumberOfGenerations()];
//                float[] avgFitnessInGeneration = new float[GA.getNumberOfGenerations()];
//
//                for (int i = 0; i < iterations; i++) {
//                    // selectionType: 0 - tournament, 1 - roulette
//                    bestIndividual = GA.startEvolution(0, bestFitnessInGeneration, bestGlobalFitness);
//                    fitnessMatrix[i] = bestFitnessInGeneration;
//                }
//                int sumFitness = 0;
//                for (int i = 0; i < GA.getNumberOfGenerations(); i++) {
//                    sumFitness = 0;
//                    for (int j = 0; j < iterations; j++) {
//                        sumFitness += fitnessMatrix[j][i];
//                    }
//                    avgFitnessInGeneration[i] = sumFitness / iterations;
//                }
//
//                // saving avgFitness to file
//                Saver saver = new Saver();
////                saver.saveToFile(avgFitnessInGeneration);
//                saver.saveToFile(bestGlobalFitness);
//
//                // displaying pathList of the best Individual
//                Individual finalRandomIndividual = bestIndividual;
//
//                Runnable r = () -> {
//                    int scaleUp = 40;
//                    JFrame frmOpt = new JFrame();
//                    frmOpt.setVisible(true);
//                    frmOpt.setLocation(100, 100);
//                    frmOpt.setAlwaysOnTop(true);
//                    LineComponent lineComponent = new LineComponent(pcb.getWidth() * scaleUp, pcb.getHeight() * scaleUp);
//                    for (int i = 0; i < finalRandomIndividual.getPathList().size(); i++) {
//                        int xStart = finalRandomIndividual.getPathList().get(i).getStartPoint().getX();
//                        int yStart = finalRandomIndividual.getPathList().get(i).getStartPoint().getY();
//                        int xEnd = finalRandomIndividual.getPathList().get(i).getEndPoint().getX();
//                        int yEnd = finalRandomIndividual.getPathList().get(i).getEndPoint().getY();
//                        lineComponent.addPoint(xStart, yStart);
//                        lineComponent.addPoint(xEnd, yEnd);
//                        for (int j = 0; j < finalRandomIndividual.getPathList().get(i).getSegmentList().size(); j++) {
//                            int x1 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getX();
//                            int x2 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getEndPoint().getX();
//                            int y1 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getY();
//                            int y2 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getEndPoint().getY();
//                            lineComponent.addLine(x1, x2, y1, y2);
//                        }
//                    }
//                    JOptionPane.showMessageDialog(frmOpt, lineComponent);
//                    frmOpt.dispose();
//                };
//                SwingUtilities.invokeLater(r);
//
//                // displaying graph of avgFitnesses for each generation
//                SwingUtilities.invokeLater(() -> DrawGraph.createAndShowGui());
//
//                finalRandomIndividual.printAllIndividualInfo();
//                System.out.println("Fitness: " + pcb.calculateFitness(finalRandomIndividual));
//                // printing info about generated random individual - calculating its fitness at the end
////                System.out.println("Program generated a random individual, all of its info below");
////                newRandom.printAllIndividualInfo();
////                System.out.println("Number of out of bounds paths: " + pcb.countOutOfBoundsPaths(newRandom));
////                System.out.println("Length of out of bounds path parts: " + pcb.countOutOfBoundsPathsLength(newRandom));
////                System.out.println("Number of intersections: " + pcb.countIntersections(newRandom));
////                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
////                System.out.println("Fitness is calculated based on weighted sum represented by the formula below: ");
////                System.out.println("Fitness = 1000 * intersectionsCount + 500 * outOfBoundsPathsCount + 200 * outOfBoundsPathsLength + 2 * segmentsCount + 1 * pathsLength");
////                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
////                System.out.println("Fitness: " + pcb.calculateFitness(newRandom));
//                System.out.println("\nPress one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
//            } else {
//                System.out.println("Press one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
//            }
//        }
//        scanner.close();

    }
}
