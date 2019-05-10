package com.other.demo.dao;

import com.other.demo.dto.UserDto;
import com.other.demo.entity.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权限的设置及修改
 */
@Mapper
public interface UserTypeDao {
    /**
     * 添加用户权限
     * @param userType  用户权限的对象
     * @return 是否添加成功
     */
    public boolean addUserType(@Param("userType") UserType userType);

    /**
     * 修改用户权限
     * @param userType
     * @return
     */
    public boolean updateUserType(@Param("id") long id,@Param("userType")int userType);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    public Short selectUserType(@Param("userId") Long userId);

    /**
     * 搜索所有的会员
     * @return
     */
    public List<UserDto> allUsers(@Param("userpname") String userpname ,@Param("start") int start,@Param("end") int end);

    /**
     *
     * @param userId
     * @return
     */
    public boolean deleteUserType(@Param("userId")Long userId);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    public UserType getUserType(@Param("userId") Long userId);

}
