package cn.zhangyuying.analysis.record.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zyy.
 */
@Getter
@Setter
public class PageParam {
    private Integer page;
    private Integer limit;

    public PageParam(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
}
