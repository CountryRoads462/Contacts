package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Record> records = new ArrayList<>();

    public List<Record> getRecords() {
        return records;
    }

    public void deleteRecord(int index) {
        records.remove(index);
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecordByIndex(int index) {
        return records.get(index);
    }

    @Override
    public String toString() {
        int numberOfRecord = 1;
        StringBuilder str = new StringBuilder();
        for (Record record :
                records) {
            str.append(numberOfRecord).append(". ");
            if (record instanceof PersonRecord) {
                str.append(record.getShortRepresentation());
            } else {
                str.append(record.getShortRepresentation());
            }
            numberOfRecord++;
        }
        return str.toString();
    }
}
