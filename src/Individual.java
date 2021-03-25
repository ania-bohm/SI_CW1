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
        this.pathList = new ArrayList<>();
        for (int i = 0; i < individual.getPathList().size(); i++) {
            this.pathList.add(new Path(individual.getPathList().get(i)));
        }
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
        this.segmentsCount = segmentCount;
        return segmentCount;
    }

    public int calculateTotalPathLength() {
        int totalPathLength = 0;
        for (int i = 0; i < pathList.size(); i++) {
            totalPathLength += pathList.get(i).calculatePathLength();
        }
        this.pathsLength = totalPathLength;
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

    public Individual mutation(float pm) {
        Random random = new Random();
        float probability;
        Segment mutatedSegment, previousSegment, nextSegment;
        boolean isSegmentVertical;
        int direction; // -1 - up/left, 1 - down/right
        int segmentIndex;
        for (Path path : this.getPathList()) {
            previousSegment = null;
            nextSegment = null;
//            probability = 0.1f;
            probability = random.nextFloat();
            if (probability < pm) { // if mutation occurs then
                probability = random.nextFloat();
                if (probability < 0.5) { // mutation a
//                    System.out.println("Mutation a");
                    direction = random.nextInt(2) * 2 - 1; // -1 or 1
                    segmentIndex = random.nextInt(path.getSegmentCount()); // random index pointing to segment
//                    System.out.println("SegmentIndex in A: " + segmentIndex);
//                    System.out.println("Direction in A: " + direction);
                    mutatedSegment = path.getSegmentList().get(segmentIndex);
                    if (segmentIndex == 0) { // if the first segment in path is chosen
                        // adding new segment with length = 0 in path's start point so it can be used as a previous segment
                        path.getSegmentList().add(0, new Segment(new Point(path.getStartPoint()), new Point(path.getStartPoint())));
                        path.countSegments();
                        previousSegment = path.getSegmentList().get(0);
                        segmentIndex++;
                    } else {
                        previousSegment = path.getSegmentList().get(segmentIndex - 1);
                    }
                    if (segmentIndex == path.getSegmentCount() - 1) { // if the last segment in path is chosen
                        // adding new segment with length = 0 in path's end point so it can be used as a next segment
                        path.getSegmentList().add(new Segment(new Point(path.getEndPoint()), new Point(path.getEndPoint())));
                        path.countSegments();
                        nextSegment = path.getSegmentList().get(path.getSegmentList().size() - 1);
                    } else {
                        nextSegment = path.getSegmentList().get(segmentIndex + 1);
                    }
                    // determining orientation of the segment
                    isSegmentVertical = isVertical(mutatedSegment.getStartPoint(), mutatedSegment.getEndPoint());
                    if (isSegmentVertical) { // if vertical
                        mutatedSegment.getStartPoint().setX(mutatedSegment.getStartPoint().getX() + direction);
                        mutatedSegment.getEndPoint().setX(mutatedSegment.getEndPoint().getX() + direction);
                        if (previousSegment != null) {
                            previousSegment.getEndPoint().setX(mutatedSegment.getStartPoint().getX());
                        }
                        if (nextSegment != null) {
                            nextSegment.getStartPoint().setX(mutatedSegment.getEndPoint().getX());
                        }

                    } else { // if horizontal
                        mutatedSegment.getStartPoint().setY(mutatedSegment.getStartPoint().getY() + direction);
                        mutatedSegment.getEndPoint().setY(mutatedSegment.getEndPoint().getY() + direction);
                        if (previousSegment != null) {
                            previousSegment.getEndPoint().setY(mutatedSegment.getStartPoint().getY());
                        }
                        if (nextSegment != null) {
                            nextSegment.getStartPoint().setY(mutatedSegment.getEndPoint().getY());
                        }

                    }
                    this.countAllSegments();
                    this.calculateTotalPathLength();

                } else { // mutation b
//                    System.out.println("Mutation b");
                    int side = random.nextInt(2); // 0 - left/up, 1 - right/down
                    direction = random.nextInt(2) * 2 - 1; // -1 or 1
                    segmentIndex = random.nextInt(path.getSegmentCount());
//                    System.out.println("segment index in B: " + segmentIndex);
//                    System.out.println("direction in B: " + direction);
                    mutatedSegment = path.getSegmentList().get(segmentIndex);
                    isSegmentVertical = isVertical(mutatedSegment.getStartPoint(), mutatedSegment.getEndPoint());

                    if (mutatedSegment.getSegmentLength() > 1) {
                        int bisection = random.nextInt(mutatedSegment.getSegmentLength() - 1) + 1;
                        if (isSegmentVertical) { //if vertical
                            path.getSegmentList().add(segmentIndex,
                                    new Segment(new Point(mutatedSegment.getStartPoint()),
                                            (new Point(mutatedSegment.getStartPoint().getX(), mutatedSegment.getStartPoint().getY() + bisection))));
                            path.countSegments();
                            mutatedSegment.getStartPoint().setY(mutatedSegment.getStartPoint().getY() + bisection);
                        } else { //if horizontal
                            path.getSegmentList().add(segmentIndex,
                                    new Segment(new Point(mutatedSegment.getStartPoint()),
                                            (new Point(mutatedSegment.getStartPoint().getX() + bisection, mutatedSegment.getStartPoint().getY()))));
                            path.countSegments();
                            mutatedSegment.getStartPoint().setX(mutatedSegment.getStartPoint().getX() + bisection);
                        }
                        path.getSegmentList().add(segmentIndex + 1,
                                new Segment(new Point(mutatedSegment.getStartPoint()), new Point(mutatedSegment.getStartPoint())));
                        path.countSegments();

                        segmentIndex += 2 * side; // choosing segment to shift (left/right, up/down)
                        this.countAllSegments();
                        this.calculateTotalPathLength();
                    }
                    mutatedSegment = path.getSegmentList().get(segmentIndex);

                    if (segmentIndex == 0) { //if the first segment is chosen
                        path.getSegmentList().add(0, new Segment(new Point(path.getStartPoint()), new Point(path.getStartPoint())));
                        path.countSegments();
                        previousSegment = path.getSegmentList().get(0);
                        segmentIndex++;
                    } else {
                        previousSegment = path.getSegmentList().get(segmentIndex - 1);
                    }
                    if (segmentIndex == path.getSegmentCount() - 1) { // if the last segment is chosen
                        path.getSegmentList().add(new Segment(new Point(path.getEndPoint()), new Point(path.getEndPoint())));
                        path.countSegments();
                        nextSegment = path.getSegmentList().get(path.getSegmentCount() - 1);
                    } else {
                        nextSegment = path.getSegmentList().get(segmentIndex + 1);
                    }

                    if (isSegmentVertical) { //if chosen segment is vertical
                        mutatedSegment.getStartPoint().setX(mutatedSegment.getStartPoint().getX() + direction);
                        mutatedSegment.getEndPoint().setX(mutatedSegment.getEndPoint().getX() + direction);
                        if (previousSegment != null) {
                            previousSegment.getEndPoint().setX(mutatedSegment.getStartPoint().getX());
                        }
                        if (nextSegment != null) {
                            nextSegment.getStartPoint().setX(mutatedSegment.getEndPoint().getX());
                        }

                    } else {// if chosen segment is horizontal
                        mutatedSegment.getStartPoint().setY(mutatedSegment.getStartPoint().getY() + direction);
                        mutatedSegment.getEndPoint().setY(mutatedSegment.getEndPoint().getY() + direction);
                        if (previousSegment != null) {
                            previousSegment.getEndPoint().setY(mutatedSegment.getStartPoint().getY());
                        }
                        if (nextSegment != null) {
                            nextSegment.getStartPoint().setY(mutatedSegment.getEndPoint().getY());
                        }
                    }
                    this.countAllSegments();
                    this.calculateTotalPathLength();
                }
            }
//            System.out.println(this.toString());
        }
        // fixing looped paths
        this.fixLoopedPaths();
        return this;
    }

    public boolean isVertical(Point p1, Point p2) {
        if (p1.getX() == p2.getX()) {
            return true;
        }
        return false;
    }

    public Individual fixLoopedPaths() {
        for (Path path : this.getPathList()) {
            path.fixPath();
        }
        return this;
    }
}
