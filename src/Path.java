import java.util.ArrayList;
import java.util.List;

public class Path {
    private Point startPoint;
    private Point endPoint;
    private List<Segment> segmentList;
    private int pathLength;
    private int segmentCount;

    public Path(Point startPoint, Point endPoint) {
        this.startPoint = new Point(startPoint);
        this.endPoint = new Point(endPoint);
        this.pathLength = 0;
        this.segmentCount = 0;
        this.segmentList = new ArrayList<>();
    }

    public Path(Path p) {
        this.startPoint = new Point(p.startPoint);
        this.endPoint = new Point(p.endPoint);
        this.pathLength = p.pathLength;
        this.segmentCount = p.segmentCount;
        this.segmentList = new ArrayList<>();
        for (int i = 0; i < p.segmentList.size(); i++) {
            this.segmentList.add(new Segment(p.segmentList.get(i)));
        }
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
//        this.segmentList = List.copyOf(segmentList);
        this.segmentList.clear();
        for (int i = 0; i < segmentList.size(); i++) {
            this.segmentList.add(new Segment(segmentList.get(i)));
        }
        countSegments();
        calculatePathLength();
    }

    public int getPathLength() {
        return calculatePathLength();
    }

    public int getSegmentCount() {
        return countSegments();
    }

    public int countSegments() {
        this.segmentCount = segmentList.size();
        return segmentCount;
    }

    public int calculatePathLength() {
        int pathLength = 0;
        for (int i = 0; i < segmentList.size(); i++) {
            pathLength += segmentList.get(i).getSegmentLength();
        }
        this.pathLength = pathLength;
        return pathLength;
    }

    public String printPathShort() {
        String path = "{";
        for (int i = 0; i < segmentList.size() - 1; i++) {
            path += segmentList.get(i).printSegmentShort() + ", ";
        }
        path += segmentList.get(segmentList.size() - 1).printSegmentShort() + "}";
        return path;
    }

    @Override
    public String toString() {
        String path = "{";
        for (int i = 0; i < segmentList.size() - 1; i++) {
            path += segmentList.get(i).toString() + ", ";
        }
        path += segmentList.get(segmentList.size() - 1).toString() + "}";
        return path;
    }

    public List<Point> getPointsInPath() {
        List<Point> pointsInPath = new ArrayList<>();

        for (int i = 0; i < segmentList.size(); i++) {
            for (int j = 0; j < segmentList.get(i).getPointsInSegment().size() - 1; j++) {
                pointsInPath.add(segmentList.get(i).getPointsInSegment().get(j));
            }
        }
        pointsInPath.add(endPoint);
        return pointsInPath;
    }

    public Path fixPath() {
        boolean wasFixed = true;
        while(wasFixed) {
            wasFixed = false;
            for (int i = 0; i < getSegmentCount(); i++) {
                if (getSegmentList().get(i).getSegmentLength() == 0) {
                    wasFixed = true;
                    getSegmentList().remove(i);
                    // quick fix
                    if (i != 0 && i < (getSegmentList().size())) {
                        getSegmentList().get(i - 1).setEndPoint(new Point(getSegmentList().get(i).getEndPoint()));
                        getSegmentList().remove(i);
                    }
                    i--;
                }
            }
        }
        while (wasFixed) {
            wasFixed = false;
            for (int i = 0; i < getSegmentCount(); i++) {
                int counter = 0;
                for (int j = i + 1; j < getSegmentCount(); j++) {
                    if (getSegmentList().get(i).getStartPoint().compareTo(getSegmentList().get(j).getStartPoint()) == 0) {
                        counter++;
                        for (int c = 0; c < counter; c++) {
                            getSegmentList().remove(i);
                        }
                        counter = 0;
                        wasFixed = true;
                        break;
                    } else {
                        counter++;
                    }
                }
            }

            for (int i = getSegmentCount() - 1; i > 1; i--) { // i od 4 do 2
                int counter = 0;
                for (int j = i - 1; j > 0; j--) { // j od 3 do 1
                    if (getSegmentList().get(i).getEndPoint().compareTo(getSegmentList().get(j).getEndPoint()) == 0) {
                        counter++;
                        for (int c = 0; c < counter; c++) {
                            getSegmentList().remove(i);
                            i--;
                        }
                        counter = 0;
                        wasFixed = true;
                        break;
                    } else {
                        counter++;
                    }
                }
            }
        }
        return this;
    }


    public boolean isVertical(Point p1, Point p2) {
        if (p1.getX() == p2.getX()) {
            return true;
        }
        return false;
    }
}
