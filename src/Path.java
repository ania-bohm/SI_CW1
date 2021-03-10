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
        return pathLength;
    }

    public int getSegmentCount() {
        return segmentCount;
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
}
