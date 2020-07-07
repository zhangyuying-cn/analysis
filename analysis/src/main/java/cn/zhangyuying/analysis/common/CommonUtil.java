package cn.zhangyuying.analysis.common;

import java.util.UUID;

/**
 * @author zyy.
 */
public final class CommonUtil {
    private CommonUtil() {
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
