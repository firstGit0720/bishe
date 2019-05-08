package com.redis.demo.redisUtil;

import com.alibaba.fastjson.JSONObject;
import com.redis.demo.dto.UserDto;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Component
public class Redis {

    //获取一个jedis连接
    private Jedis jedis = new Jedis("localhost");
    private ThreadLocal<String> finalValue = new ThreadLocal<String>();

/*    *//**
     * 获取锁
     *//*
    public String lock(){
        String uuid = UUID.randomUUID().toString();
        String ret = jedis.set("buyTicket",uuid,"NX","PX",100000);
        if("OK".equals(ret)){
            //将uuid保存
            finalValue.set(uuid);
            return uuid;
        }
        return null;
    }

    *//**
     * 解锁
     * @param uuid
     * @return
     *//*
    public boolean unLock(String uuid){
        Long check = null;
        //判断uuid是否相同
        if (uuid.equals(this.finalValue)) {
            //解锁
            check = jedis.del("buyTicket");
        }

        return check != null ? true : false;
    }*/

    /**
     * 关闭资源
     */
    public void close(){
        jedis.close();
    }


    /**
     * 将用户信息添加到缓存
     */
    public boolean addUserToCache(UserDto user){
        //将用户信息转换为字符串
        String userMessage = JSONObject.toJSONString(user);
        //将其存到缓冲
        String check = jedis.set(user.getUserName(),userMessage,"NX","PX",1000*60*30);
        if("OK".equals(check)){
            //将uuid保存
            return true;
        }
        return false;
    }

    /**
     * 冲缓冲中获取用户信息
     * @return
     */
    public UserDto getUserFromCache(String username){
        String userMessage = jedis.get(username);
        return JSONObject.parseObject(userMessage,UserDto.class);
    }

    /**
     * 清除缓存中的信息，用户退出登录时
     * @return
     */
    public boolean cleanUserFromCache(String username){
        return jedis.del(username) > 0 ? true : false;
    }

}
