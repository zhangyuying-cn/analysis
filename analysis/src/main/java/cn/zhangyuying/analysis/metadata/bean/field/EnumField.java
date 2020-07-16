package cn.zhangyuying.analysis.metadata.bean.field;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnumField extends BaseField {
    private static final long serialVersionUID = -5764427394751832486L;

    private Boolean check;

    private List<Enum> enums;

}
