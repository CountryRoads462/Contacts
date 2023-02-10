import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.next());
        int hoursToSub = Integer.parseInt(scanner.next());
        int minutesToAdd = Integer.parseInt(scanner.next());
        localDateTime = localDateTime.minusHours(hoursToSub);
        localDateTime = localDateTime.plusMinutes(minutesToAdd);
        System.out.println(localDateTime);
    }
}