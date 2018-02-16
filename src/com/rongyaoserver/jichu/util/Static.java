package com.rongyaoserver.jichu.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Static {
    private static Map<String,UUID> playerMap = new HashMap<>();

    public static void checkConfig(JavaPlugin plugin){
        if(new File("/JiChu/config.xml").exists())
            return;
        FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static boolean checkArgs(String[] args , int number){
        return args.length == number ? true : false;
    }

    public static Player getPlayers(String playerName){
        return Validity.isEmpty(playerName) ? null : Bukkit.getPlayer(Static.playerMap.get(playerName));
    }

    public static void setPlayer(String key, UUID value){
        if (!Validity.isEmpty(key) && !Validity.isEmpty(value)) playerMap.put(key,value);
    }

    public static void removePlayer(String key){
        if (!Validity.isEmpty(key)) playerMap.remove(key);
    }
}
