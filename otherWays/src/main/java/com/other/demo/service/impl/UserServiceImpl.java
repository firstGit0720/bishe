package com.other.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.other.demo.dao.UserDao;
import com.other.demo.dao.UserTypeDao;
import com.other.demo.dto.RegisterDto;
import com.other.demo.dto.UserDto;
import com.other.demo.entity.User;
import com.other.demo.entity.UserType;
import com.other.demo.feign.RedisServiceFeign;
import com.other.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserTypeDao userTypeDao;
    @Autowired
    private RedisServiceFeign redisServiceFeign;

    /**
     * 用户登录校验
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public UserDto login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //登陆之前先获取一下缓存中的数据
        UserDto cache = redisServiceFeign.getUserFromCache(username);
        if (cache != null){
            boolean clear = redisServiceFeign.clearUserMessage(username);
        }
        //根据用户名查找用户id
        Long userId = userDao.getUserId(username);
        //更具用户id查找用户的加密后的密码
        String pwd = userDao.getPassword(userId);
        User user = userDao.selectByUsername(username);
        short userType = userTypeDao.selectUserType(userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setUserType(userType);
        //将信息添加到缓存
        boolean addCheck = redisServiceFeign.addUserToCache(JSONObject.toJSONString(userDto));
        if(this.checkpassword(password,pwd) && addCheck){
            return userDto;
        }

        return null;
    }

    /**
     * 用户注册
     * @param userDto 用户传输对象
     * @return  是否注册成功
     */
    @Override
    @Transactional
    public boolean registerUser(RegisterDto userDto) {
        boolean registerCheck = false, insertPwd = false;
        //创建user对象并进行数据封装
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setUserAge(getAge(userDto.getUserBirthday()));
        //注册用户基本信息
        registerCheck = userDao.registerUser(user);
        //添加成功
        if (registerCheck){
            //成功后可以得到，用户的id
            long userId = userDao.getUserId(userDto.getUserName());
            //获取base64加密后的密码
            String pwdBase64 = userDto.getPassword();
            //加密密码
            String password = null;
            try {
                password = this.EncoderByMd5(pwdBase64);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            insertPwd = userDao.insertPassword(userId,password);
            if(insertPwd){
                //添加用户的权限
                //创建userType对象
                UserType userType = new UserType();
                userType.setUserId(userId);
                userType.setCreateTime(new Date());
                userType.setUpdateTime(new Date());
                userType.setUserType((short)2);//默认用户权限为2，即普通用户
                return  userTypeDao.addUserType(userType);
            }
        }
        return false;
    }

    /**
     * 通过用户名查找永远忽信息
     * @param username 用户名
     * @return
     */
    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 修改密码
     * @param userName
     * @param newPassword
     * @return
     */
    @Override
    public boolean updatePassword(String userName, String oldPassword, String newPassword) {
        //根据用户名查找用户id
        User user = userDao.selectByUsername(userName);
        //获取旧密码
        String oldPwd = userDao.getPassword(user.getId());
        boolean isCheck = false;
        //加密新密码和旧密码
        String pwd = "";
        try {
            pwd = this.EncoderByMd5(newPassword);
            if (this.EncoderByMd5(oldPassword).equals(oldPwd)){ //加密旧密码和原来的密码进行判断，验证是否一致，一直则修改，否则修改失败
                isCheck = userDao.updateUserPassword(user.getId(),pwd);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUserMessage(User user) {
        return userDao.updateUserMessage(user);
    }

    /**
     * 获取用户id
     * @param username
     * @return
     */
    @Override
    public Long getUserId(String username) {
        return userDao.getUserId(username);
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUser(Long userId) {
        boolean checkType = false,checkPassword = false,checkUser = false;
        //删除用户级别
        checkType = userTypeDao.deleteUserType(userId);
        //删除用户密码
        checkPassword  = userDao.deletePassword(userId);
        //删除用户信息
        checkUser = userDao.deleteUser(userId);
        return checkPassword && checkType && checkUser;
    }

    /**
     * 计算年龄
     * @param date
     * @return
     */
    public int getAge(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        int birtgday = Integer.parseInt(sdf.format(date).substring(0,4));
        int now = Integer.parseInt(sdf.format(nowDate).substring(0,4));
        return (now - birtgday);
    }

    /**
     * md5加密算法
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**判断用户密码是否正确
     *newpasswd 用户输入的密码
     *oldpasswd 正确密码*/
    public boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }

}
