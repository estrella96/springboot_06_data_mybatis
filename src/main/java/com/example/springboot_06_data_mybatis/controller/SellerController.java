package com.example.springboot_06_data_mybatis.controller;

import com.example.springboot_06_data_mybatis.bean.Customer;
import com.example.springboot_06_data_mybatis.bean.Seller;
import com.example.springboot_06_data_mybatis.mapper.CustomerMapper;
import com.example.springboot_06_data_mybatis.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController//返回json数据 不返回页面
public class SellerController {

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    CustomerMapper customerMapper;

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

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id){
        return customerMapper.getCusById(id);
    }

}
