package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.UserfindDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserfindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = UserfindService.class)
public class Userfindimpl implements UserfindService {

    @Autowired
    UserfindDao userfindDao;

    @Override
    public PageResult<User> userfind(QueryPageBean queryPageBean) {
       /* // 页码，与大小 pageHelper复杂的查询语句，使用手工分页方式，影响性能
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 判断是否有查询条件 如果有要实现模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //【注意】 非空要加!
            queryPageBean.setQueryString("%"+ queryPageBean.getQueryString()+"%");
        }
        //去执行查询
        List<User> userfind = userfindDao.userfind(queryPageBean.getQueryString());*/

       //先获取参数
        //查询条件
        // 判断是否有查询条件 如果有要实现模糊查询
        String queryString=null;
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //【注意】 非空要加!
            queryPageBean.setQueryString("%"+ queryPageBean.getQueryString()+"%");
            //查询条件
            queryString=queryPageBean.getQueryString();
        }
        //当前是第几页
        Integer currentPage = queryPageBean.getCurrentPage();
        //每页多少页
        Integer pageSize = queryPageBean.getPageSize();

        //差总页数
        Long userfindcount = userfindDao.userfindcount();


        //Page<User> page = userfindDao.userfind(queryPageBean.getQueryString(), currentPage, pageSize);
        PageResult<User> userPageResult = new PageResult<User>(userfindcount,userfindDao.userfind(queryString, (currentPage-1)*pageSize, pageSize) );

        return userPageResult;
    }


    public void useradd(Integer [] checkitemIds,User user) {
        //密码加密
        BCryptPasswordEncoder bpasswrod=new BCryptPasswordEncoder();
        String password = user.getPassword();
        String newpassword = bpasswrod.encode(password);
        user.setPassword(newpassword);
        userfindDao.useradd(user);

        for (Integer integer : checkitemIds) {
            userfindDao.useraddrole(integer,user.getId());
        }

    }

    @Override
    public User findById(int id) {
        return userfindDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return userfindDao.findAll();
    }


    @Transactional(rollbackFor=Exception.class)
    public void userupdate(Integer [] checkitemIds,User user) {
        //根据传过来的参数去修改
        //密码加密
        BCryptPasswordEncoder bpasswrod=new BCryptPasswordEncoder();
        String password = user.getPassword();
        String newpassword = bpasswrod.encode(password);
        user.setPassword(newpassword);
        userfindDao.userupdate(user);
        //去增加角色关系表
        for (Integer integer : checkitemIds) {
            userfindDao.useraddrole(integer,user.getId());
        }

    }

    @Transactional(rollbackFor=Exception.class)
    public void userdeleteById(Integer [] checkitemIds,int id) {

        //如果这个数据有外键关系就也去删除
        List<Role> role = userfindDao.finduserroleByid(id);
        if(role.size()>0){
            //就去执行删除关系表的数据
            userfindDao.deleteuserrole(id);
        }
        userfindDao.userdeleteById(id);
    }

    @Override
    public  List<Role>  finduserroleByid(int id) {
        List<Role> role = userfindDao.finduserroleByid(id);
        return role;
    }

    @Override
    public List<Role> findRole() {
        return userfindDao.findRole();
    }


}
