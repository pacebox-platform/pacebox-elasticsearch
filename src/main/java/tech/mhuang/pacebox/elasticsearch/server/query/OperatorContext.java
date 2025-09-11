package tech.mhuang.pacebox.elasticsearch.server.query;

/**
 * 查询条件封装类
 *
 * @author zhangxh
 * @since 1.0.0
 */
public class OperatorContext {

    /**
     * 语法
     */
    private QueryQuota queryQuota;

    /**
     * 域
     */
    private String fieldName;

    /**
     * 值
     */
    private Object[] values;

    /**
     * 操作类型
     */
    private OperatorType operateType;

    public QueryQuota getQueryQuota() {
        return queryQuota;
    }

    public void setQueryQuota(QueryQuota queryQuota) {
        this.queryQuota = queryQuota;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public OperatorType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperatorType operateType) {
        this.operateType = operateType;
    }
}
