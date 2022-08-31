package tech.mhuang.pacebox.elasticsearch.server.query;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import tech.mhuang.pacebox.elasticsearch.model.query.ESPage;

import java.util.ArrayList;
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

    class DefaultEsQueryResult extends AbstractESQueryAware {

        private QueryContext queryContext = null;

        private ElasticsearchClient client = null;

        DefaultEsQueryResult(QueryContext queryContext, ElasticsearchClient client) {
            this.queryContext = queryContext;
            this.client = client;
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
            return results.size() == 0 ? null : results.get(0);
        }
    }
}
