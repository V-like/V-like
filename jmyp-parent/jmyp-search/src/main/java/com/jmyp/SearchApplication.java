package com.jmyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by on 2018/12/18.
 */
@SpringBootApplication
public class SearchApplication {

    //入口函数
    public static void main(String[] args) {
        //入口
        SpringApplication.run(SearchApplication.class,args);
    }

}
