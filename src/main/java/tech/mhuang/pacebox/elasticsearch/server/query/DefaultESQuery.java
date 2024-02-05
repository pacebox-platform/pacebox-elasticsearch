package tech.mhuang.pacebox.elasticsearch.server.query;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import tech.mhuang.pacebox.elasticsearch.model.query.ESPage;

import java.util.Collections;
import java.util.List;

/**
 * 默认的查询实现
 *
 * @author zhangxh
 * @since 1.0.0
 */
@Slf4j
public class DefaultESQuery extends AbstractESQuery {

    public DefaultESQuery(ElasticsearchClient client) {
        super(client);
    }

    @Override
    public ESQueryAware query(QueryContext queryContext) {
        return new DefaultEsQueryResult(queryContext, client);
    }

    static class DefaultEsQueryResult extends AbstractESQueryAware {

        private final QueryContext queryContext;

        DefaultEsQueryResult(QueryContext queryContext, ElasticsearchClient client) {
            this.queryContext = queryContext;
        }

        @Override
        public <T> List<T> list(Class<T> clz) {
            return Collections.EMPTY_LIST;
        }

        @Override
        public <T> ESPage<T> page(Class<T> clz) {
            return new ESPage<>();
        }

        @Override
        public <T> T get(Class<T> clz) {
            this.queryContext.size(1);
            List<T> results = this.list(clz);
            return results.isEmpty() ? null : results.getFirst();
        }
    }
}
