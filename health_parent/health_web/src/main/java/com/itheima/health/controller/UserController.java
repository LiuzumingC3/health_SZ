package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/31
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Reference
    UserService userService;

    /**
     * 获取登陆用户名
     * @return
     */
    @GetMapping("/getUsername")
    public Result getUsername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("登陆的用户名:" + user.getUsername());
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }


    /**
     * 动态菜单展示
     */

    @GetMapping("/getMenu")
    public Result getMenu(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取用户信息

        String username = user.getUsername();
        System.out.println("登陆的用户名:" + username);

        //根据用户名查询用户
        //返回一个菜单对象
        List<Menu> menuList =userService.getMenu(username);

        //返回一个结果集
        return new Result(true, MessageConstant.GET_MENU_SUCCESS,menuList);
    }

}
