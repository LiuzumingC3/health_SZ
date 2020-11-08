package com.itheima.health.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserfindService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userfind")
public class UserfindController {

    @Reference
    UserfindService userfindService;

    @RequestMapping("/userfindAllpage")
    public Result userfindAllpage(@RequestBody QueryPageBean queryPageBean){
        PageResult<User> userfind = userfindService.userfind(queryPageBean);

        return new Result(true,"数据查询成功",userfind);
    }

    @RequestMapping("/useradd")
    public Result useradd(Integer[] checkitemIds,@RequestBody User user){
            userfindService.useradd(checkitemIds,user);

        //去执行新增角色
        //user里面没有id值，需要去查询一下，根据

        return new Result(true,"新增成功");
    }
    @GetMapping("/findById")
    public Result findById(int id){
        // 调用服务查询
        User user = userfindService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,user);
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Role> list = userfindService.findAll();
        return new Result(true,"查询角色数据成功",list);
    }

    @RequestMapping("/userupdate")
    public Result userupdate(Integer [] checkitemIds,@RequestBody User user){
        userfindService.userupdate(checkitemIds,user);
        return new Result(true,"修改数据成功");
    }

    @RequestMapping("/userdeleteById")
    public Result userdeleteById(Integer [] checkitemIds,int id){
        userfindService.userdeleteById(checkitemIds,id);
        return new Result(true,"删除成功");
    }

    @RequestMapping("/finduserroleByid")
    public Result finduserroleByid(int id){
        List<Role> roles = userfindService.finduserroleByid(id);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Role role : roles) {
            Integer id1 = role.getId();
            arrayList.add(id1);
        }
        return new Result(true,"查询角色列表成功",arrayList);
    }

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findRole")
    public Result findRole(){

        List<Role> role = userfindService.findRole();
        return new Result(true,"数据查询成功",role);
    }

}
