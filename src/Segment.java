public class Segment {
    private Point startPoint;
    private Point endPoint;
    private int segmentLength;

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = new Point(startPoint);
        this.endPoint = new Point(endPoint);
        this.segmentLength = Math.abs(this.startPoint.getX() - this.endPoint.getX()) + Math.abs(this.startPoint.getY() - this.endPoint.getY());
    }

    public Segment(Segment s) {
        this.startPoint = new Point(s.getStartPoint());
        this.endPoint = new Point(s.getEndPoint());
        this.segmentLength = s.getSegmentLength();
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = new Point(startPoint);
        this.segmentLength = Math.abs(this.startPoint.getX() - this.endPoint.getX()) + Math.abs(this.startPoint.getY() - this.endPoint.getY());
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = new Point(endPoint);
        this.segmentLength = Math.abs(this.startPoint.getX() - this.endPoint.getX()) + Math.abs(this.startPoint.getY() - this.endPoint.getY());
    }

    public int getSegmentLength() {
        return segmentLength;
    }

    public String printSegmentShort() {
        return "[" + startPoint.toString() + ", " + endPoint.toString() + "]";
    }

    @Override
    public String toString() {
        String segmentPath;
        int currentX;
        int currentY;
        Point currentPoint;

        segmentPath = "[";
        currentX = startPoint.getX();
        currentY = startPoint.getY();

        if (startPoint.getX() == endPoint.getX()) {
            if (startPoint.getY() < endPoint.getY()) {
                while (currentY != endPoint.getY()) {
                    currentPoint = new Point(currentX, currentY);
                    segmentPath += currentPoint.toString() + ", ";
                    currentY++;
                }
            } else {
                while (currentY != endPoint.getY()) {
                    currentPoint = new Point(currentX, currentY);
                    segmentPath += currentPoint.toString() + ", ";
                    currentY--;
                }
            }
        } else {
            if (startPoint.getX() < endPoint.getX()) {
                while (currentX != endPoint.getX()) {
                    currentPoint = new Point(currentX, currentY);
                    segmentPath += currentPoint.toString() + ", ";
                    currentX++;
                }
            } else {
                while (currentX != endPoint.getX()) {
                    currentPoint = new Point(currentX, currentY);
                    segmentPath += currentPoint.toString() + ", ";
                    currentX--;
                }
            }
        }
        segmentPath += endPoint.toString() + "]";
        return segmentPath;
    }
}
