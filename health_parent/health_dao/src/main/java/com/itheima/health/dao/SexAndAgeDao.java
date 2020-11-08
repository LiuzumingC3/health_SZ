package com.itheima.health.dao;

import java.util.List;
import java.util.Map;

public interface SexAndAgeDao {


    List<Map<String, Object>> findMemberCount();

    List<Map<String, Object>> findMemberAgeCount();

}
