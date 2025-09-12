package tech.mhuang.pacebox.elasticsearch.server.query;

import tech.mhuang.pacebox.elasticsearch.model.query.ESOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * 可执行的上下文类
 *
 * @author zhangxh
 * @since 1.0.0
 */
public class QueryContext {

    public static final long SCROLL_TIMEOUT_DEFAULT = 30L;

    /**
     * 每次显示行数
     */
    private Integer size;

    private Integer from;

    /**
     * 分钟数
     */
    private Long scrollTimeout;

    private String scrollId;

    private String[] indexNames;

    private String[] includeFields;

    private String[] excludeFields;

    private final List<ESOrder> orders = new ArrayList<>(0);

    /**
     * 查询语法存储
     */
    final List<OperatorContext> contexts = new ArrayList<>(0);

    void addContext(OperatorContext context) {
        contexts.add(context);
    }

    void scrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    void from(Integer from) {
        this.from = from;
    }

    void size(Integer size) {
        this.size = size;
    }

    void indexNames(String[] indexNames) {
        this.indexNames = indexNames;
    }

    void includeFields(String[] includeFields) {
        this.includeFields = includeFields;
    }

    void excludeFields(String[] excludeFields) {
        this.excludeFields = excludeFields;
    }

    void addOrder(ESOrder order) {
        orders.add(order);
    }

    void scrollTimeout(long minute) {
        this.scrollTimeout = minute;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Long getScrollTimeout() {
        return scrollTimeout;
    }

    public void setScrollTimeout(Long scrollTimeout) {
        this.scrollTimeout = scrollTimeout;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public String[] getIndexNames() {
        return indexNames;
    }

    public void setIndexNames(String[] indexNames) {
        this.indexNames = indexNames;
    }

    public String[] getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(String[] includeFields) {
        this.includeFields = includeFields;
    }

    public String[] getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(String[] excludeFields) {
        this.excludeFields = excludeFields;
    }

    public List<ESOrder> getOrders() {
        return orders;
    }

    public List<OperatorContext> getContexts() {
        return contexts;
    }
}
