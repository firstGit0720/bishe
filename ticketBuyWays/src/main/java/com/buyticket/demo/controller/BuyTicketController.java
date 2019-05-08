package com.buyticket.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buyticket.demo.dto.BuyTicketDto;
import com.buyticket.demo.dto.TicketShowDto;
import com.buyticket.demo.dto.TrainDto;
import com.buyticket.demo.entity.TrainArrive;
import com.buyticket.demo.service.BuyTivketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/buyticket")
public class BuyTicketController {

    @Autowired
    private BuyTivketService buyTivketService;
    /**
     * 在不登录的情况下也可以查询车票信息，直接返回车票信息
     * @param from
     * @param arrive
     * @return
     */
    @GetMapping(value = "/selectTrainTickets")
    public String selectTrainTicket(@RequestParam("from") String from , @RequestParam("arrive") String arrive, @RequestParam("date") String date, @RequestParam("aoData") String aoData) throws UnsupportedEncodingException {
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
        List<TrainDto>  trains = buyTivketService.allTrain(from, arrive,date,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", trains.size());//实际的行数
        getObj.put("iTotalDisplayRecords", trains.size()); //显示的行数，和上面一致
        getObj.put("aaData", trains);
        return getObj.toString();

    }

    /**
     * 购买火车票
     * @param buyTicketDao
     * @return
     */
    @PostMapping("/buyTicket")
    public boolean buyTicket(@RequestParam("buyTicketMessage") String buyTicketDao) throws UnsupportedEncodingException {
        System.out.println(buyTicketDao);
        BuyTicketDto buyTicketDto = JSONObject.parseObject(buyTicketDao,BuyTicketDto.class);
        return buyTivketService.buyTicket(buyTicketDto);
    }

    /**
     * 根据火车编号查询火车信息
     * @param trainCard
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getTrainMessage")
    public String allSpace(@RequestParam("trainCard") String trainCard,@RequestParam("aoData") String aoData){
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
        List<TicketShowDto>  allTrainSpaces = buyTivketService.allTrainSpaces(trainCard,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", allTrainSpaces.size());//实际的行数
        getObj.put("iTotalDisplayRecords", allTrainSpaces.size()); //显示的行数，和上面一致
        getObj.put("aaData", allTrainSpaces);
        return getObj.toString();
    }

    /**
     * 根据火车id查询所有的站点信息
     * @param trainId
     * @param aoData
     * @return
     */
    @GetMapping("/getArriveByTrainId")
    public String getArriveMessage(@RequestParam("trainId") Long trainId,@RequestParam("aoData") String aoData){
       /* JSONObject jsonObject = JSON.parseObject(message);
        Long trainId = jsonObject.getLong("trainId");
        String aoData = jsonObject.getString("aoData");*/
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
        List<TicketShowDto>  allTrainSpaces = buyTivketService.allTrainSpaces(trainId,iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", allTrainSpaces.size());//实际的行数
        getObj.put("iTotalDisplayRecords", allTrainSpaces.size()); //显示的行数，和上面一致
        getObj.put("aaData", allTrainSpaces);
        return getObj.toString();
    }

    /**
     * 获取所有的中间站点信息
     * @param trainId
     * @return
     */
    @GetMapping("/allTrainArrive")
    public List<TrainArrive> allTrainArrive(@RequestParam("trainId") Long trainId){
        return buyTivketService.allTrainArrives(trainId);
    }



}
