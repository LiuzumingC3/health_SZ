package com.itheima.health.controller;

/**
 * @Author xlm <tobexlm@163.com>
 * @Date 2020/11/6 16:01
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    /**
     * 分页查询(页面初始化显示)
     *
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用服务分页查询
        PageResult<Menu> pageResult = menuService.findPage(queryPageBean);
        return new Result(true, "查询菜单成功", pageResult);
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Menu menu) {
        // 调用服务添加
        menuService.add(menu);
        return new Result(true, "新增菜单成功");
    }

    /**
     * 编辑弹窗回显菜单信息
     *
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(int id) {
        Menu menu = menuService.findById(id);
        return new Result(true, "查询菜单信息成功", menu);
    }

    /**
     * 编辑提交
     *
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Menu menu) {
        // 调用服务更新
        menuService.update(menu);
        return new Result(true, "更新成功");
    }

    /**
     * 删除菜单
     *
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(int id) {
        menuService.deleteById(id);
        return new Result(true, "删除菜单成功");
    }
}

