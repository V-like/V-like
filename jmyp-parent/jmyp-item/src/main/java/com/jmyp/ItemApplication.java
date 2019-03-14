package com.jmyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by on 2018/12/25.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ItemApplication {

    //入口
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }

}
