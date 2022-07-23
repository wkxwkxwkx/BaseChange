package com.qcby.mapper;

import com.qcby.entity.User;

import java.util.List;

/**
 * @author wangkx
 */
public interface UserMapper {
    User findById(Integer id);
    int insert(User user);
    int delete(Integer id);
    int update(User user);
    List<User> findAll();
    int insertGetId(User user);
    User selectByUsername(String username);

}
