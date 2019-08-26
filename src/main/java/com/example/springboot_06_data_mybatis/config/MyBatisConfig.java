package com.example.springboot_06_data_mybatis.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

//import org.springframework.context.annotation.Configuration;


@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    //自动识别驼峰命名法和_连接 sellerId seller_id
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){

            @Override
            public void customize(Configuration configuration){
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
