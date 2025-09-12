package tech.mhuang.pacebox.elasticsearch.model.query;


import java.util.List;

/**
 * 分页信息
 */
public class ESPage<T> {
    /**
     * 总数
     */
    private long total;

    /**
     * scrollId
     */
    private String scrollId;

    /**
     * 当前行数
     */
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
