package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.UserDao;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过用户名查询用户信息，包含角色及权限信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<Menu> getMenu(String username) {
        //获取一级菜单并放进
       List<Menu> menuList= userDao.getMenu(username);
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            //根据二级目录id获取二级菜单目录
            List<Menu> children= userDao.getMenu2(id);
            menu.setChildren(children);
        }


        //用户权限
        return menuList;
    }
}
