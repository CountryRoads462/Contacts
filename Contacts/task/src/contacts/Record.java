package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Record {

    String phoneNumber;
    LocalDateTime timeCreated;
    LocalDateTime timeLastEdit;

    public abstract List<String> getFieldNames();

    public abstract String getShortRepresentation();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract void setValue(String fieldName, String value);

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

class PersonRecord extends Record {

    enum Gender {
        MALE,
        FEMALE;

        public static Gender parse(String str) {
            if (str.equals("M")) {
                return MALE;
            } else {
                return FEMALE;
            }
        }
    }

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    PersonRecord() {
        this.timeCreated = LocalDateTime.now().withNano(0).withSecond(0);
        this.timeLastEdit = timeCreated;
    }

    @Override
    public String getShortRepresentation() {
        return name + " " + surname;
    }

    @Override
    public List<String> getFieldNames() {
        return new ArrayList<>(List.of("name", "surname", "birth", "gender", "number"));
    }

    @Override
    public void setValue(String fieldName, String value) {
        switch (fieldName) {
            case "name": {
                this.name = value;
                break;
            }
            case "surname": {
                this.surname = value;
                break;
            }
            case "birth": {
                this.birthDate = LocalDate.parse(value);
                break;
            }
            default: {
                this.gender = Gender.parse(value);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s\n" +
                        "Surname: %s\n" +
                        "Birth date: %s\n" +
                        "Gender: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s",
                Objects.requireNonNullElse(name, "[no data]"),
                Objects.requireNonNullElse(surname, "[no data]"),
                Objects.requireNonNullElse(birthDate, "[no data]"),
                Objects.requireNonNullElse(gender, "[no data]"),
                Objects.requireNonNullElse(phoneNumber, "[no data]"),
                Objects.requireNonNullElse(timeCreated, "[no data]"),
                Objects.requireNonNullElse(timeLastEdit, "[no data]"));
    }
}

class OrganizationRecord extends Record {

    private String organizationName;
    private String address;

    OrganizationRecord() {
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = timeCreated;
    }

    @Override
    public String getShortRepresentation() {
        return organizationName;
    }

    @Override
    public List<String> getFieldNames() {
        return new ArrayList<>(List.of("name", "address", "number"));
    }

    @Override
    public void setValue(String fieldName, String value) {
        if (fieldName.equals("name")) {
            this.organizationName = value;
        } else {
            this.address = value;
        }
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s\n" +
                        "Address: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s",
                Objects.requireNonNullElse(organizationName, "[no data]"),
                Objects.requireNonNullElse(address, "[no data]"),
                Objects.requireNonNullElse(phoneNumber, "[no data]"),
                Objects.requireNonNullElse(timeCreated, "[no data]"),
                Objects.requireNonNullElse(timeLastEdit, "[no data]"));
    }
}