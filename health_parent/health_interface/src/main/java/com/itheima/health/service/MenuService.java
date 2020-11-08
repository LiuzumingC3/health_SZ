package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;

public interface MenuService {
    /**
     * 分页查询(页面初始化显示)
     *
     * @param queryPageBean
     * @return
     */
    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    /**
     * 新增菜单
     *
     * @param menu
     */
    void add(Menu menu);

    /**
     * 编辑弹窗回显菜单信息
     *
     * @param id
     * @return
     */
    Menu findById(int id);

    /**
     * 编辑提交
     *
     * @param menu
     */
    void update(Menu menu);

    /**
     * 删除菜单
     *
     * @param id
     */
    void deleteById(int id) throws MyException;
}
