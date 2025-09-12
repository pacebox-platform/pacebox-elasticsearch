package tech.mhuang.pacebox.elasticsearch.admin.factory;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import tech.mhuang.pacebox.elasticsearch.server.query.AbstractESQuery;
import tech.mhuang.pacebox.elasticsearch.server.query.ESSearchBuilder;
import tech.mhuang.pacebox.json.BaseJsonService;

import java.io.IOException;

/**
 * es接口层
 *
 * @author mhuang
 * @since 1.0.0
 */
public interface IESFactory {

    void setName(String name);

    void setClient(ElasticsearchClient client);


    void setJsonConvert(BaseJsonService jsonConvert);

    /**
     * 获取构造器
     *
     * @return ESSearchBuilder
     */
    ESSearchBuilder getBuilder();

    /**
     * 获取es查询实现类
     *
     * @return AbstractESQuery
     */
    AbstractESQuery getQuery();

    /**
     * 新增
     *
     * @param t 新增的数据
     * @return String 返回对应的id
     */
    <T> IndexResponse insert(T t) throws IOException;

    /**
     * 新增
     *
     * @param data  新增的数据
     * @param index 新增的数据中的索引
     * @return String 返回id
     * @throws IOException io异常
     */
    <T> IndexResponse insert(T data, String index) throws IOException;

    /**
     * 新增
     *
     * @param data  新增的数据
     * @param index 新增的数据中的索引
     * @return String 返回id
     * @throws IOException io异常
     */
    IndexResponse insert(String data, String index) throws IOException;

    /**
     * 修改
     *
     * @param t  修改的数据
     * @param id 修改的id
     * @return UpdateResponse
     * @throws IOException io异常
     */
    <T> UpdateResponse update(T t, String id) throws IOException;

    /**
     * 数据更新
     *
     * @param data  更新的数据
     * @param index 更新的索引
     * @param id    更新的id
     * @return UpdateResponse
     * @throws IOException io异常
     */
    UpdateResponse update(String data, String index, String id) throws IOException;

    /**
     * 数据更新
     *
     * @param data  更新的数据
     * @param index 更新的索引
     * @param id    更新的id
     * @return UpdateResponse
     * @throws IOException io异常
     */
    <T> UpdateResponse update(T data, String index, String id) throws IOException;

    /**
     * 删除索引及数据
     *
     * @param index 索引
     * @return AcknowledgedResponse
     * @throws IOException IO异常
     */
    AcknowledgedResponse delete(String index) throws IOException;

    /**
     * 通过id删除数据
     *
     * @param index 删除的索引
     * @param id    删除的id
     * @return DeleteResponse
     * @throws IOException IO异常
     */
    DeleteResponse delete(String index, String id) throws IOException;

    /**
     * 获取链接
     *
     * @return TransportClient 返回链接对象
     */
    ElasticsearchClient getClient();

}
