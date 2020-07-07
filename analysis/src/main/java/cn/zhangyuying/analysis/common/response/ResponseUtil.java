package cn.zhangyuying.analysis.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zyy.
 */
public class ResponseUtil {
    private static final String DATAFORMAT = "yyyy-MM-dd";
    private static final String DATAFORMAT_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    private static SerializeConfig mapping = new SerializeConfig();
    private static List<String> authorityFilteName = Arrays.asList("compressData", "authorityVO", "roleGroupVO");
    private static PropertyFilter authorityProfilter = new PropertyFilter() {
        @Override
        public boolean apply(Object object, String name, Object value) {
            if (value == null) {
                return false;
            }
            if (authorityFilteName.contains(name)) {
                return false;
            }
            if (value instanceof String) {
                return StringUtils.isNotBlank((String) value);
            }
            return true;
        }

    };

    public static String toString(ApiResponse apiResult) {
        if (apiResult == null) {
            return null;
        }

        JSON.DEFFAULT_DATE_FORMAT = DATAFORMAT;
        mapping.put(Date.class, new SimpleDateFormatSerializer(DATAFORMAT_TIME));
        return JSONObject.toJSONString(apiResult, mapping, authorityProfilter, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
    }
}
