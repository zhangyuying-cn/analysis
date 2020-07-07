package cn.zhangyuying.analysis.dao.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author zyy.
 */
public class TextTypeHandler<T extends Object> extends BaseTypeHandler<T> {

    private static final String DATA_FORMAT = "yyyy-MM-dd";

    private static SerializeConfig mapping = new SerializeConfig();

    private Class<T> clazz;

    public TextTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    /**
     * 把Java类型参数转换为对应的数据库类型
     *
     * @param preparedStatement 当前的PreparedStatement对象
     * @param i                 当前参数位置
     * @param t                 当前参数的Java对象
     * @param jdbcType          当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, this.toString(t));
    }

    /**
     * 获取数据结果集时把数据库类型转换为对应的Java类型
     *
     * @param resultSet 当前的结果集
     * @param s         当前的字段名称
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return this.toObject(resultSet.getString(s), clazz);
    }

    /**
     * 通过字段位置获取字段数据时把数据库类型转换为对应的Java类型
     *
     * @param resultSet 当前的结果集
     * @param i         当前字段的位置
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return this.toObject(resultSet.getString(i), clazz);
    }

    /**
     * 调用存储过程后把数据库类型的数据转换为对应的Java类型
     *
     * @param callableStatement 当前的CallableStatement执行后的CallableStatement
     * @param i                 当前输出参数的位置
     * @return
     * @throws SQLException
     */
    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.toObject(callableStatement.getString(i), clazz);
    }

    private String toString(T object) {
        try {
            if (object != null) {
                //TODO MAP转化为String
                mapping.put(Date.class, new SimpleDateFormatSerializer(DATA_FORMAT));
                return JSONObject.toJSONString(object, mapping, SerializerFeature.WriteDateUseDateFormat);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private T toObject(String content, Class<?> clazz) {
        if (StringUtils.isNotBlank(content)) {
            try {
                //TODO String转化为MAP
                return (T) JSON.parseObject(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
