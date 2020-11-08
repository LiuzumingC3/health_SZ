package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.Result;
import com.itheima.health.service.SexAndAgeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class SexAndAgeController {

    @Reference
    private SexAndAgeService sexAndAgeService;

    @GetMapping("/sex")
    public Result sex(){
        List<Map<String,Object>> genderCount = sexAndAgeService.findMemberCount();
       List<String> gender = new ArrayList<>();
       if (genderCount !=null){
           for (Map<String, Object> map : genderCount) {
               gender.add((String)map.get("name"));
           }
       }
        Map<String,Object> resultMap = new HashMap<String,Object>(2);
        resultMap.put("gender", gender);
        resultMap.put("genderCount", genderCount);
        return new Result(true,"查询会员性别占比成功",resultMap);

    }
    @GetMapping("/age")
    public Result age(){
        //调用服务层查询所有年龄的集合
        List<Map<String,Object>> ageCount = sexAndAgeService.findMemberAgeCount();

        //年龄名称集合
        List<String> age = new ArrayList<>();

        //抽取各个年龄
        if (null != ageCount){
            for (Map<String, Object> map : ageCount) {
                //获取各个年龄展示在页面
                age.add((String) map.get("name"));
            }
        }

        //封装返回的结果
        Map<String,Object> resultMap = new HashMap<String, Object>(2);
        resultMap.put("age",age);
        resultMap.put("ageCount",ageCount);

        return new Result(true,"查询所有年龄占比成功",resultMap);
    }
}
