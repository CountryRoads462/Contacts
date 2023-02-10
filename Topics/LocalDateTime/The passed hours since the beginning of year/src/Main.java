import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.nextLine());
        int hours = (localDateTime.getDayOfYear() - 1) * 24;
        hours += localDateTime.getHour();
        System.out.println(hours);
    }
}