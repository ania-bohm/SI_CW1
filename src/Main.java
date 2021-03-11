import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        System.out.println("Choose a file to load PCB. Press one of the digits {0, 1, 2, 3} or any non integer key to terminate program");
        System.out.println("0 - zad0, 1 - zad1, 2 - zad2, 3 - zad3");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int chosenFile = scanner.nextInt();
            if(chosenFile >= 0 && chosenFile < 4) {
                // loading PCB instance from file
                Loader loader = new Loader();
               // PCB pcb = loader.load("zad" + chosenFile);
                PCB pcb = loader.loadFromJar("zad" + chosenFile);

                // generating random individual
                Individual randomIndividual = new Individual(pcb.getConnectionTab());
                randomIndividual = randomIndividual.generateRandomIndividual();

                // printing info about generated random individual - calculating its fitness at the end
                System.out.println("Program generated a random individual, all of its info below");
                randomIndividual.printAllIndividualInfo();
                System.out.println("Number of out of bounds paths: " + pcb.countOutOfBoundsPaths(randomIndividual));
                System.out.println("Length of out of bounds path parts: " + pcb.countOutOfBoundsPathsLength(randomIndividual));
                System.out.println("Number of intersections: " + pcb.countIntersections(randomIndividual));
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Fitness is calculated based on weighted sum represented by the formula below: ");
                System.out.println("Fitness = 1000 * intersectionsCount + 500 * outOfBoundsPathsCount + 200 * outOfBoundsPathsLength + 2 * segmentsCount + 1 * pathsLength");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Fitness: " + pcb.calculateFitness(randomIndividual));
                System.out.println("\nPress one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
            } else {
                System.out.println("Press one of the digits {0, 1, 2, 3} to continue or any non integer key to terminate program");
            }
        }
    }
}
