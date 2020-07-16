package cn.zhangyuying.analysis.analysis.bean;

import cn.zhangyuying.analysis.record.bean.Record;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zyy.
 */
@Getter
@Setter
public class AnalysisResult {
    /**
     * 分析字段的名称
     */
    private String name;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 当前name下对应的数据
     */
    private List<Record> records;

    private AnalysisResult(String name, Integer count, List<Record> records) {
        this.name = name;
        this.count = count;
        this.records = records;
    }

    public static AnalysisResult analysisResultFactory(String name, Integer count, List<Record> records) {
        return new AnalysisResult(name, count, records);
    }
}
