package com.other.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.other.demo.dto.UserDto;
import com.other.demo.feign.RedisServiceFeign;
import com.other.demo.service.UserService;
import com.other.demo.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 登录的controller
 */
@RestController
@RequestMapping("/otherWays")
@CrossOrigin
public class LoginController {
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    @Autowired
    private UserService userService;
    @Autowired
    private RedisServiceFeign redisServiceFeign;
    @Autowired
    private ImageUtil imageUtil;

    /**
     * 登录验证
     * @return
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST , consumes = {CONTENT_TYPE})
    public String  userLogin(@RequestBody String data)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        /*@RequestParam("username") String username,@RequestParam("password") String password*/
        System.out.print(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        UserDto userDto = userService.login(username,password);
        return JSONObject.toJSONString(userDto);
    }

    /**
     *从redis中获取数据
     * @param username
     * @return
     */
    @RequestMapping(value = "/getUser" ,method = RequestMethod.GET, consumes = {CONTENT_TYPE})
    public UserDto getUser(@RequestParam("username") String username){
//        JSONObject jsonObject = JSONObject.parseObject(username);
        return redisServiceFeign.getUserFromCache(username);
    }

    /**
     * 修改密码
     * @param username  用户名
     * @param newPassword 新密码
     * @return  是否成功
     */
    @RequestMapping(value = "/updatePassword" , method = RequestMethod.POST ,consumes = {CONTENT_TYPE})
    public boolean updatePassword(@RequestParam("username") String username, @RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
       return userService.updatePassword(username,oldPassword, newPassword);
    }

    /**
     * 获取用户id
     * @param username
     * @return
     */
    @RequestMapping(value = "/getUserId",method = RequestMethod.GET)
    public Long getUserId(@RequestParam("username") String username){
        return userService.getUserId(username);
    }

    /**
     * 清空登录信息
     * @param username
     * @return
     */
    @PostMapping(value = "/clearUserMessage",consumes = {CONTENT_TYPE})
    public boolean clearUserMessage(@RequestBody String username){
        JSONObject jsonObject = JSONObject.parseObject(username);
        return redisServiceFeign.clearUserMessage(jsonObject.getString("username"));
    }

    /**
     * 删除会员
     * @param userid
     * @return
     */
    @PostMapping(value = "/deleteuser",consumes = {CONTENT_TYPE})
    public boolean deleteUser(@RequestParam("userId") Long userid){
        return userService.deleteUser(userid);
    }

    /**
     * 上传图片
     * @return
     */
    @PostMapping(value = "/uploadImage" , consumes = {CONTENT_TYPE})
    public boolean uploadImage(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        String baseStr = jsonObject.getString("baseStr");
        String username = jsonObject.getString("username");
        try {
            return imageUtil.uploadImage(baseStr, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("/getiamge")
    public void getImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username) throws IOException {
        imageUtil.getImage(username,request,response);

    }

}
