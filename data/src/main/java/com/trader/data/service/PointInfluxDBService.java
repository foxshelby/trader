package com.trader.data.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Map;

@Service
public class PointInfluxDBService {

    private final WriteApiBlocking writeApi;
    private final QueryApi queryApi;

    public PointInfluxDBService(InfluxDBClient influxDBClient) {
        this.writeApi = influxDBClient.getWriteApiBlocking();
        this.queryApi = influxDBClient.getQueryApi();
    }

    /**
     * 使用Point写入数据（不需要定义POJO类）
     */
    public void writePoint(String measurement, Map<String, String> tags,
                           Map<String, String> fields, Instant time) {
        Point point = Point.measurement(measurement)
                .time(time, WritePrecision.NS);

        // 添加标签
        if (tags != null) {
            for (Map.Entry<String, String> tag : tags.entrySet()) {
                point.addTag(tag.getKey(), tag.getValue());
            }
        }

        // 添加字段
        if (fields != null) {
            for (Map.Entry<String, String> field : fields.entrySet()) {
                point.addField(field.getKey(), field.getValue());
            }
        }

        writeApi.writePoint(point);
    }

//    /**
//     * 示例：写入温度数据
//     */
//    public void writeTemperaturePoint(String location, Double value) {
//        Map<String, String> tags = Map.of("location", location);
//        Map<String, Object> fields = Map.of("value", value);
//
//        writePoint("temperature", tags, fields, Instant.now());
//    }
//
//    /**
//     * 示例：写入CPU使用率
//     */
//    public void writeCpuUsagePoint(String host, String region, Double usage) {
//        Map<String, String> tags = Map.of(
//                "host", host,
//                "region", region
//        );
//        Map<String, Object> fields = Map.of("usage", usage);
//
//        writePoint("cpu_usage", tags, fields, Instant.now());
//    }
}