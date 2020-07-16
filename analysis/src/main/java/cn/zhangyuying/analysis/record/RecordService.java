package cn.zhangyuying.analysis.record;

import cn.zhangyuying.analysis.dao.RecordDao;
import cn.zhangyuying.analysis.record.bean.Record;
import cn.zhangyuying.analysis.record.bean.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyy.
 */
@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;

    Record selectRecordById(String id) {
        return recordDao.selectRecordById(id);
    }

    List<Record> getAllRecord(PageParam recordParam) {
        if (recordParam == null) {
            return recordDao.getAllRecord();
        }
        Integer offset = recordParam.getLimit() * (recordParam.getPage() - 1);
        return recordDao.getAllRecord(recordParam.getLimit(), offset);
    }

    void insertRecord(Record record) {
        recordDao.insertRecord(record);
    }
    void insertRecords(List<Record> records) {
        recordDao.insertRecords(records);
    }

    void updateRecords(List<Record> records) {
        recordDao.updateRecords(records);
    }

    void updateRecord(Record record) {
        recordDao.updateRecord(record);
    }

    void deleteRecord(String id) {
        recordDao.deleteRecord(id);
    }


}
