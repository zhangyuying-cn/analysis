package cn.zhangyuying.analysis.metadata.bean.field;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseField implements Serializable {

    private static final long serialVersionUID = 5098804351069356991L;

    private String key;
    private String name;
//    private boolean require;
//    private boolean unique;
    private Object defaultValue;

}
