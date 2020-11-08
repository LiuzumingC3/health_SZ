package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Author xlm <tobexlm@163.com>
 * @Date 2020/11/6 19:14
 */
@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 分页查询(页面初始化显示)
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Menu> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Menu> page = menuDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Menu>(page.getTotal(), page.getResult());
    }

    /**
     * 新增菜单
     *
     * @param menu
     */
    @Override
    @Transactional
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    /**
     * 编辑弹窗回显菜单信息
     *
     * @param id
     * @return
     */
    @Override
    public Menu findById(int id) {
        return menuDao.findById(id);
    }

    /**
     * 编辑提交
     *
     * @param menu
     */
    @Override
    @Transactional
    public void update(Menu menu) {
        menuDao.update(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(int id) throws MyException {
        // 判断菜单是否被角色所使用
        int cnt = menuDao.findCountByRole(id);
        if (cnt > 0) {
            throw new MyException("该菜单下有角色正在使用,不能删除!!");
        }
        // 没有使用
        // 删除
        menuDao.deleteById(id);
    }
}
