import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Loader loader = new Loader();
        PCB pcb = loader.load("zad0");
        Individual randomIndividual = new Individual(pcb.getConnectionTab());
        randomIndividual = randomIndividual.generateRandomIndividual();
        randomIndividual.printAllIndividualInfo();

//        Point start1 = new Point(1,2);
//        Point start2 = new Point(1,5);
//        Point end1 = new Point(1,5);
//        Point end2 = new Point(3,5);
//
//        Segment s1 = new Segment(start1, end1);
//        Segment s2 = new Segment(start2, end2);
//
//        List segList = new ArrayList();
//        segList.add(s1);
//        segList.add(s2);
//        Path path = new Path(start1, end2);
//        path.setSegmentList(segList);
//        System.out.println(path.toString());
//        System.out.println(path.printPathShort());

    }
}
