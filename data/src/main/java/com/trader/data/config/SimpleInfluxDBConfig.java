package com.trader.data.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.trader.data.service.PointInfluxDBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleInfluxDBConfig {
    
    @Value("${influxdb.url:http://localhost:8086}")
    private String url;
    
    @Value("${influxdb.token:}")
    private String token;
    
    @Value("${influxdb.org:my-org}")
    private String org;
    
    @Value("${influxdb.bucket:my-bucket}")
    private String bucket;
    
    @Bean
    public InfluxDBClient influxDBClient() {
        return InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
    }
    
    @Bean
    public PointInfluxDBService influxDBService(InfluxDBClient influxDBClient) {
        return new PointInfluxDBService(influxDBClient);
    }
}
