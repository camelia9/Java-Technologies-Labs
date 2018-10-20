package repository;

import model.Login;
import model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RecordRepository {
    private List<Record> recordList = new ArrayList<>();
    private static Map<Integer, Record> records;

    static {
        records = new ConcurrentHashMap<>();
    }

    public RecordRepository(){}

    public RecordRepository(List<Record> records){
        recordList = records;
    }

    public void setRecordList(List<Record>records){
        recordList = records;
    }

    public boolean canBeAdded(Record record){
        return !records.containsKey(record.hashCode());
    }

    public void addRecord(Record record){
        records.put(record.hashCode(), record);
    }

    public Record getUser(Login login){
        return records.getOrDefault(login.hashCode(), null);
    }

    public Record getUser(Integer loginHash){
        return records.getOrDefault(loginHash, null);
    }

    public List<Record> getRecordList(){
        return recordList;
    }

}
