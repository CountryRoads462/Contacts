import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.next());
        localDateTime = localDateTime.plusDays(Integer.parseInt(scanner.next())).minusHours(Integer.parseInt(scanner.next()));
        System.out.println(localDateTime);
    }
}