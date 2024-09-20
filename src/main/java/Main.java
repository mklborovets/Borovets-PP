import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас LucasNumber зберігає номер та значення числа Люка.
 * Містить методи доступу до даних і методи для перевірки, чи є число квадратом.
 */
class LucasNumber {
    private int index;
    private int value;

    /**
     * Конструктор, що ініціалізує номер та значення числа Люка.
     * @param index Номер числа Люка.
     * @param value Значення числа Люка.
     */
    public LucasNumber(int index, int value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Отримати номер числа Люка.
     * @return Номер числа Люка.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Отримати значення числа Люка.
     * @return Значення числа Люка.
     */
    public int getValue() {
        return value;
    }

    /**
     * Перевіряє, чи є число Люка квадратом.
     * @return true, якщо число квадрат, false інакше.
     */
    public boolean isSquare() {
        int sqrt = (int) Math.sqrt(value);
        return sqrt * sqrt == value;
    }
}

/**
 * Головний клас для запуску програми.
 */
public class Main {

    /**
     * Метод для обчислення числа Люка за індексом.
     * @param n Номер числа Люка.
     * @return Значення числа Люка.
     */
    public static int lucasNumber(int n) {
        if (n == 0) {
            return 2;
        } else if (n == 1) {
            return 1;
        } else {
            int a = 2, b = 1, c;
            for (int i = 2; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return b;
        }
    }

    /**
     * Головна функція програми.
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення кількості чисел Люка, які потрібно перевірити
        System.out.print("Введіть кількість перших чисел Люка, які потрібно перевірити: ");
        int N = scanner.nextInt();

        // Створюємо список об'єктів LucasNumber
        List<LucasNumber> lucasNumbers = new ArrayList<>();

        // Обчислюємо числа Люка і додаємо їх до списку
        for (int i = 0; i < N; i++) {
            int lucasValue = lucasNumber(i);
            LucasNumber lucasNumber = new LucasNumber(i, lucasValue);
            lucasNumbers.add(lucasNumber);
        }

        // Виводимо числа Люка та перевіряємо, чи є вони квадратами
        System.out.println("Перші " + N + " чисел Люка та їх перевірка на квадрат:");
        for (LucasNumber ln : lucasNumbers) {
            System.out.println("Число Люка #" + ln.getIndex() + " = " + ln.getValue()
                    + (ln.isSquare() ? " (є квадратом)" : ""));
        }
    }
}
