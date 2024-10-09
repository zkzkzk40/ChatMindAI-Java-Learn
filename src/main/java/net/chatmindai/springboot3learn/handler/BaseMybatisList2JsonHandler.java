package net.chatmindai.springboot3learn.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * mybatis的 list 和 json互转的基础处理程序
 *
 * @author zk
 * @date 2024/01/09
 */
public abstract class BaseMybatisList2JsonHandler<T> extends BaseTypeHandler<List<T>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtil.toJsonStr(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String data = rs.getString(columnName);
        return StrUtil.isBlank(data) ? null : JSONUtil.toList(data, (Class<T>) getRawType());
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String data = rs.getString(columnIndex);
        return StrUtil.isBlank(data) ? null : JSONUtil.toList(data, (Class<T>) getRawType());
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String data = cs.getString(columnIndex);
        return StrUtil.isBlank(data) ? null : JSONUtil.toList(data, (Class<T>) getRawType());
    }

    @Override
    public List<T> getResult(ResultSet rs, String columnName) throws SQLException {
        return super.getResult(rs, columnName);
    }

    @Override
    public List<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return super.getResult(rs, columnIndex);
    }

    @Override
    public List<T> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return super.getResult(cs, columnIndex);
    }
}

