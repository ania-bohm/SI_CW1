import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {

    public Loader() {

    }

    public PCB load(String fileName) {
        try {
            int width, height, connectionCount;
            String line;
            String[] splitFirstLine;
            String[] splitLine;
            List<Point> connectionList;
            Point[][] connectedPoints;
            File file;
            Scanner scanner;

            file = new File(fileName + ".txt");
            scanner = new Scanner(file);
            line = scanner.nextLine();
            splitFirstLine = new String[2];
            splitFirstLine = line.split(";");

            width = Integer.valueOf(splitFirstLine[0]);
            height = Integer.valueOf(splitFirstLine[1]);

            connectionList = new ArrayList<>();
            splitLine = new String[4];
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                splitLine = line.split(";");
                int x1 = Integer.valueOf(splitLine[0]);
                int y1 = Integer.valueOf(splitLine[1]);
                int x2 = Integer.valueOf(splitLine[2]);
                int y2 = Integer.valueOf(splitLine[3]);
                connectionList.add(new Point(x1, y1));
                connectionList.add(new Point(x2, y2));
            }
            connectionCount = connectionList.size()/2;
            connectedPoints = new Point[connectionCount][2];
            for (int i = 0; i < connectionList.size(); i += 2) {
                connectedPoints[i / 2][0] = new Point(connectionList.get(i));
                connectedPoints[i / 2][1] = new Point(connectionList.get(i + 1));
            }
            return new PCB(width, height, connectionCount, connectedPoints);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
