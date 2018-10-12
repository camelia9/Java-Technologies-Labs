package repository;

import model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {
    private List<Record> recordList = new ArrayList<>();

    public RecordRepository(){}

    public RecordRepository(List<Record> records){
        recordList = records;
    }

    public void setRecordList(List<Record>records){
        recordList = records;
    }

    public boolean canBeAdded(Record record){
        if(recordList.contains(record)){
            return false;
        }
        return true;
    }

    public void addRecordToList(Record record){

        recordList.add(record);
    }

    public List<Record> getRecordList(){
        return recordList;
    }

}
