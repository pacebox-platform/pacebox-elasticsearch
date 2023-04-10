package tech.mhuang.pacebox.elasticsearch.server;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.mhuang.pacebox.core.check.CheckAssert;
import tech.mhuang.pacebox.elasticsearch.admin.factory.IESFactory;
import tech.mhuang.pacebox.elasticsearch.server.annoation.ESTable;
import tech.mhuang.pacebox.elasticsearch.server.query.AbstractESQuery;
import tech.mhuang.pacebox.elasticsearch.server.query.DefaultESQuery;
import tech.mhuang.pacebox.elasticsearch.server.query.ESSearchBuilder;

import java.io.IOException;


/**
 * es工厂实现类
 *
 * @author mhuang
 * @since 1.0.0
 */
public class ESFactory implements IESFactory {

    private String name;

    private ElasticsearchClient client;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setClient(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public ESSearchBuilder getBuilder() {
        return new ESSearchBuilder();
    }

    @Override
    public AbstractESQuery getQuery() {
        return new DefaultESQuery(this.client);
    }


    /**
     * 插入
     *
     * @param t 插入的数据
     * @return IndexResponse
     */
    @Override
    public <T> IndexResponse insert(T t) throws IOException {
        ESTable esTable = checkESTable(t);
        return insert(t, esTable.index());
    }

    @Override
    public <T> IndexResponse insert(T t, String index) throws IOException {
        return insert(packEntity(t), index);
    }

    @Override
    public IndexResponse insert(String data, String index) throws IOException {
        return baseInsert(data, index);
    }

    private <T> String packEntity(T t) {
        return JSON.toJSONString(t);
    }

    private IndexResponse baseInsert(String data, String index) throws IOException {
        logger.debug("===正在插入ES数据=====");
        logger.debug("放入得ES数据为{}", data);
        JSONObject jsonObject = JSON.parseObject(data);
        IndexRequest indexRequest = IndexRequest.of(i -> i.index(index).document(jsonObject));
        //此处判断是否有埋点，有的话则进行埋点处理
        IndexResponse response = client.index(indexRequest);
        //此处判断是否有埋点,有的话则埋点处理
        logger.debug("===放入ES数据完毕=====,response:{}", response);
        return response;
    }

    private <T> ESTable checkESTable(T model) {
        ESTable esTable = null;
        if (model.getClass().isAnnotationPresent(ESTable.class)) {
            esTable = model.getClass().getAnnotation(ESTable.class);
        }
        assert esTable != null;
        return CheckAssert.check(esTable, "当前保存的对象不是ES对象");
    }

    /**
     * ES更新
     *
     * @param t 更新的对象
     * @return UpdateResponse
     * @see IESFactory#update(java.lang.Object, java.lang.String)
     */
    @Override
    public <T> UpdateResponse update(T t, String id) throws IOException {
        ESTable esTable = checkESTable(t);
        return update(t, esTable.index(), id);
    }

    @Override
    public <T> UpdateResponse update(T t, String index, String id) throws IOException {
        return update(packEntity(t), index, id);
    }

    @Override
    public AcknowledgedResponse delete(String index) throws IOException {
        logger.debug("===正在删除ES数据=====,index:{}", index);
        DeleteIndexRequest deleteRequest = DeleteIndexRequest.of(d -> d.index(index));
        AcknowledgedResponse response = client.indices().delete(deleteRequest);
        logger.debug("===删除ES数据完毕=====,response:{}", response);
        return response;
    }

    @Override
    public DeleteResponse delete(String index, String id) throws IOException {
        logger.debug("===正在删除ES数据=====,index:{},id:{}", index, id);
        DeleteRequest deleteRequest = DeleteRequest.of(d -> d.index(index).id(id));
        DeleteResponse response = client.delete(deleteRequest);
        logger.debug("===删除ES数据完毕=====,response:{}", response);
        return response;
    }

    @Override
    public UpdateResponse update(String data, String index, String id) throws IOException {
        return baseUpdate(data, index, id);
    }

    private UpdateResponse baseUpdate(String data, String index, String id) throws IOException {
        logger.debug("===正在修改ES数据=====");
        logger.debug("修改ES数据为{}，id为:{}", data, id);
        JSONObject jsonObject = JSON.parseObject(data);
        UpdateRequest updateRequest = UpdateRequest.of(u -> u.index(index).id(id).doc(jsonObject));
        UpdateResponse response = client.update(updateRequest, JSONObject.class);
        logger.debug("打印应答的数据是:{}", response);
        return response;

    }

    @Override
    public ElasticsearchClient getClient() {
        return client;
    }
}
