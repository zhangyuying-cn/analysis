package cn.zhangyuying.analysis.record.bean;

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
public class Record {
    private String id;
    private Map<String, Object> recordData = new HashMap<>();
    private Date updateTime;
}
