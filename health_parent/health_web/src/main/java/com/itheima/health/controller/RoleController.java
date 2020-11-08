package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    /**
     * 新增角色
     * @param role 角色信息
     * @param permissionIds  权限信息
     * @return
     */
    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ROLE_ADD')")   //权限管理
    public Result add(@RequestBody Role role, Integer[] permissionIds , Integer[] menuIds){
    //public Result add(@RequestBody Role role, Integer[] permissionIds ){
        try {
            // 调用服务添加
            roleService.add(role, permissionIds, menuIds);
            //roleService.add(role, permissionIds);
            return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_ROLE_FAIL);
        }
    }

    /**
     * 通过id删除角色
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        try {
            // 调用业务删除
            roleService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_ROLE_FAIL);
        }
    }

    /**
     * 更新角色
     */
    @PostMapping("/update")
    public Result update(@RequestBody Role role,Integer[] permissionIds ){
        try {
            // 调用服务修改
            roleService.update(role,permissionIds);
            roleService.addRolePermission(role,permissionIds);
            return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_ROLE_FAIL);
        }
    }

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            // 调用服务分页查询
            PageResult<Role> pageResult = roleService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }


    /**
     * 通过id查询
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id){
        try {
            Role role = roleService.findById(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public Result findAll(){
        try {
            List<Role> list = roleService.findAll();
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }


}
