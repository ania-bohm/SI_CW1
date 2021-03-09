import java.util.List;

public class PCB {

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
}
