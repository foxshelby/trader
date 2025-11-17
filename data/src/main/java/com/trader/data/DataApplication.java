package com.trader.data;

import com.trader.data.service.PointInfluxDBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DataApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DataApplication.class, args);

        PointInfluxDBService bean = run.getBean(PointInfluxDBService.class);
        Map<String, String> tags = new HashMap<>();
        tags.put("trader", "trader");
         bean.writePoint("temperature", tags, null, Instant.now());
    }

}
