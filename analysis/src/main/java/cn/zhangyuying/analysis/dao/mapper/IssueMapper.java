package cn.zhangyuying.analysis.dao.mapper;

import cn.zhangyuying.analysis.issue.bean.Issue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zyy.
 */
public interface IssueMapper {
    Issue selectIssueById(@Param("id") String id);

    List<Issue> getAllIssue(@Param("limit") Integer limit, @Param("offset") Integer offset);

    void insertIssue(@Param("issue") Issue issue);

    void insertIssues(@Param("issues") List<Issue> issues);

    void updateIssue(@Param("issue") Issue issue);

     void deleteIssue(@Param("id") String id);


}
