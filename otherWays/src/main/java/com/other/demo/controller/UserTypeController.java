package com.other.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.other.demo.dto.UserDto;
import com.other.demo.dto.UserTypeDto;
import com.other.demo.dto.UsersDto;
import com.other.demo.entity.UserType;
import com.other.demo.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * 修改会员等级
     * @param data
     * @return
     */
    @PostMapping(value = "/updateUserType" ,consumes = {CONTENT_TYPE})
    public boolean updateUserType(@RequestBody String data){
        System.out.print(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        return userTypeService.updateUserType(jsonObject.getLong("id"),jsonObject.getShort("userType"));
    }

    /**
     * 删除用户
     * @param data
     * @return
     */
    @PostMapping(value = "/deleteUser" , consumes = {CONTENT_TYPE})
    public boolean deleteAdmin(@RequestBody String data){
        System.out.print(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Long id = jsonObject.getLong("id");
        return userTypeService.deleteUserType(id);
    }


    /**
     * 获取用户等级
     * @param userId
     * @return
     */
    @GetMapping(value = "getUserType" , consumes = {CONTENT_TYPE})
    public UserTypeDto getUserTypeMessage(@RequestParam("userId") Long userId){
        return userTypeService.getUserType(userId);
    }

    /**
     * 返回所有会员信息
     * @return
     */
    @RequestMapping(value = "/allusers" , method = RequestMethod.GET , consumes = {CONTENT_TYPE})
    public String allUser(@RequestParam("aoData") String aoData ,@RequestParam("userpname") String  userpname){
        JSONArray jsonarray = JSONArray.parseArray(aoData);
        String sEcho = null;//记录操作的次数  
        int iDisplayStart=0;// 起始索引    
        int iDisplayLength=0;// 每页显示的行数    
        //这里获取从前台传递过来的参数，从而确保是否分页、是否进行查询、是否排序等
        for (int i = 0; i < jsonarray.size() ; i++){
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if(obj.get("name").equals("sEcho")){
                sEcho = obj.get("value").toString();
            }else if(obj.get("name").equals("iDisplayStart")){
                iDisplayStart = obj.getInteger("value");
            }else if(obj.get("name").equals("iDisplayLength")){
                iDisplayLength = obj.getInteger("value");
            }
        }
        List<UsersDto> allusers  = userTypeService.allUsers(userpname,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", allusers.size());//实际的行数
        getObj.put("iTotalDisplayRecords", allusers.size()); //显示的行数，和上面一致
        getObj.put("aaData", allusers);
        return getObj.toString();
    };



}
