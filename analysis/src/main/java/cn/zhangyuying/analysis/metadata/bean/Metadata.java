package cn.zhangyuying.analysis.metadata.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Metadata implements Serializable {
    private static final long serialVersionUID = -5236996819072949709L;

    private String id;
    private Field field;
    private Date updateTime;
    private Boolean currentUse;


}
