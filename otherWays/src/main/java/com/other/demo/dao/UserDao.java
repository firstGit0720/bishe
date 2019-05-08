package com.other.demo.dao;

import com.other.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户登录和注册dao层
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户的id查找用户的密码
     * @param userId 用户的id
     * @return  用户的加密后的密码
     */
    public String getPassword(@Param("userId") Long userId);

    /**
     * 更具用户名获取用户id
     * @param username  用户的姓名
     * @return 用户的id
     */
    public Long getUserId(@Param("username") String username);

    /**
     * 注册用户的信息
     * @param user 用户的信息
     * @return 是否注册成功
     */
    public boolean registerUser(@Param("user") User user);

    /**
     * 添加密码
     * @param userId  用户id
     * @param password 密码
     * @return 是否添加成功
     */
    public boolean insertPassword(@Param("userId") long userId,@Param("password") String password);

    /**
     * 通过用户名查询该用户名是否存在，用于注册时的校验
     * @param username 用户名
     * @return 用户信息对象，为空为不存在，否则就是存在
     */
    public User selectByUsername(@Param("username") String username);

    /**
     * 获取user信息
     * @param userId
     * @return
     */
    public User getUserById(Long userId);

    /**
     * 修改密码
     * @param userId  用户id
     * @param newPassword  新密码
     * @return
     */
    public boolean updateUserPassword(@Param("userId") Long userId,@Param("newPassword") String newPassword);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUserMessage(@Param("user") User user);

    /**
     * 删除user
     * @param id
     * @return
     */
    public boolean deleteUser(@Param("id") Long id);

    /**
     * 删除密码
     * @param userId
     * @return
     */
    public boolean deletePassword(@Param("userId") Long userId);


}
