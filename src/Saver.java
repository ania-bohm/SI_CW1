import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Saver {
    public Saver() {

    }

    public void saveToFile(float[] avgFitness) {
        try {
            File file = new File("src/fitness.txt");
            if (!file.exists()) {
                System.out.println(file.createNewFile());
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);
            for (int i = 0; i < avgFitness.length; i++) {
                buffer.write(String.valueOf(avgFitness[i]));
                buffer.newLine();
            }
            buffer.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
