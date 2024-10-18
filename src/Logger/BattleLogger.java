package Logger;

import java.io.FileWriter;
import java.io.IOException;

public class BattleLogger {

    private static StringBuilder log;

    public BattleLogger() {
        log = new StringBuilder();
    }

    public static void logEvent(String event) {
        log.append(event).append("\n");
    }

    public static void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(log.toString());
            System.out.println("Битву збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}