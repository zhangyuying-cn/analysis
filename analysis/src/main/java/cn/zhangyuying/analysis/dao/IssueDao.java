package cn.zhangyuying.analysis.dao;

import cn.zhangyuying.analysis.dao.mapper.IssueMapper;
import cn.zhangyuying.analysis.issue.bean.Issue;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyy.
 */
@Repository
public class IssueDao {

    private final IssueMapper issueMapper;

    public IssueDao(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    public List<Issue> getAllIssue() {
        return issueMapper.getAllIssue(null, null);
    }

    public List<Issue> getAllIssue(Integer limit, Integer offset) {
        return issueMapper.getAllIssue(limit, offset);
    }

    public Issue selectIssueById(String id) {
        return issueMapper.selectIssueById(id);
    }

    public void insertIssue(Issue issue) {
        issueMapper.insertIssue(issue);
    }

    public void insertIssues(List<Issue> issues) {
        issueMapper.insertIssues(issues);
    }

    public void updateIssue(Issue issue) {
        issueMapper.updateIssue(issue);
    }

    public void deleteIssue(String id) {
        issueMapper.deleteIssue(id);
    }

}
