package com.example.springboot_06_data_mybatis.mapper;

import com.example.springboot_06_data_mybatis.bean.Customer;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface CustomerMapper {
    public Customer getCusById(Integer id);

    public void insertCus(Customer customer);
}
