package com.itheima.health.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;

import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 分页条件查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Role> page = roleDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Role> pageResult = new PageResult<Role>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }



    /**
     * 添加角色
     * @param role
     * @param permissionIds
     */
    @Override
    public void add(Role role, Integer[] permissionIds, Integer[] menuIds ) throws MyException {
    //public void add(Role role, Integer[] permissionIds) {
        if (null!=roleDao.findByName(role.getName())) {
            //该角色名存在，添加失败
            throw new MyException("该角色名已存在!!!");
        }
        // 先添加角色
        roleDao.add(role);
        // 获取角色id
        Integer roleId = role.getId();

        if(null != permissionIds) {
            // 添加角色与权限的关系

                this.addRolePermission(role,permissionIds);

        }
        //维护菜单角色中间表
        for (Integer menuId : menuIds) {
            roleDao.addRoleMenu(roleId,menuId);
        }
    }

    /**
     * 查所有
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }



    /**
     * 通过id删除角色
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        // 判断是否被用户使用了
        int count = roleDao.findUserAndRoleByRoleId(id);
        // 被使用了，则要报错
        if(count != 0) {
            throw new RuntimeException(MessageConstant.DELETE_ROLE_FAIL);
        }
        // 没使用，就可以删除
        roleDao.deleteById(id);
        roleDao.deleteByRoleId(id);
        roleDao.deleteMenuAndRoleByRoleId(id);
    }

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     */
    @Override
    public void update(Role role, Integer[] permissionIds) {
        roleDao.update(role);
    }

    @Override
    public void addRolePermission(Role role, Integer[] permissionIds) {
        for (Integer permissionId : permissionIds) {

            roleDao.addRolePermission(role.getId(),permissionId);
        }
    }

    /**
     * 设置角色和权限中间表的关系
     * @param roleId
     * @param permissionIds
     */
    private void setRoleAndPermission(Integer roleId, Integer[] permissionIds) {
        if (permissionIds != null && permissionIds.length > 0) {
            for (Integer groupId : permissionIds) {
                Map<String, Object> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("roleId", roleId);
                roleDao.setRoleAndPermission(map);
            }
        }
    }

    /**
     * 设置角色和菜单中间表的关系
     * @param roleId
     * @param menuIds
     */
    private void setRoleAndMenu(Integer roleId, Integer[] menuIds) {
        if (menuIds != null && menuIds.length > 0) {
            for (Integer groupId : menuIds) {
                Map<String, Object> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("roleId", roleId);
                roleDao.setRoleAndMenu(map);
            }
        }


    }
}