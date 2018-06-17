package com.springboot.demo.service;

import com.springboot.demo.dao.cityMapper;
import com.springboot.demo.model.city;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cityService {

    @Autowired
    cityMapper mapper;



    public city getCityInfoById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

}
