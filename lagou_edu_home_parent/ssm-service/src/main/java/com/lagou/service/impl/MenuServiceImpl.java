package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.invoke.CallSite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> list = menuMapper.findSubMenuListByPid(pid);
        return list;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> list = menuMapper.findAllMenu();
        return list;
    }

    @Override
    public Menu findMenuById(int id) {
        return  menuMapper.findMenuById(id);
    }


}
