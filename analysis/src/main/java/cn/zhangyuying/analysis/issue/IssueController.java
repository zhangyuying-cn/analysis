package cn.zhangyuying.analysis.issue;

import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseStatus;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import cn.zhangyuying.analysis.issue.bean.Issue;
import cn.zhangyuying.analysis.issue.bean.IssueParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zyy.
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping(path = "issues")
    public String getAllIssue(@RequestParam IssueParam issueParam) {
        List<Issue> issues = issueService.getAllIssue(issueParam);
        return ResponseUtil.toString(new ApiResponse<List>(ResponseStatus.RESPONSE_2XX_CREATE, issues));
    }

    @PutMapping(path = "issues")
    public String addOrUpdateIssue(@RequestBody Issue issue) {
        if (issueService.selectIssueById(issue.getId()) != null) {
            issueService.updateIssue(issue);
        } else {
            issueService.insertIssue(issue);
            return ResponseUtil.toString(new ApiResponse<Issue>(ResponseStatus.RESPONSE_2XX_CREATE, issue));
        }
        return null;
    }

    @DeleteMapping(path = "issues/{id}")
    public String deleteIssue(@PathVariable String id) {
        issueService.deleteIssue(id);
        return ResponseUtil.toString(new ApiResponse<>(ResponseStatus.RESPONSE_2XX_NO_CONTENT));
    }

}
