package com.example.springboot_06_data_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//mapper下面的所有包都会自动@Mapper注解 不需要每个类上单独加
@MapperScan(value = "com.example.springboot_06_data_mybatis.mapper")
@SpringBootApplication
public class Springboot06DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06DataMybatisApplication.class, args);
	}

}
