package com.other.demo.service;

import com.other.demo.dto.UserTypeDto;
import com.other.demo.dto.UsersDto;
import com.other.demo.entity.UserType;

import java.util.List;

/**
 * 用户权限的service层
 */

public interface UserTypeService {

    /**
     * 修稿用户权限
     * @param userType
     * @return
     */
    public boolean updateUserType(long id, int userType);

    /**
     * 所有会员信息
     * @return
     */
    public List<UsersDto> allUsers(String userpname,int start,int end);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    public UserTypeDto getUserType(Long userId);

    /**
     * 删除用户权限
     * @param userId
     * @return
     */
    public boolean deleteUserType(Long userId);

}
