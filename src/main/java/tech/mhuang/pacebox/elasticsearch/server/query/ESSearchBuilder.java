package tech.mhuang.pacebox.elasticsearch.server.query;

import tech.mhuang.pacebox.elasticsearch.model.query.ESOrder;

/**
 * ES查询封装
 *
 * @author zhangxh
 * @since 1.0.0
 */
public class ESSearchBuilder {


    private final QueryContext queryContext = new QueryContext();

    /**
     * 新增查询条件
     *
     * @param field 字段
     * @param type 类型
     * @param values 值
     * @return 构建器
     */
    public ESSearchBuilder addCondition(String field, OperatorType type, Object... values) {
        this.addContext(field, type, QueryQuota.CONDITION, values);
        return this;
    }


    /**
     * 新增类似于 and 操作符
     *
     * @return 构建器
     */
    public ESSearchBuilder and() {
        this.addContext(null, null, QueryQuota.AND);
        return this;
    }

    /**
     * 添加查询上下文
     *
     * @param field 字段
     * @param type 类型
     * @param queryQuota 查询方式
     * @param values 值
     */
    private void addContext(String field, OperatorType type, QueryQuota queryQuota, Object... values) {
        OperatorContext context = new OperatorContext();
        context.setFieldName(field);
        context.setQueryQuota(queryQuota);
        context.setValues(values);
        context.setOperateType(type);
        queryContext.addContext(context);
    }

    /**
     * 新增 操作符 or
     *
     * @return 构建器
     */
    public ESSearchBuilder or() {
        this.addContext(null, null, QueryQuota.OR);
        return this;
    }

    /**
     * 新增左括号
     *
     * @return 构建器
     */
    public ESSearchBuilder startInnerCondition() {
        this.addContext(null, null, QueryQuota.LT);
        return this;
    }

    /**
     * 新增右边括号
     *
     * @return 构建器
     */
    public ESSearchBuilder endInnerCondition() {
        this.addContext(null, null, QueryQuota.GT, (Object) null);
        return this;
    }

    /**
     * 分页查询的scrollId
     *
     * @param scrollId 分页的scrollId
     * @return 构建器
     */
    public ESSearchBuilder scrollId(String scrollId) {
        this.queryContext.scrollId(scrollId);
        return this;
    }

    /**
     * 集合查询的开始值
     *
     * @param from 开始值
     * @return 构建器
     */
    public ESSearchBuilder from(Integer from) {
        this.queryContext.from(from);
        return this;
    }

    /**
     * 分页Scroll查询的超时时间
     *
     * @param minute 时间
     * @return 构建器
     */
    public ESSearchBuilder scrollTimeout(long minute) {
        this.queryContext.scrollTimeout(minute);
        return this;
    }

    /**
     * 返回条数
     *
     * @param size 条数
     * @return 构建器
     */
    public ESSearchBuilder size(Integer size) {
        this.queryContext.size(size);
        return this;
    }

    /**
     * 搜索的索引
     *
     * @param indexNames 索引名称
     * @return 构建器
     */
    public ESSearchBuilder indexNames(String[] indexNames) {
        this.queryContext.indexNames(indexNames);
        return this;
    }

    /**
     * 查询排除字段
     *
     * @param excludeFields 排除字段数组
     * @return 构建器
     */
    public ESSearchBuilder excludeFields(String[] excludeFields) {
        this.queryContext.excludeFields(excludeFields);
        return this;
    }

    /**
     * 查询包含字段
     *
     * @param includeFields 引用字段数组
     * @return 构建器
     */
    public ESSearchBuilder includeFields(String[] includeFields) {
        this.queryContext.includeFields(includeFields);
        return this;
    }

    /**
     * 排序
     *
     * @param field 字段
     * @param orderType 排序方式
     * @return 构建器
     */
    public ESSearchBuilder order(String field, OrderType orderType) {
        this.queryContext.addOrder(new ESOrder(field, orderType));
        return this;
    }

    /**
     * 不带条件查询
     *
     * @return 构建器
     */
    public ESSearchBuilder all() {
        return this;
    }

    /**
     * 查询全文
     *
     * @return 查询全文
     */
    public QueryContext getQueryContext() {
        return queryContext;
    }
}
