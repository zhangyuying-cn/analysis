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
public class BarData {
    /**
     * 横轴
     */
    private String xAxis;

    /**
     * 按照横轴区分的数量
     */
    private Integer count;

    /**
     * 数量对应的具体数据
     */
    private List<Record> records;

    private BarData(String xAxis, Integer count, List<Record> records) {
        this.xAxis = xAxis;
        this.count = count;
        this.records = records;
    }

    public static BarData barDataFactory(String xAxis, Integer count, List<Record> records) {
        return new BarData(xAxis, count, records);
    }
}
