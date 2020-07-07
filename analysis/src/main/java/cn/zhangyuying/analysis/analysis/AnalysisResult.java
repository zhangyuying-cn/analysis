package cn.zhangyuying.analysis.analysis;

import cn.zhangyuying.analysis.issue.bean.Issue;
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
    private List<Issue> issues;

    private AnalysisResult(String name, Integer count, List<Issue> issues) {
        this.name = name;
        this.count = count;
        this.issues = issues;
    }

    static AnalysisResult buildAnalysisResult(String name, Integer count, List<Issue> issues) {
        return new AnalysisResult(name, count, issues);
    }
}
