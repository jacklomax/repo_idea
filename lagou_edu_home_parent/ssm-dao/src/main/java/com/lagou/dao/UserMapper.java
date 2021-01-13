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
}
