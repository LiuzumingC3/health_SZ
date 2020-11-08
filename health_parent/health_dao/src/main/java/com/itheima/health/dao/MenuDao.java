package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Menu;

public interface MenuDao {
    /**
     * 分页查询(页面初始化显示)
     *
     * @param queryString
     * @return
     */
    Page<Menu> findByCondition(String queryString);

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
     * 判断菜单是否被角色所使用
     *
     * @param id
     * @return
     */
    int findCountByRole(int id);

    /**
     * 删除菜单
     *
     * @param id
     */
    void deleteById(int id);
}
