package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;

import java.util.List;

public interface RoleService {
    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 查所有
     * @return
     */
    List<Role> findAll();


    /**
     * 添加角色
     * @param role
     */
    void add(Role role, Integer[] permissionIds, Integer[] menuIds);
    //void add(Role role, Integer[] permissionIds);

    /**
     * 通过id删除角色
     * @param id
     */
    void deleteById(int id);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role,Integer[] permissionIds);


    void addRolePermission(Role role, Integer[] permissionIds);
}
