package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;

import java.util.List;

public interface UserfindService {
    //分页查询
    PageResult<User> userfind(QueryPageBean queryPageBean);

    //新增用户
    void useradd(Integer [] checkitemIds,User user);

    //根据id查询
    User findById(int id);

    /**
     * 查询所有的用户
     * @return
     */
    List<Role> findAll();
    /*修改用户数据*/
    void userupdate(Integer [] checkitemIds,User user);

    void userdeleteById(Integer [] checkitemIds,int id);

    List<Role> finduserroleByid(int id);

    List<Role> findRole();

}
