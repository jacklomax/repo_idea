package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;


public interface UserService {

    public PageInfo findAllUserByPage(UserVo userVo);

    public User login(User user) throws Exception;

    public List<Role> findUserRelationRoleById(Integer id);

    public void userContextRole(UserVo userVo);

    public ResponseResult getUserPermissions(Integer userid);
}
