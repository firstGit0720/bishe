package com.other.demo.service;

import com.other.demo.dto.RegisterDto;
import com.other.demo.dto.UserDto;
import com.other.demo.entity.User;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户注册
 */
public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public UserDto login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 注册用户的信息
     * @param userDto 用户传输对象
     * @return
     */
    public boolean registerUser(RegisterDto userDto);

    /**
     * 通过用户名查找用户信息，用于注册时用户名的校验
     * @param username 用户名
     * @return
     */
    public User selectByUsername(String username);

    /**
     *修改密码
     * @param userName
     * @param newPassword
     * @param oldPassword
     * @return
     */
    public boolean updatePassword(String userName,String oldPassword,String newPassword);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUserMessage(User user);

    /**
     * 获取用户id
     * @param username
     * @return
     */
    public Long getUserId(String username);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public boolean deleteUser(Long userId);

}
