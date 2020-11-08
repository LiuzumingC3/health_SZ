package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserfindDao {
    Page<User> userfind(@Param("queryString") String queryString,
                        @Param("currentPage") int currentPage,
                        @Param("pageSize") int pageSize);
    Long userfindcount();

    void useradd(User user);

    User findById(int id);

    List<Role> findAll();

    void userupdate(User user);

    void userdeleteById(int id);

    List<Role> finduserroleByid(int id);

    List<Role> findRole();

    void useraddrole(@Param("checkitemIds") Integer checkitemIds, @Param("id") int id);

    void deleteuserrole(int id);
}
