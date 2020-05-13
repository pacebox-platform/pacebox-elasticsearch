package tech.mhuang.pacebox.elasticsearch.admin.external;

import tech.mhuang.pacebox.elasticsearch.admin.factory.IESFactory;
import tech.mhuang.pacebox.elasticsearch.server.ESFactory;

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
     * @param key
     * @return
     */
    default IESFactory create(String key) {
        return new ESFactory();
    }
}