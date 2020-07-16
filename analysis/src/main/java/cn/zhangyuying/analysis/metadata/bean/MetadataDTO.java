package cn.zhangyuying.analysis.metadata.bean;

import cn.zhangyuying.analysis.metadata.bean.field.DateField;
import cn.zhangyuying.analysis.metadata.bean.field.EnumField;
import cn.zhangyuying.analysis.metadata.bean.field.StringField;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author zyy.
 */
@Setter
@Getter
public class MetadataDTO {
    private String id;

    private List<StringField> stringField;
    private List<DateField> dateField;
    private List<EnumField> enumField;
    private Date updateTime;
    private BooleanUtils currentUse;

}
