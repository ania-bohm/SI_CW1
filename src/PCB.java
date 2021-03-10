import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PCB {
    // width - X
    // height - Y
    private int width, height;
    private Point[][] connectionTab;

    public PCB(int width, int height, int connectionCount, Point[][] connectedPoints) {
        this.width = width;
        this.height = height;
        connectionTab = new Point[connectionCount][2];
        for (int i = 0; i < connectionCount; i++) {
            connectionTab[i][0] = new Point(connectedPoints[i][0]);
            connectionTab[i][1] = new Point(connectedPoints[i][1]);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point[][] getConnectionTab() {
        return connectionTab;
    }

    public void setConnectionTab(Point[][] connectionTab) {
        for (int i = 0; i < this.connectionTab.length; i++) {
            this.connectionTab[i][0] = new Point(connectionTab[i][0]);
            this.connectionTab[i][1] = new Point(connectionTab[i][1]);
        }
    }

    public void printConnectionTab() {
        for (int i = 0; i < connectionTab.length; i++) {
            System.out.print(connectionTab[i][0]);
            System.out.print(" -> " + connectionTab[i][1] + "\n");
        }
    }

    public void printPCB() {
        System.out.println("Width: " + this.getWidth());
        System.out.println("Height: " + this.getHeight());
        System.out.println("Connection table: ");
        this.printConnectionTab();
    }

    public int calculateFitness(Individual individual) {
        int fitness = 0;
        //liczba przecięć
        int intersectionsCount = countIntersections(individual);
        //sumaryczna długość ścieżek
        int pathsLength = individual.getPathsLength();
        //sumaryczna liczba segmentów tworzących ścieżki
        int segmentsCount = individual.getSegmentsCount();
        //liczba ścieżek poza płytką
        int outOfBoundsPathsCount = countOutOfBoundsPaths(individual);
        //sumaryczna długość części ścieżek poza obszarem płytki
        int outOfBoundsPathsLength = countOutOfBoundsPathsLength(individual);

        fitness = 1000 * intersectionsCount + 500 * outOfBoundsPathsCount + 200 * outOfBoundsPathsLength + 2 * segmentsCount + 1 * pathsLength;

        return fitness;
    }

    public int countIntersections(Individual individual) {
        int intersectionsCount = 0;
        int[][] allPointsTab;
        int maxX = this.width;
        int minX = 0;
        int maxY = this.height;
        int minY = 0;
        List<Point> allPoints = new ArrayList<>();
        Set<String> uniquePoints = new HashSet<>();

        for (int i = 0; i < individual.getPathList().size(); i++) {
            for (int j = 0; j < individual.getPathList().get(i).getPointsInPath().size(); j++) {
                allPoints.add(individual.getPathList().get(i).getPointsInPath().get(j));
            }
        }

        for (int i = 0; i < allPoints.size(); i++) {
            uniquePoints.add(allPoints.get(i).toString());
        }
        return (allPoints.size() - uniquePoints.size());
    }

    public int countOutOfBoundsPaths(Individual individual) {
        int outOfBoundsPathsCount = 0;
        int maxX = this.width;
        int maxY = this.height;
        int startPointX = 0;
        int startPointY = 0;
        for (int i = 0; i < individual.getPathList().size(); i++) {
            for (int j = 0; j < individual.getPathList().get(i).getSegmentList().size(); j++) {
                startPointX = individual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getX();
                startPointY = individual.getPathList().get(i).getSegmentList().get(j).getStartPoint().getY();
                if (startPointX > maxX || startPointX < 0 || startPointY > maxY || startPointY < 0) {
                    outOfBoundsPathsCount++;
                    break;
                }
            }
        }
        return outOfBoundsPathsCount;
    }

    public int countOutOfBoundsPathsLength(Individual individual) {
        int outOfBoundsPathsLength = 0;
        boolean isOut = false;
        List<Point> pointsInPath;

        for (int i = 0; i < individual.getPathList().size(); i++) {
            pointsInPath = individual.getPathList().get(i).getPointsInPath();
            for (int j = 0; j < pointsInPath.size(); j++) {
                if (!isInBounds(pointsInPath.get(j))) {
                    outOfBoundsPathsLength++;
                    isOut = true;
                } else if (isOut) {
                    outOfBoundsPathsLength++;
                    isOut = false;
                }
            }
        }
        return outOfBoundsPathsLength;
    }

    public boolean isInBounds(Point point) {
        if (point.getX() > this.width || point.getX() < 0 || point.getY() > this.height || point.getY() < 0) {
            return false;
        }
        return true;
    }
}
