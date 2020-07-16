package cn.zhangyuying.analysis.dao;

import cn.zhangyuying.analysis.dao.mapper.RecordMapper;
import cn.zhangyuying.analysis.record.bean.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyy.
 */
@Repository
public class RecordDao {

    private final RecordMapper recordMapper;

    public RecordDao(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    public List<Record> getAllRecord() {
        return recordMapper.getAllRecord(null, null);
    }

    public List<Record> getAllRecord(Integer limit, Integer offset) {
        return recordMapper.getAllRecord(limit, offset);
    }

    public Record selectRecordById(String id) {
        return recordMapper.selectRecordById(id);
    }

    public void insertRecord(Record record) {
        recordMapper.insertRecord(record);
    }

    public void insertRecords(List<Record> records) {
        recordMapper.insertRecords(records);
    }

    public void updateRecord(Record record) {
        recordMapper.updateRecord(record);
    }

    public void updateRecords(List<Record> records) {
        recordMapper.updateRecords(records);
    }

    public void deleteRecord(String id) {
        recordMapper.deleteRecord(id);
    }

}
