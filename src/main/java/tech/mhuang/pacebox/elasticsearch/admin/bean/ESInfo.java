package tech.mhuang.pacebox.elasticsearch.admin.bean;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * es 实体类
 *
 * @author mhuang
 * @since 1.0.0
 */
public class ESInfo {

    /**
     * 可指定多个key,value
     */
    private Map<String, ESBean> beanMap = new ConcurrentHashMap<>();

    public Map<String, ESBean> getBeanMap() {
        return beanMap;
    }

    public void setBeanMap(Map<String, ESBean> beanMap) {
        this.beanMap = beanMap;
    }

    public static class ESBean {

        /**
         * 是否启动
         */
        private boolean enable;

        /**
         * 设置es的ip或者是对应name 默认http://127.0.0.1:9200本地
         */
        private String url = "http://127.0.0.1:9200";

        /**
         * 客户端连接数、默认20
         */
        private Integer connectNum = 20;

        /**
         * 并发数、默认10
         */
        private Integer connectPerRoute = 10;

        /**
         * 连接超时 默认10000
         */
        private Integer connectionTimeout = 10000;
        /**
         * 连接请求超时时间、默认3秒
         */
        private Integer connectionRequestTimeout = 3000;

        /**
         * socket超时时间 默认40秒
         */
        private Integer socketTimeout = 40000;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getConnectNum() {
            return connectNum;
        }

        public void setConnectNum(Integer connectNum) {
            this.connectNum = connectNum;
        }

        public Integer getConnectPerRoute() {
            return connectPerRoute;
        }

        public void setConnectPerRoute(Integer connectPerRoute) {
            this.connectPerRoute = connectPerRoute;
        }

        public Integer getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(Integer connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public Integer getConnectionRequestTimeout() {
            return connectionRequestTimeout;
        }

        public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }

        public Integer getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(Integer socketTimeout) {
            this.socketTimeout = socketTimeout;
        }
    }
}
