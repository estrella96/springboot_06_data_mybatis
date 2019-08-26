# Spring Boot data access mybatis

## 引入依赖

```xml
<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.0</version>
</dependency>
```

## 使用
- 步骤
    1 配置数据源相关属性（见05 druid配置）
    2 给数据库建表
    3 创建JavaBean
- 注解版 不需要配置
```java
//mapper
@Mapper
public interface SellerMapper {
    @Select("select * from seller where sellerid=#{sellerid}")
    public Seller getSellerByID(Integer sellerid);

    @Delete("delete from seller where sellerid=#{sellerid}")
    public int deleteSellerById(Integer sellerid);

    @Options(useGeneratedKeys =true, keyProperty = "sellerid" )
    @Insert("insert into seller(gender,job,name,password,telephone,username) values(#{gender},#{job},#{name},#{password},#{telephone},#{username})")
    public int insertSeller(Seller seller);

    @Update("update seller set name=#{name} where sellerid=#{sellerid}")
    public int updateSeller(Seller seller);
}
```
```java
//controller
@RestController//返回json数据 不返回页面
public class SellerController {

    @Autowired
    SellerMapper sellerMapper;

    @GetMapping("/seller/{id}")
    public Seller getSeller(@PathVariable("id") Integer id){
        return sellerMapper.getSellerByID(id);
    }

    @GetMapping("/seller")
    // ?拼接参数
    public Seller insertSeller(Seller seller){
        sellerMapper.insertSeller(seller);
        return seller;
    }
}

```
   - MapperScan 批量扫描mapper接口
```java
//mapper下面的所有包都会自动@Mapper注解 不需要每个类上单独加
//放在Springboot06DataMybatisApplication
@MapperScan(value = "com.example.springboot_06_data_mybatis.mapper")
```
   - 自定义配置 给容器添加ConfigurationCustomizer
```java
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
```
- 配置文件方式
    1 在全局配置文件中指明配置文件位置
```yaml
mybatis:
  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
```
   2 写mapper接口
```java
public interface CustomerMapper {
    public Customer getCusById(Integer id);

    public void insertCus(Customer customer);
}
```
   3 配置文件里写sql
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.springboot_06_data_mybatis.mapper.CustomerMapper">
<!--    public Customer getCusById(Integer id);

    public void insertCus(Customer customer);-->
    <select id="getCusById" resultType="com.example.springboot_06_data_mybatis.bean.Customer">
        select * from customer where customerid=#{id}
    </select>
    <insert id="insertCus">
        insert into customer(address,gender,name,password,reputation,telephone,username) values(#{address},#{gender},#{name},#{password},#{reputation},#{telephone},#{username})
    </insert>

</mapper>

        <!--sql映射文件-->
```
   4 mybatis配置
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

</configuration>

        <!--mybatis 全局配置文件-->
``` 
   5 写controller使用mapper
    

    