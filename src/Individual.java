import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {
    private Point[][] connectionTab;
    private List<Path> pathList;
    private int pathsLength, segmentsCount;
    private int fitness;

    public Individual(Point[][] connectionTab) {
        this.connectionTab = new Point[connectionTab.length][2];
        for (int i = 0; i < connectionTab.length; i++) {
            this.connectionTab[i][0] = new Point(connectionTab[i][0]);
            this.connectionTab[i][1] = new Point(connectionTab[i][1]);
        }
        this.pathList = new ArrayList<>();
        this.pathsLength = 0;
        this.segmentsCount = 0;
        this.fitness = 0;
    }

    public Individual(Individual individual) {
        this.connectionTab = new Point[individual.getConnectionTab().length][2];
        for (int i = 0; i < individual.getConnectionTab().length; i++) {
            this.connectionTab[i][0] = new Point(individual.getConnectionTab()[i][0]);
            this.connectionTab[i][1] = new Point(individual.getConnectionTab()[i][1]);
        }
        this.pathList = new ArrayList<>(individual.getPathList());//<------------------check it
        this.pathsLength = individual.getPathsLength();
        this.segmentsCount = individual.getSegmentsCount();
        this.fitness = individual.getFitness();
    }

    public Point[][] getConnectionTab() {
        return connectionTab;
    }

    public List<Path> getPathList() {
        return pathList;
    }

    public void setPathList(List<Path> pathList) {
        this.pathList.clear();
        for (int i = 0; i < pathList.size(); i++) {
            this.pathList.add(new Path(pathList.get(i)));
        }
        this.pathsLength = calculateTotalPathLength();
        this.segmentsCount = countAllSegments();
    }

    public int getPathsLength() {
        return pathsLength;
    }

    public int getSegmentsCount() {
        return segmentsCount;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int countAllSegments() {
        int segmentCount = 0;
        for (int i = 0; i < pathList.size(); i++) {
            segmentCount += pathList.get(i).countSegments();
        }
        return segmentCount;
    }

    public int calculateTotalPathLength() {
        int totalPathLength = 0;
        for (int i = 0; i < pathList.size(); i++) {
            totalPathLength += pathList.get(i).calculatePathLength();
        }
        return totalPathLength;
    }

    @Override
    public String toString() {
        String paths = "{";
        for (int i = 0; i < pathList.size() - 1; i++) {
            paths += pathList.get(i).toString() + ", ";
        }
        paths += pathList.get(pathList.size() - 1).toString() + "}";
        return paths;
    }

    public String printPathShort() {
        String paths = "{";
        for (int i = 0; i < pathList.size() - 1; i++) {
            paths += pathList.get(i).printPathShort() + ", ";
        }
        paths += pathList.get(pathList.size() - 1).printPathShort() + "}";
        return paths;
    }

    public void printAllIndividualInfo() {
        System.out.println("Paths in short version: " + this.printPathShort());
        System.out.println("Paths in extended version: " + this.toString());
        System.out.println("Total paths length: " + this.calculateTotalPathLength());
        System.out.println("Segments count: " + this.countAllSegments());
    }

    public Individual generateRandomIndividual() {
        Individual randomIndividual = new Individual(this.connectionTab);
        List<Path> pathList = new ArrayList<>();
        for (int i = 0; i < connectionTab.length; i++) {
            int startX = randomIndividual.connectionTab[i][0].getX();
            int startY = randomIndividual.connectionTab[i][0].getY();
            int endX = randomIndividual.connectionTab[i][1].getX();
            int endY = randomIndividual.connectionTab[i][1].getY();
            int distanceX = endX - startX;
            int distanceY = endY - startY;
            List<Character> randomOrder = new ArrayList();
            List<Segment> segmentList = new ArrayList<>();

            for (int j = 0; j < Math.abs(distanceX); j++) {
                if (distanceX > 0) {
                    randomOrder.add('E'); //east
                } else {
                    randomOrder.add('W'); //west
                }
            }
            for (int j = 0; j < Math.abs(distanceY); j++) {
                if (distanceY > 0) {
                    randomOrder.add('S'); //south
                } else {
                    randomOrder.add('N'); //north
                }
            }
            Collections.shuffle(randomOrder);

            boolean directionChanged = false;
            Point segmentBeginning = new Point(startX, startY);
            Point currentPoint = new Point(startX, startY);
            Point nextPoint = new Point(startX, startY);
            for (int j = 0; j < randomOrder.size(); j++) {
                switch (randomOrder.get(j)) {
                    case 'N':
                        nextPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
                        break;
                    case 'S':
                        nextPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
                        break;
                    case 'W':
                        nextPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
                        break;
                    case 'E':
                        nextPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
                        break;
                }

                if (segmentBeginning.getX() != nextPoint.getX() && segmentBeginning.getY() != nextPoint.getY()) {
                    directionChanged = true;
                } else {
                    directionChanged = false;
                }

                if (directionChanged) {
                    Segment segment = new Segment(segmentBeginning, currentPoint);
                    segmentList.add(segment);
                    segmentBeginning = currentPoint;
                    currentPoint = nextPoint;
                } else {
                    currentPoint = nextPoint;
                }
            }
            Segment segment = new Segment(segmentBeginning, currentPoint);
            segmentList.add(segment);
            Path path = new Path(randomIndividual.connectionTab[i][0], randomIndividual.connectionTab[i][1]);
            path.setSegmentList(segmentList);
            pathList.add(path);
        }
        randomIndividual.setPathList(pathList);
        return randomIndividual;
    }

    public Individual crossover(Individual secondParent) {
        int crossPoint;
        Random random;
        List<Path> newPathList;
        Individual child;

        random = new Random();
        crossPoint = random.nextInt(this.pathList.size() + 1);
        newPathList = new ArrayList<>();
        child = new Individual(this.connectionTab);

        for (int i = 0; i < crossPoint; i++) {
            newPathList.add(this.getPathList().get(i));
        }

        for (int i = crossPoint; i < secondParent.getPathList().size(); i++) {
            newPathList.add(secondParent.getPathList().get(i));
        }

        child.setPathList(newPathList);

        return child;
    }
}
