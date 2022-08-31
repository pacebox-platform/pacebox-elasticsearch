package tech.mhuang.pacebox.elasticsearch.server.query;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

/**
 * 扩展查询类
 *
 * @author zhangxh
 * @since 1.0.0
 */
public abstract class AbstractESQuery {

    protected ElasticsearchClient client;

    /**
     * 包装RestHighLevelClient
     *
     * @param client
     */
    public AbstractESQuery(ElasticsearchClient client) {
        this.client = client;
    }

    /**
     * 查询抽象
     *
     * @param queryContext 查询全文
     * @return
     */
    public abstract ESQueryAware query(QueryContext queryContext);
}
