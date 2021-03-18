import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
//        Segment s1 = new Segment(new Point(2, 2), new Point(3, 2));
//        Segment s2 = new Segment(new Point(3, 2), new Point(4, 2));
//        Segment s3 = new Segment(new Point(4, 2), new Point(3, 2));
//        Segment s4 = new Segment(new Point(4, 1), new Point(3, 1));
//        Segment s5 = new Segment(new Point(3, 1), new Point(3, 2));
//        Segment s6 = new Segment(new Point(3, 2), new Point(3, 3));
//        Segment s7 = new Segment(new Point(3, 3), new Point(5, 3));
//        List<Segment> list = new ArrayList<>();
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
//        list.add(s4);
//        list.add(s5);
//        list.add(s6);
//        list.add(s7);
//        Path path = new Path(new Point(2, 2), new Point(5, 3));
//        path.setSegmentList(list);
//        System.out.println(path.toString());
//        path.fixPath();
//        System.out.println(path.toString());

        System.out.println("Choose a file to load PCB. Press one of the digits {0, 1, 2, 3} or any non integer key to terminate program");
        System.out.println("0 - zad0, 1 - zad1, 2 - zad2, 3 - zad3");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int chosenFile = scanner.nextInt();
            if (chosenFile >= 0 && chosenFile < 4) {
                // loading PCB instance from file
                Loader loader = new Loader();
                // PCB pcb = loader.load("zad" + chosenFile);
                PCB pcb = loader.loadFromJar("zad" + chosenFile);

                // generating random individual
                Individual randomIndividual = new Individual(pcb.getConnectionTab());
                randomIndividual = randomIndividual.generateRandomIndividual();

                // printing info about generated random individual - calculating its fitness at the end
//                System.out.println("Program generated a random individual, all of its info below");
//                randomIndividual.printAllIndividualInfo();
//                System.out.println("Number of out of bounds paths: " + pcb.countOutOfBoundsPaths(randomIndividual));
//                System.out.println("Length of out of bounds path parts: " + pcb.countOutOfBoundsPathsLength(randomIndividual));
//                System.out.println("Number of intersections: " + pcb.countIntersections(randomIndividual));
//                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("Fitness is calculated based on weighted sum represented by the formula below: ");
//                System.out.println("Fitness = 1000 * intersectionsCount + 500 * outOfBoundsPathsCount + 200 * outOfBoundsPathsLength + 2 * segmentsCount + 1 * pathsLength");
//                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("Fitness: " + pcb.calculateFitness(randomIndividual));
//                System.out.println("\nPress one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");

                Individual finalRandomIndividual = randomIndividual;
                Runnable r = () -> {
                    JFrame frmOpt = new JFrame();
                    frmOpt.setVisible(true);
                    frmOpt.setLocation(100, 100);
                    frmOpt.setAlwaysOnTop(true);
                    LineComponent lineComponent = new LineComponent(pcb.getWidth() * 50, pcb.getHeight() * 50);
                    for (int i = 0; i < finalRandomIndividual.getPathList().size(); i++) {
                        int xStart = finalRandomIndividual.getPathList().get(i).getStartPoint().getX();
                        int yStart = finalRandomIndividual.getPathList().get(i).getStartPoint().getY();
                        int xEnd = finalRandomIndividual.getPathList().get(i).getEndPoint().getX();
                        int yEnd = finalRandomIndividual.getPathList().get(i).getEndPoint().getY();
                        lineComponent.addPoint(xStart, yStart);
                        lineComponent.addPoint(xEnd, yEnd);
                        for (int j = 0; j < finalRandomIndividual.getPathList().get(i).getSegmentList().size(); j++) {
                            int x1 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getX();
                            int x2 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getEndPoint().getX();
                            int y1 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getY();
                            int y2 = finalRandomIndividual.getPathList().get(i).getSegmentList().get(j).getEndPoint().getY();
                            lineComponent.addLine(x1, x2, y1, y2);
                        }
                    }
                    JOptionPane.showMessageDialog(frmOpt, lineComponent);
                    frmOpt.dispose();
                };
                SwingUtilities.invokeLater(r);
                Individual newRandom = new Individual(randomIndividual);
                GeneticAlgorithm GA = new GeneticAlgorithm(50, 100, 0.7f, 0.95f, 2, pcb);
                newRandom = GA.startEvolution(0);


//                Individual parent1 = new Individual(pcb.getConnectionTab());
//                parent1 = parent1.generateRandomIndividual();
//                System.out.println("Parent1: " + parent1.toString());
//                Individual parent2 = new Individual(pcb.getConnectionTab());
//                parent2 = parent2.generateRandomIndividual();
//                System.out.println("Parent2: " + parent2.toString());
//                for (int i = 0; i < 10; i++) {
//                    newRandom.mutation(1);
//                }
//                newRandom = parent1.crossover(parent2);
//                System.out.println("Child: " + newRandom.toString());

                Individual finalRandomIndividual1 = newRandom;
                Runnable r1 = () -> {
                    JFrame frmOpt = new JFrame();
                    frmOpt.setVisible(true);
                    frmOpt.setLocation(500, 100);
                    frmOpt.setAlwaysOnTop(true);
                    LineComponent lineComponent = new LineComponent(pcb.getWidth() * 50, pcb.getHeight() * 50);
                    for (int i = 0; i < finalRandomIndividual1.getPathList().size(); i++) {
                        int xStart = finalRandomIndividual1.getPathList().get(i).getStartPoint().getX();
                        int yStart = finalRandomIndividual1.getPathList().get(i).getStartPoint().getY();
                        int xEnd = finalRandomIndividual1.getPathList().get(i).getEndPoint().getX();
                        int yEnd = finalRandomIndividual1.getPathList().get(i).getEndPoint().getY();
                        lineComponent.addPoint(xStart, yStart);
                        lineComponent.addPoint(xEnd, yEnd);
                        for (int j = 0; j < finalRandomIndividual1.getPathList().get(i).getSegmentList().size(); j++) {
                            int x1 = finalRandomIndividual1.getPathList().get(i).getSegmentList().get(j).getStartPoint().getX();
                            int x2 = finalRandomIndividual1.getPathList().get(i).getSegmentList().get(j).getEndPoint().getX();
                            int y1 = finalRandomIndividual1.getPathList().get(i).getSegmentList().get(j).getStartPoint().getY();
                            int y2 = finalRandomIndividual1.getPathList().get(i).getSegmentList().get(j).getEndPoint().getY();
                            lineComponent.addLine(x1, x2, y1, y2);
                        }
                    }
                    JOptionPane.showMessageDialog(frmOpt, lineComponent);
                    frmOpt.dispose();
                };
                SwingUtilities.invokeLater(r1);
                // printing info about generated random individual - calculating its fitness at the end
//                System.out.println("Program generated a random individual, all of its info below");
//                newRandom.printAllIndividualInfo();
//                System.out.println("Number of out of bounds paths: " + pcb.countOutOfBoundsPaths(newRandom));
//                System.out.println("Length of out of bounds path parts: " + pcb.countOutOfBoundsPathsLength(newRandom));
//                System.out.println("Number of intersections: " + pcb.countIntersections(newRandom));
//                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("Fitness is calculated based on weighted sum represented by the formula below: ");
//                System.out.println("Fitness = 1000 * intersectionsCount + 500 * outOfBoundsPathsCount + 200 * outOfBoundsPathsLength + 2 * segmentsCount + 1 * pathsLength");
//                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("Fitness: " + pcb.calculateFitness(newRandom));
                System.out.println("\nPress one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
            } else {
                System.out.println("Press one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
            }
        }
        scanner.close();

    }
}
