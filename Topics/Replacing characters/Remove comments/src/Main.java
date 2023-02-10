import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String codeWithComments = scanner.nextLine();

        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codeWithComments.length() - 3; i++) {
            if (codeWithComments.charAt(i) == '/' && codeWithComments.charAt(i + 1) == '*') {
                i += 2;
                while (true) {
                    if (codeWithComments.charAt(i) == '*' && codeWithComments.charAt(i + 1) == '/') {
                        stringList.add(stringBuilder.insert(0, "/*").append("*/").toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        i += 2;
                        break;
                    } else {
                        stringBuilder.append(codeWithComments.charAt(i));
                        i++;
                    }
                }
            }
        }

        for (String elem :
                stringList) {
            codeWithComments = codeWithComments.replace(elem, "");
        }

        codeWithComments = codeWithComments.replaceAll("//.*$", "").trim();

        System.out.println(codeWithComments);
    }
}
