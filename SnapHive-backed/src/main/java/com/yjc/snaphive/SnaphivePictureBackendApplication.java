package com.yjc.snaphive;

import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@MapperScan("com.yjc.snaphive.mapper")
@SpringBootApplication(exclude = {ShardingSphereAutoConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
public class SnaphivePictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnaphivePictureBackendApplication.class, args);
    }

}
