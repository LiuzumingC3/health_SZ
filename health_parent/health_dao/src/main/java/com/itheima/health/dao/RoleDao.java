package com.itheima.health.dao;

import com.github.pagehelper.Page;

import com.itheima.health.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {


    /**
     * 条件查询
     * @param queryString
     * @return
     */
    Page<Role> findByCondition(String queryString);

    /**
     * 查所有
     * @return
     */
    List<Role> findAll();

    /**
     * 通过角色id查询选中的角色id集合
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 通过id删除角色
     * @param id
     */
    void deleteById(int id);


    void deleteByRoleId(int id);


    void deleteMenuAndRoleByRoleId(int id);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role);

    /**
     *  添加角色与权限的关系
     * @param roleId
     * @param permissionId
     */
    void addRolePermission(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    /**
     * 新增角色
     * @param role
     */
    void add(Role role);

    /**
     *  根据名字查角色
     * @param name
     * @return
     */
    Role findByName(String name);

    void addRoleMenu(Integer roleId, Integer menuId);


    int findUserAndRoleByRoleId(int id);

    /**
     * 设置角色和权限中间表的关系
     * @param map
     */
    void setRoleAndPermission(Map<String, Object> map);

    /**
     * 设置角色和菜单中间表的关系
     * @param map
     */
    void setRoleAndMenu(Map<String, Object> map);
}
