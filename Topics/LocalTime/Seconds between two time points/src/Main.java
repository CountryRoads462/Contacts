import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime localTime1 = LocalTime.parse(scanner.nextLine());
        LocalTime localTime2 = LocalTime.parse(scanner.nextLine());
        int seconds1 = localTime1.toSecondOfDay();
        int seconds2 = localTime2.toSecondOfDay();
        System.out.println(Math.abs(seconds2 - seconds1));
    }
}