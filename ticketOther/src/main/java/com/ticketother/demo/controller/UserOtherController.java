package com.ticketother.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketother.demo.dto.BackTicketDto;
import com.ticketother.demo.dto.IndentMessageDto;
import com.ticketother.demo.dto.ShowTicketHestoryDto;
import com.ticketother.demo.entity.IndentMessage;
import com.ticketother.demo.entity.Train;
import com.ticketother.demo.entity.UserTickerMessage;
import com.ticketother.demo.service.UserOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userother")
public class UserOtherController {

    @Autowired
    private UserOtherService userOtherService;



    /**
     * 获取已完成的行程
     * @param userId
     * @return
     */
    @GetMapping("/getUserHistroy")
    public String showUserHistroy(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData){

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
        List<IndentMessageDto> userHistroy = userOtherService.showUserHistroy(userId,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", userHistroy.size());//实际的行数
        getObj.put("iTotalDisplayRecords", userHistroy.size()); //显示的行数，和上面一致
        getObj.put("aaData", userHistroy);
        return getObj.toString();
    }

    /**
     * 显示未来的行程
     * @param userId
     * @return
     */
    @GetMapping("/getUserFuture")
    public String showFuture(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData){
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
        List<IndentMessageDto> userFuture = userOtherService.showFuture(userId,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", userFuture.size());//实际的行数
        getObj.put("iTotalDisplayRecords", userFuture.size()); //显示的行数，和上面一致
        getObj.put("aaData", userFuture);
        return getObj.toString();
    }

    @GetMapping("/selectTrainId")
    public Long selectTrainId(@RequestParam("trainCard") String trainCard){
        return userOtherService.selectTrainId(trainCard);
    }

    /**
     * 改签
     * @param id
     * @return
     */
    @PostMapping("/updateTicket")
    public boolean updateTicket(@RequestParam("id")long id){
        return userOtherService.updateTicketStatus(id);
    }

    /**
     * 退票
     * @param id
     * @return
     */
    @PostMapping("/exitTicket")
    public boolean exitTicket(@RequestParam("id")long id){
        return userOtherService.exitTicket(id);
    }

    /**
     * 获取订单信息
     * @param id
     * @return
     */
    @GetMapping("/getMessage")
    public IndentMessage getMessage(@RequestParam("id")long id){
        return userOtherService.getMessage(id);
    }

    /**
     *
     * @param
     * @param aoData
     * @return
     */
    @GetMapping("/allIndent")
    public String getAllIndent(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData){

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
        List<IndentMessageDto> userHistroy = userOtherService.allIndentMessage(startTime,endTime,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", userHistroy.size());//实际的行数
        getObj.put("iTotalDisplayRecords", iDisplayLength); //显示的行数，和上面一致
        getObj.put("aaData", userHistroy);
        return getObj.toString();
    }

    /**
     *
     * @param
     * @param aoData
     * @return
     */
    @GetMapping("/allBackTicket")
    public String getAllBackTicket(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData){

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
        List<BackTicketDto> userHistroy = userOtherService.lists(startTime,endTime,iDisplayStart,100);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", userHistroy.size());//实际的行数
        getObj.put("iTotalDisplayRecords", userHistroy.size()); //显示的行数，和上面一致
        getObj.put("aaData", userHistroy);
        return getObj.toString();
    }

}
