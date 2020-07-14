package com.gu.elasticsearch.config;

import com.gu.elasticsearch.properties.RestClientPoolProperties;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientBuilderCustomizer;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @author FastG
 * @date 2020/4/9 17:57
 */
@Configuration
@EnableConfigurationProperties(RestClientPoolProperties.class)
public class AutoEsConfig {

    @Bean
    public RestClientBuilderCustomizer restClientBuilderCustomizer(RestClientPoolProperties poolProperties
            , RestClientProperties restProperties) {
        return (builder ->
                builder
                        /**
                         * 异步httpclient连接延时配置
                         */
                        .setRequestConfigCallback(requestConfigBuilder -> {
                            requestConfigBuilder.setConnectTimeout(poolProperties.getConnectTimeOut())
                                    .setSocketTimeout(poolProperties.getSocketTimeOut())
                                    .setConnectionRequestTimeout(poolProperties.getConnectionRequestTimeOut());
                            return requestConfigBuilder;
                        })
                        /**
                         * 异步httpclient连接数配置
                         */
                        .setHttpClientConfigCallback(httpClientBuilder -> {
                            httpClientBuilder.setMaxConnTotal(poolProperties.getMaxConnectNum())
                                    .setMaxConnPerRoute(poolProperties.getMaxConnectPerRoute());

                            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
                            map.from(restProperties::getUsername).to(username -> {
                                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                                credentialsProvider.setCredentials(AuthScope.ANY,
                                        new UsernamePasswordCredentials(username, restProperties.getPassword()));
                                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            });
                            return httpClientBuilder;
                        }));
    }

//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("10.171.7.201", 9200, "http")));
//    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}
