package tech.mhuang.pacebox.elasticsearch.admin;

import tech.mhuang.pacebox.core.builder.BaseBuilder;
import tech.mhuang.pacebox.elasticsearch.admin.bean.ESInfo;

/**
 * elasticsearch 构造器
 *
 * @author mhuang
 * @since 1.0.0
 */
public class ESBuilder {

    /**
     * 构建jwt构造器
     *
     * @return Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class ProducerBuilder implements BaseBuilder<ESInfo.ESBean> {

        private ESInfo.ESBean info;

        public ProducerBuilder() {
            this.info = new ESInfo.ESBean();
        }

        /**
         * 配置是否启动配置、默认不启动、要使用需要设置为启动
         *
         * @param enable 启动
         * @return ProducerBuilder
         */
        public ProducerBuilder enable(boolean enable) {
            this.info.setEnable(enable);
            return this;
        }

        /**
         * 连接url
         *
         * @param url url
         * @return this
         */
        public ProducerBuilder setUrl(String url) {
            this.info.setUrl(url);
            return this;
        }

        /**
         * 设置客户端连接数 默认20
         *
         * @param connectNum 客户端连接数
         * @return this
         */
        public ProducerBuilder connectNum(Integer connectNum) {
            this.info.setConnectNum(connectNum);
            return this;
        }

        /**
         * 设置连接并发数 默认10
         *
         * @param connectPerRoute 连接并发数
         * @return this
         */
        public ProducerBuilder connectPerRoute(Integer connectPerRoute) {
            this.info.setConnectPerRoute(connectPerRoute);
            return this;
        }

        /**
         * 设置连接超时时间毫秒级 默认3000 3秒
         *
         * @param connectionRequestTimeout 连接超时时间
         * @return this
         */
        public ProducerBuilder connectionRequestTimeout(Integer connectionRequestTimeout) {
            this.info.setConnectionRequestTimeout(connectionRequestTimeout);
            return this;
        }

        /**
         * 设置socket超时时间毫秒级 默认40000 40秒
         *
         * @param socketTimeout socket超时时间
         * @return this
         */
        public ProducerBuilder socketTimeout(Integer socketTimeout) {
            this.info.setSocketTimeout(socketTimeout);
            return this;
        }

        /**
         * 设置客户端连接超时时间毫秒级 默认10000 10秒
         *
         * @param connectionTimeout 客户端连接超时时间
         * @return ProducerBuilder
         */
        public ProducerBuilder connectionTimeout(Integer connectionTimeout) {
            this.info.setConnectionTimeout(connectionTimeout);
            return this;
        }

        @Override
        public ESInfo.ESBean builder() {
            return this.info;
        }
    }

    public static class Builder implements BaseBuilder<ESFramework> {

        private ESInfo info;

        Builder() {
            this.info = new ESInfo();
        }


        /**
         * 创建jwt生产构造器
         *
         * @return jwt生产构造器
         */
        public ProducerBuilder createProducerBuilder() {
            return new ProducerBuilder();
        }

        public Builder bindProducer(String key, ESInfo.ESBean value) {
            info.getBeanMap().put(key, value);
            return this;
        }

        /**
         * 构建
         *
         * @return ESFramework es框架
         */
        @Override
        public ESFramework builder() {
            return new ESFramework(this.info);
        }
    }
}
