package cn.zhangyuying.analysis.analysis;

import cn.zhangyuying.analysis.analysis.bean.AnalysisResult;
import cn.zhangyuying.analysis.analysis.bean.BarAnalysisResult;
import cn.zhangyuying.analysis.analysis.bean.BarData;
import cn.zhangyuying.analysis.dao.RecordDao;
import cn.zhangyuying.analysis.record.bean.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyy.
 */
@Service
public class AnalysisService {
    @Autowired
    private RecordDao recordDao;

    List<AnalysisResult> getPieAnalysisData(String analysisType) {
        List<Record> records = recordDao.getAllRecord();

        Map<String, List<Record>> analysisType2RecordMap = getAnalysisType2RecordMap(analysisType, records);
        List<AnalysisResult> analysisResults = new ArrayList<>();
        for (Map.Entry<String, List<Record>> recordEntry : analysisType2RecordMap.entrySet()) {
            AnalysisResult analysisResult = AnalysisResult.analysisResultFactory(recordEntry.getKey(), recordEntry.getValue().size(), recordEntry.getValue());
            analysisResults.add(analysisResult);
        }
        return analysisResults;
    }

    private Map<String, List<Record>> getAnalysisType2RecordMap(String analysisType, List<Record> records) {
        Map<String, List<Record>> analysisType2RecordMap = new HashMap<>();
        for (Record record : records) {
            Object mapKey = record.getRecordData().get(analysisType);
            if (mapKey == null) {
                continue;
            }
            List<Record> recordList = analysisType2RecordMap.get(mapKey.toString());
            if (recordList == null) {
                recordList = new ArrayList<>();
            }
            recordList.add(record);
            analysisType2RecordMap.put(mapKey.toString(), recordList);
        }
        return analysisType2RecordMap;
    }

    List<BarAnalysisResult> getBarAnalysisData(String analysisType, String xAxis) {
        List<Record> records = recordDao.getAllRecord();
        Map<String, List<Record>> analysisType2RecordMap = getAnalysisType2RecordMap(analysisType, records);
        List<BarAnalysisResult> barAnalysisResults = new ArrayList<>();
        for (Map.Entry<String, List<Record>> recordEntry : analysisType2RecordMap.entrySet()) {
            
            Map<String, List<Record>> xAxis2RecordMap = getAnalysisType2RecordMap(xAxis, recordEntry.getValue());
            List<BarData> barDatas = new ArrayList<>();
            for (Map.Entry<String, List<Record>> xAxisEntry : xAxis2RecordMap.entrySet()) {
                BarData barData = BarData.barDataFactory(xAxisEntry.getKey(), xAxisEntry.getValue().size(), xAxisEntry.getValue());
                barDatas.add(barData);
            }
            barAnalysisResults.add(BarAnalysisResult.buildAnalysisResult(recordEntry.getKey(), barDatas));
        }
        return barAnalysisResults;
    }

}
