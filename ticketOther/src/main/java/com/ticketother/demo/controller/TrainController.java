package com.ticketother.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketother.demo.dto.TrainSeatMessageDto;
import com.ticketother.demo.entity.Train;
import com.ticketother.demo.service.TrainService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    /**
     * 添加车票信息
     * @param data
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST )
    public boolean addTrainMessage(@RequestBody String data) throws ParseException {
        System.out.print(data);
        return trainService.addTrainMessage(data);
    }

    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getTrainSeats" )
    public List<TrainSeatMessageDto> getTrainSeats(@RequestParam("trainId") Long trainId,@RequestParam("from") String from,@RequestParam("arrive") String arrive){
       System.out.println("获取座位信息：" + trainId + " ;" + from + " :" + arrive);
        return trainService.getTrainSeats(trainId,from,arrive);
    }

    /**
     * 根据火车编号搜索
     * @param trainCard
     * @return
     */
    @GetMapping(value = "/getTrainMessage")
    public Train selectTrain(@RequestParam("trainCard") String trainCard){
        return trainService.selectTrain(trainCard);
    }

    /**
     * 更具火车id查找
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getSelectById")
    public Train selectTrainById(@RequestParam("trainId") Long trainId){
        return trainService.selectTrainById(trainId);
    }

    /**
     * 获取所有的火车信息
     * @return
     */
    @GetMapping("/getAllTicker")
    public String  getAllTrain(@RequestParam("aoData") String aoData){
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
        List<Train>  allTrain = trainService.getAllTrain(iDisplayStart,iDisplayLength);
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);
        getObj.put("iTotalRecords", allTrain.size());//实际的行数
        getObj.put("iTotalDisplayRecords", allTrain.size()); //显示的行数，和上面一致
        getObj.put("aaData", allTrain);
        return getObj.toString();
    }

    /**
     * 修改火车信息
     * @param trainStr
     * @return
     */
    @PostMapping("/updateTrain")
    public boolean updateTrain(@Param("trainMessage") String trainStr){

        Train train = JSONObject.parseObject(trainStr,Train.class);
        System.out.print(trainStr);
        return trainService.updateTrain(train);
    }



}
