package com.example.springboot_06_data_mybatis.mapper;

import com.example.springboot_06_data_mybatis.bean.Seller;
import org.apache.ibatis.annotations.*;

//@Mapper
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
