package main;

import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuTest {

    private Menu menu;
    private TestCommand command1;
    private TestCommand command2;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        command1 = new TestCommand();
        command2 = new TestCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAddCommand() {
        menu.addCommand(1, command1);
        menu.addCommand(2, command2);

        String simulatedInput = "1\n2\n7\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        menu.displayMenu();

        assertEquals(1, command1.getExecutionCount());
        assertEquals(1, command2.getExecutionCount());
    }

    @Test
    void testInvalidOption() {
        menu.addCommand(1, command1);

        String simulatedInput = "9\n1\n7\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        menu.displayMenu();

        assertTrue(outputStream.toString().contains("Невірний вибір. Спробуйте ще раз."));

        assertEquals(1, command1.getExecutionCount());
    }

    @Test
    void testExitOption() {
        String simulatedInput = "7\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        menu.displayMenu();

        assertTrue(outputStream.toString().contains("Програма завершена."));
    }

    private static class TestCommand implements Command {
        private int executionCount = 0;

        @Override
        public void execute() {
            executionCount++;
        }

        public int getExecutionCount() {
            return executionCount;
        }
    }
}
