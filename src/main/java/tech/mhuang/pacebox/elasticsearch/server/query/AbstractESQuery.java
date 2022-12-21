package tech.mhuang.pacebox.elasticsearch.server.query;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

/**
 * 扩展查询类
 *
 * @author zhangxh
 * @since 1.0.0
 */
public abstract class AbstractESQuery {

    protected final ElasticsearchClient client;

    /**
     * 包装RestHighLevelClient
     *
     * @param client client
     */
    public AbstractESQuery(ElasticsearchClient client) {
        this.client = client;
    }

    /**
     * 查询抽象
     *
     * @param queryContext 查询全文
     * @return 查询
     */
    public abstract ESQueryAware query(QueryContext queryContext);
}
