package cn.zhangyuying.analysis.dao.mapper;

import cn.zhangyuying.analysis.record.bean.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyy.
 */
public interface RecordMapper {

    Record selectRecordById(@Param("id") String id);

//    List<String> selectRecordIdById(@Param("id") String id);

    List<Record> getAllRecord(@Param("limit") Integer limit, @Param("offset") Integer offset);

    void insertRecord(@Param("record") Record record);

    void insertRecords(@Param("records") List<Record> records);

    void updateRecord(@Param("record") Record record);

    void updateRecords(@Param("records") List<Record> records);

     void deleteRecord(@Param("id") String id);


}
