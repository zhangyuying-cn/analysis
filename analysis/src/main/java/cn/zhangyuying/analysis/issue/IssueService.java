package cn.zhangyuying.analysis.issue;

import cn.zhangyuying.analysis.dao.IssueDao;
import cn.zhangyuying.analysis.issue.bean.Issue;
import cn.zhangyuying.analysis.issue.bean.IssueParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyy.
 */
@Service
public class IssueService {

    @Autowired
    private IssueDao issueDao;

    Issue selectIssueById(String id) {
        return issueDao.selectIssueById(id);
    }

    List<Issue> getAllIssue(IssueParam issueParam) {
        Integer offset = issueParam.getLimit() * (issueParam.getPage() - 1);
        return issueDao.getAllIssue(issueParam.getLimit(), offset);
    }

    void insertIssue(Issue issue) {
        issueDao.insertIssue(issue);
    }

    void updateIssue(Issue issue) {
        issueDao.updateIssue(issue);
    }

    void deleteIssue(String id) {
        issueDao.deleteIssue(id);
    }


}
