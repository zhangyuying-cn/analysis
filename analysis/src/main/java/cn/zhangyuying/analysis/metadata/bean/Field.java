package cn.zhangyuying.analysis.metadata.bean;

import cn.zhangyuying.analysis.metadata.bean.field.BaseField;
import cn.zhangyuying.analysis.metadata.bean.field.DateField;
import cn.zhangyuying.analysis.metadata.bean.field.EnumField;
import cn.zhangyuying.analysis.metadata.bean.field.StringField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zyy.
 */
@Setter
@Getter
public class Field implements Serializable {

    private static final long serialVersionUID = 7031379063905155905L;
    private List<StringField> stringField;
    private List<DateField> dateField;
    private List<EnumField> enumField;

}
