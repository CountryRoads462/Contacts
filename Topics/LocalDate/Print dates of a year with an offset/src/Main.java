import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.parse(scanner.nextLine());
        int sourceYear = localDate.getYear();
        int days = Integer.parseInt(scanner.nextLine());
        while (localDate.getYear() == sourceYear) {
            System.out.println(localDate);
            localDate = localDate.plusDays(days);
        }
    }
}