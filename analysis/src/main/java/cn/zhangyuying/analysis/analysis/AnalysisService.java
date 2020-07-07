package cn.zhangyuying.analysis.analysis;

import cn.zhangyuying.analysis.dao.IssueDao;
import cn.zhangyuying.analysis.issue.bean.Issue;
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
    private IssueDao issueDao;

    List<AnalysisResult> analysisData(String analysisType) {
        List<Issue> issues = issueDao.getAllIssue();

        Map<String, List<Issue>> analysisType2IssueMap = getAnalysisType2IssueMap(analysisType, issues);
        List<AnalysisResult> analysisResults = new ArrayList<>();
        for (Map.Entry<String, List<Issue>> issueEntry : analysisType2IssueMap.entrySet()) {
            AnalysisResult analysisResult = AnalysisResult.buildAnalysisResult(issueEntry.getKey(), issueEntry.getValue().size(), issueEntry.getValue());
            analysisResults.add(analysisResult);
        }
        return analysisResults;
    }

    private Map<String, List<Issue>> getAnalysisType2IssueMap(String analysisType, List<Issue> issues) {
        Map<String, List<Issue>> analysisType2IssueMap = new HashMap<>();
        for (Issue issue : issues) {
            Object mapKey = issue.getMetadata().get(analysisType);
            if (mapKey == null) {
                continue;
            }
            List<Issue> issueList = analysisType2IssueMap.get(mapKey.toString());
            if (issueList == null) {
                issueList = new ArrayList<>();
            }
            issueList.add(issue);
            analysisType2IssueMap.put(mapKey.toString(), issueList);
        }
        return analysisType2IssueMap;
    }

}
