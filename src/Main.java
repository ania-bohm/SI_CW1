import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Loader loader = new Loader();
        PCB pcb = loader.load("zad0");

        Individual randomIndividual = new Individual(pcb.getConnectionTab());
        randomIndividual = randomIndividual.generateRandomIndividual();

        randomIndividual.printAllIndividualInfo();
        System.out.println("Number of out of bounds paths: " + pcb.countOutOfBoundsPaths(randomIndividual));
        System.out.println("Length of out of bounds path parts: " + pcb.countOutOfBoundsPathsLength(randomIndividual));
        System.out.println("Number of intersections: " + pcb.countIntersections(randomIndividual));
        System.out.println("Fitness: " + pcb.calculateFitness(randomIndividual));

    }
}
