package org.sq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.sq.mapper.UserMapper;
import org.sq.service.IUserService;

@Service
public class UsersServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = "passwordUpdate")
    public int updatePassword(String password, String userName) {
        int i = userMapper.updatePassword(password, userName);
        return i;
    }
}
