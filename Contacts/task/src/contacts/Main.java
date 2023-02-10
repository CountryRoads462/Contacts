package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static PhoneBook phoneBook;
    private static String dataBaseFileName;

    enum Action {
        ADD,
        LIST,
        SEARCH,
        REMOVE,
        EDIT,
        COUNT,
        INFO,
        EXIT;

        static Action getAction(String str) {
            switch (str) {
                case "add":
                    return ADD;
                case "list":
                    return LIST;
                case "search":
                    return SEARCH;
                case "remove":
                    return REMOVE;
                case "edit":
                    return EDIT;
                case "count":
                    return COUNT;
                case "info":
                    return INFO;
                default:
                    return EXIT;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            dataBaseFileName = args[0];
        }

        phoneBook = new PhoneBook();

        if (dataBaseFileName != null) {
            try {
                phoneBook = (PhoneBook) SerializationUtils.deserialize(args[0]);
            } catch (Exception ignored) {}
        }

        menu();
    }

    private static void menu() {
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            Action action = Action.getAction(scanner.nextLine());

            switch (action) {
                case ADD: {
                    Record newRecord;

                    System.out.print("Enter the type (person, organization): ");
                    if (scanner.nextLine().equals("person")) {
                        newRecord = new PersonRecord();

                        setName(newRecord);
                        setSurname(newRecord);
                        setBirthDate(newRecord);
                        setGender(newRecord);
                    } else {
                        newRecord = new OrganizationRecord();

                        setOrganizationName(newRecord);
                        setAddress(newRecord);
                    }

                    setPhoneNumber(newRecord);

                    phoneBook.addRecord(newRecord);
                    System.out.println("The record added.");
                    break;
                }
                case SEARCH: {
                    while (true) {
                        System.out.print("Enter search query: ");
                        String query = scanner.nextLine();

                        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);

                        List<Record> searchingResults = new ArrayList<>();

                        for (Record record :
                                phoneBook.getRecords()) {
                            Matcher matcher1 = pattern.matcher(record.getShortRepresentation());
                            Matcher matcher2 = pattern.matcher(record.getPhoneNumber());
                            if (matcher1.find() || matcher2.find()) {
                                searchingResults.add(record);
                            }
                        }

                        System.out.printf("Found %d results: \n", searchingResults.size());
                        int index = 1;
                        for (Record result :
                                searchingResults) {
                            System.out.println(index + ". " + result.getShortRepresentation());
                            index++;
                        }

                        System.out.println();

                        System.out.print("[search] Enter action ([number], back, again): ");
                        String filedName = scanner.nextLine();
                        if (filedName.equals("back")) {
                            break;
                        } else {
                            index = Integer.parseInt(filedName) - 1;
                            Record record = searchingResults.get(index);
                            System.out.println(record);

                            System.out.println();

                            recordMenu(record);
                        }
                    }

                }
                case COUNT: {
                    System.out.printf("The Phone Book has %d records.\n", phoneBook.getRecords().size());
                    break;
                }

                case LIST: {
                    System.out.println(phoneBook);

                    System.out.println();

                    System.out.print("[list] Enter action ([number], back): ");
                    String userInput = scanner.nextLine();
                    if (userInput.equals("back")) {
                        break;
                    } else {
                        int index = Integer.parseInt(userInput) - 1;
                        Record record = phoneBook.getRecordByIndex(index);
                        System.out.println(record);

                        System.out.println();
                        recordMenu(record);
                    }
                    break;
                }
                default: {
                    exit(0);
                }
            }

            if (dataBaseFileName != null) {
                try {
                    SerializationUtils.serialize(phoneBook, dataBaseFileName);
                } catch (Exception ignored) {}
            }

            System.out.println();
        }
    }

    private static void recordMenu(Record record) {
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String fieldName = scanner.nextLine();

            if (fieldName.equals("edit")) {
                List<String> fieldNames = record.getFieldNames();

                System.out.print("Select a field (");
                for (int i = 0; i < fieldNames.size(); i++) {
                    if (i == fieldNames.size() - 1) {
                        System.out.print(fieldNames.get(i));
                    } else {
                        System.out.print(fieldNames.get(i) + ", ");
                    }
                }
                System.out.print("): ");

                fieldName = scanner.nextLine();
                switch (fieldName) {
                    case "name": {
                        setName(record);
                        break;
                    }
                    case "surname": {
                        setSurname(record);
                        break;
                    }
                    case "birth": {
                        setBirthDate(record);
                        break;
                    }
                    case "gender": {
                        setGender(record);
                        break;
                    }
                    case "address": {
                        setAddress(record);
                        break;
                    }
                    default: {
                        setPhoneNumber(record);
                        break;
                    }
                }

                System.out.println("Saved");
                record.setTimeLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
            } else if (fieldName.equals("delete")) {
                System.out.print(phoneBook);

                System.out.print("Select a record: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                phoneBook.deleteRecord(index);

                System.out.println("The record removed!");
            } else {
                break;
            }
        }
        System.out.println();
        menu();
    }

    private static void setName(Record record) {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        if (name.equals("")) {
            System.out.println("Bad name!");
        } else {
            record.setValue("name", name);
        }
    }

    private static void setSurname(Record record) {
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        if (surname.equals("")) {
            System.out.println("Bad surname!");
        } else {
            record.setValue("surname", surname);
        }
    }

    private static void setBirthDate(Record record) {
        System.out.print("Enter the birth date: ");
        String birthDateString = scanner.nextLine();
        if (birthDateString.equals("")) {
            System.out.println("Bad birth date!");
        } else {
            record.setValue("birth", birthDateString);
        }
    }

    private static void setGender(Record record) {
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (gender.equals("")) {
            System.out.println("Bad gender!");
        } else {
            record.setValue("gender", gender);
        }
    }

    private static void setOrganizationName(Record record) {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();
        if (name.equals("")) {
            System.out.println("Bad organization name!");
        } else {
            record.setValue("name", name);
        }
    }

    private static void setAddress(Record record) {
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        if (address.equals("")) {
            System.out.println("Bad address!");
        } else {
            record.setValue("address", address);
        }
    }

    private static void setPhoneNumber(Record record) {
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (!number.matches("((\\+?\\d[ -])?((\\([0-9a-zA-Z]{2,}\\))|([0-9a-zA-Z]{2,}))([ -][0-9a-zA-Z]{2,})*)|([0-9a-zA-Z]{2,}[- ]\\([0-9a-zA-Z]{2,}\\)([ -][0-9a-zA-Z]{2,})*)|(\\+?(\\([0-9a-zA-Z]{2,}\\)|[0-9a-zA-Z]{2,})([ -][0-9a-zA-Z]{2,})*)|\\d")) {
            System.out.println("Bad number!");
        } else {
            record.setPhoneNumber(number);
        }
    }
}
