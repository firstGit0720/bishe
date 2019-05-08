package com.other.demo.service.impl;

import com.other.demo.dao.UserDao;
import com.other.demo.dao.UserTypeDao;
import com.other.demo.dto.UserDto;
import com.other.demo.dto.UserTypeDto;
import com.other.demo.dto.UsersDto;
import com.other.demo.entity.User;
import com.other.demo.entity.UserType;
import com.other.demo.service.UserTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private static final Logger logger = Logger.getLogger(UserTypeServiceImpl.class);

    @Autowired
    private UserTypeDao userTypeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean updateUserType(UserType userType) {
        //设置修改的最新时间，创建时间不变
        return userTypeDao.updateUserType(userType);
    }

    @Override
    public List<UsersDto> allUsers() {
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat  sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<UserDto> userDtos = userTypeDao.allUsers();
        List<UsersDto> usersDtos = new ArrayList<>();
        for (UserDto userDto : userDtos){
            UsersDto usersDto = new UsersDto();
            usersDto.setId(userDto.getId());
            usersDto.setUserName(userDto.getUserName());
            usersDto.setUserPname(userDto.getUserPname());
            usersDto.setUserAge(userDto.getUserAge());
            usersDto.setUserSex(userDto.getUserSex() == 0 ? "男" : "女");
            usersDto.setUserEmail(userDto.getUserEmail());
            usersDto.setUserBirthday(sdf.format(userDto.getUserBirthday()));
            usersDto.setCreateTime(sdf1.format(userDto.getUserCreateTime()));
            switch(userDto.getUserType()){
                case 0 : usersDto.setUserType("高级管理员");break;
                case 1 : usersDto.setUserType("普通管理员");break;
                case 2 : usersDto.setUserType("普通用户");break;
            }
            usersDto.setUserAddress(userDto.getUserAddress());
            usersDto.setUserCard(userDto.getUserCard());
            usersDto.setUserPhone(userDto.getUserPhone());
            usersDtos.add(usersDto);
        }
        return usersDtos;
    }

    @Override
    public UserTypeDto getUserType(Long userId) {
        UserType userType = userTypeDao.getUserType(userId);
        User user = userDao.getUserById(userId);
        UserTypeDto userTypeDto = new UserTypeDto();
//        封装数据
        userTypeDto.setId(userType.getId());
        userTypeDto.setUserName(user.getUserName());
        userTypeDto.setUserPname(user.getUserPname());
        userTypeDto.setUserType(userType.getUserType());
        return userTypeDto;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public boolean deleteUserType(Long userId) {
        boolean delUser = false,delPwd = false,delType = false;
        //先删除用户的信息表
        delUser = userDao.deleteUser(userId);
        if (delUser){
            delPwd = userDao.deletePassword(userId);
            delType = userTypeDao.deleteUserType(userId);
        }
        return delUser && delPwd && delType;
    }
}
