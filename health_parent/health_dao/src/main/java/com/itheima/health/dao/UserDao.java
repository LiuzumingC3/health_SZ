package com.itheima.health.dao;

import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
public interface UserDao {
    /**
     * 通过用户名查询用户信息，包含角色及权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 获取一级菜单目录列表
     * @param username
     * @return
     */
    List<Menu> getMenu(String username);

    /**
     * 获取二级菜单目录列表
     * @param parentMenuId
     * @return
     */
    List<Menu> getMenu2(Integer parentMenuId);
}
