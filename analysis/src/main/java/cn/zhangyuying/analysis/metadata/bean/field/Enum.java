package cn.zhangyuying.analysis.metadata.bean.field;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Enum implements Serializable {
    private static final long serialVersionUID = -3409583409232178759L;

    private String code;
    private String name;
    private Integer order;

}
