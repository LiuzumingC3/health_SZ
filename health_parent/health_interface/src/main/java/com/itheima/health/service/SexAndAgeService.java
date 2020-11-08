package com.itheima.health.service;


import java.util.List;
import java.util.Map;

public interface SexAndAgeService {

    List<Map<String, Object>> findMemberCount();

    List<Map<String, Object>> findMemberAgeCount();
}
