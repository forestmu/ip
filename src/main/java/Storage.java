import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File dataFolder = new File("./data");
    private final File candyStorage = new File("./data/candyStorage.txt");

    public Storage() {
        try {
            //makes folder and file if doesn't exist
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            if (!candyStorage.exists()) {
                candyStorage.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String string, boolean toAppend) {
        try (FileWriter writer = new FileWriter(candyStorage, toAppend)) {
            writer.write(string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
