package com.rongyaoserver.jichu.opera;

import com.rongyaoserver.jichu.util.Validity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerOpera {
    
    private static Map<String,Runnable> plaReq = new HashMap<>();
    
    public static Runnable getPlaReq(String playerName){
        return Validity.isEmpty(playerName) ? null : plaReq.get(playerName);
    }

    public static void setPlaReq(String key, Runnable value){
        if (!Validity.isEmpty(key) && null != value && null == plaReq.get(key)) plaReq.put(key,value);
    }

    public static void remPlaReq(String key){
        if (!Validity.isEmpty(key) && null != plaReq.get(key)) plaReq.remove(key);
    }
    
}
