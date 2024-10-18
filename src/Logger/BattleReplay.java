package Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleReplay {

    public void replayFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Виводимо збережені події битви на екран
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}