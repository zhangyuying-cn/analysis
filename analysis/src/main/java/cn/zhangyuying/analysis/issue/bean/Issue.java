package cn.zhangyuying.analysis.issue.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyy.
 */
@Getter
@Setter
public class Issue {
    private String id;
    private Map<String, Object> metadata = new HashMap<>();
    private Date updateTime;
}
