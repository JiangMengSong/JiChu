package com.rongyaoserver.jichu;

import com.rongyaoserver.jichu.command.CommandHome;
import com.rongyaoserver.jichu.command.CommandSend;
import com.rongyaoserver.jichu.listener.ServerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class JiChu extends JavaPlugin {

    /** 服务器读取时加载 */
    public void onLoad() {
        if(!new File("/JiChu/config.xml").exists()){
            FileConfiguration config = getConfig();
            config.options().copyDefaults(true);
            saveConfig();
        }
    }

    /** 服务器加载时加载 */
    public void onEnable() {
        getLogger().info("服务器开始加载JiChu");
        // 注册服务器监听事件
        Bukkit.getServer().getPluginManager().registerEvents(new ServerListener(),this);
    }

    /**
     * 服务器接受命令时
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            switch (label){
                case "jia" :
                case "anjia" :
                case "huijia" :
                case "chaijia" :
                    return new CommandHome().onCommand(player,label,args);
                case "chuansong":
                    return new CommandSend().onCommand(player,label,args);
                case "tongyi":
                case "jujue":
                    return new CommandSend().onCommand(player,label,args);
            }

        }
        sender.sendMessage("只有玩家可以输入该命令");
        return true;
    }


    /**
     * 服务器关闭时加载
     */
    /*
    public void onDisable() {
        getLogger().info("服务器开始卸载JiChu");
    }*/
}