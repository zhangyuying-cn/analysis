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
public class BarAnalysisResult {
    /**
     * 分析字段的名称
     */
    private String name;

    private List<BarData> barDatas;


    private BarAnalysisResult(String name, List<BarData> barDatas) {
        this.name = name;
        this.barDatas = barDatas;
    }

    public static BarAnalysisResult buildAnalysisResult(String name, List<BarData> barDatas) {
        return new BarAnalysisResult(name, barDatas);
    }
}


