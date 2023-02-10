import java.awt.desktop.SystemEventListener;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.parse(scanner.nextLine());
        System.out.println(localDate.plusDays(14));
    }
}