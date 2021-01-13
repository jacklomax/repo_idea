package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    public List<User> findAllUserByPage(UserVo userVo);

    public User login(User user);

    public List<Role> findUserRelationRoleById(Integer userId);

    public void deleteUserContextRole(Integer userId);

    public void userContextRole(User_Role_relation user_role_relation);

    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    public List<Menu> findSubMenuByPid(Integer pid);

    public List<Resource> findResourceByRoleId(List<Integer> ids);

    public List<Resource> findResourceByRoleId2(List<Integer> ids);

    public void test11();
    public void test21();
    public void test31();
    public void test51();
    public void test61();
    public void test71();
    public void test81();
    public void test91();
    public void test1();
    public void test2();
    public void test3();
    public void test5();
    public void test6();
    public void test7();
    public void test8();
    public void test9();
}
