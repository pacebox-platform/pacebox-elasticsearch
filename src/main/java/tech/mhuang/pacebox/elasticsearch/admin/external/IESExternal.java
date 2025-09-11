package tech.mhuang.pacebox.elasticsearch.admin.external;

import tech.mhuang.pacebox.elasticsearch.admin.factory.IESFactory;
import tech.mhuang.pacebox.elasticsearch.server.ESFactory;
import tech.mhuang.pacebox.json.DefaultJsonService;

/**
 * es扩展
 *
 * @author mhuang
 * @since 1.0.0
 */
public interface IESExternal {

    /**
     * 创建
     *
     * @param key 组件
     * @return es工厂
     */
    default IESFactory create(String key) {
        IESFactory factory = new ESFactory();
        factory.setJsonConvert(new DefaultJsonService());
        return factory;
    }
}