package tech.mhuang.pacebox.elasticsearch.admin;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest5_client.Rest5ClientTransport;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import co.elastic.clients.transport.rest5_client.low_level.Rest5ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.mhuang.pacebox.core.util.StringUtil;
import tech.mhuang.pacebox.elasticsearch.admin.bean.ESInfo;
import tech.mhuang.pacebox.elasticsearch.admin.external.IESExternal;
import tech.mhuang.pacebox.elasticsearch.admin.factory.IESFactory;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * es平台实现
 *
 * @author mhuang
 * @since 1.0.0
 */
public class ESFramework {
    private static final Logger log = LoggerFactory.getLogger(ESFramework.class);
    /**
     * elasticsearch配置信息
     */
    private final ESInfo info;

    /**
     * elasticsearch external
     */
    private IESExternal external;

    private final Map<String, IESFactory> factory = new ConcurrentHashMap<>();

    /**
     * 获取容器
     *
     * @param key 获取容器的key
     * @return IESFactory
     */
    public IESFactory getFactory(String key) {
        return this.factory.get(key);
    }

    public ESFramework(ESInfo info) {
        this.info = info;
    }

    public ESFramework external(IESExternal external) {
        this.external = external;
        return this;
    }

    /**
     * 启动
     */
    public void start() {
        if (this.external == null) {
            this.external = new IESExternal() {
            };
        }
        log.info("正在启动elasticsearch...");
        log.info("正在装载elasticsearch配置,{}", info);
        info.getBeanMap().forEach((key, bean) -> {
            //配置开启才加载
            if (bean.isEnable()) {
                ElasticsearchClient client = loadProperties(bean);
                IESFactory esFactory = external.create(key);
                esFactory.setClient(client);
                esFactory.setName(key);
                factory.put(key, esFactory);
            }
        });
        log.info("elasticsearch 配置装载完成...");
        log.info("elasticsearch 启动成功");
    }

    /**
     * 装载配置
     */
    private ElasticsearchClient loadProperties(ESInfo.ESBean bean) {
        String urls = bean.getUrl();
        List<URI> hostList = Arrays.stream(Objects.requireNonNull(StringUtil.split(urls, ","))).map(URI::create).toList();
        Rest5ClientBuilder restClient = Rest5Client.builder(hostList);
        Rest5ClientTransport transport = new Rest5ClientTransport(restClient.build(), new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}