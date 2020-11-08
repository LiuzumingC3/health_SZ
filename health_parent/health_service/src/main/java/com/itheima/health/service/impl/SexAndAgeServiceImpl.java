package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.SexAndAgeDao;
import com.itheima.health.service.SexAndAgeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
@Service(interfaceClass =SexAndAgeService.class)
public class SexAndAgeServiceImpl implements SexAndAgeService {

    @Autowired
    private SexAndAgeDao sexAndAgeDao;



    @Override
    public List<Map<String, Object>> findMemberCount() {
        return sexAndAgeDao.findMemberCount();
    }

    @Override
    public List<Map<String, Object>> findMemberAgeCount() {
        return sexAndAgeDao.findMemberAgeCount();
    }
}
