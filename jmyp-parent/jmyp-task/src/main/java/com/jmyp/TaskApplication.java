package com.jmyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by on 2018/12/20.
 */
@SpringBootApplication
@EnableScheduling
public class TaskApplication {

    //入口函数
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class,args);
    }

}
