package com.rongyaoserver.jichu;

import com.rongyaoserver.jichu.command.CommandHome;
import com.rongyaoserver.jichu.command.CommandParent;
import com.rongyaoserver.jichu.listener.ServerListener;
import com.rongyaoserver.jichu.time.ChuanTimerTask;
import com.rongyaoserver.jichu.util.Static;
import com.rongyaoserver.jichu.util.Validity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class JiChu extends JavaPlugin {

    private Player p1;
    private Player p2;

    private CommandParent commands;
    private TimerTask timerTask;

    /** 服务器读取时加载 */
    public void onLoad() {
        //getLogger().info("服务器开始读取JiChu");
    }

    /** 服务器加载时加载 */
    public void onEnable() {
        getLogger().info("服务器开始加载JiChu");
        Static.checkConfig(this);
        // 注册服务器监听事件
        Bukkit.getServer().getPluginManager().registerEvents(new ServerListener(),this);

    }

    /**
     * 服务器关闭时加载

    public void onDisable() {
        getLogger().info("服务器开始卸载JiChu");
    }*/

    /**
     * 服务器接受命令时
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (Static.checkArgs(args,1)){
                switch (label){
                    case "anjia":
                    case "huijia":
                    case "chaijia":
                        commands = new CommandHome(label,args);
                        break;
                    case "chuansong":
                        if (null != p1)
                            p1 = null;
                        p1 = player;
                        p2 = Static.getPlayers(args[0]);
                        if (p1.equals(p2)){
                            p1.sendMessage(ChatColor.RED + "你不可以传送自己");
                            return true;
                        }
                        if (null == p2){
                            p1.sendMessage(ChatColor.RED + "该玩家不存在或已离线");
                            return true;
                        }
                        if (!Validity.isEmpty(timerTask))
                            timerTask = null;
                        timerTask = new ChuanTimerTask(p1, p2);
                        new Timer().schedule(timerTask,30000L);
                        p1.sendMessage(ChatColor.GREEN + "已发送传送请求,请等待对方处理");
                        p2.sendMessage(ChatColor.GREEN + p1.getName()+"请求传送到你身边,使用/tongyi或/jujue处理");
                        return true;
                }
            } else if (Static.checkArgs(args,0)){
                switch (label){
                    case "tongyi":
                        if (!Validity.isEmpty(p2)){
                            timerTask.cancel();
                            p2.sendMessage(ChatColor.GREEN + "成功传送");
                            p1.sendMessage(ChatColor.GREEN + "成功传送");
                            p1.teleport(p2.getLocation());
                        }else {
                            sender.sendMessage(ChatColor.RED + "当前没有玩家请求传送");
                        }
                        break;
                    case "jujue":
                        if (!Validity.isEmpty(p2)){
                            timerTask.cancel();
                            p2.sendMessage(ChatColor.GREEN + "你拒绝了" + p1.getDisplayName()+ "的传送请求");
                            p1.sendMessage(ChatColor.GREEN + p2.getDisplayName()+ "拒绝了你的传送请求");
                        }else {
                            sender.sendMessage(ChatColor.RED + "当前没有玩家请求传送");
                        }
                        break;
                }
                p1 = p2 = null;
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "请输入正确的参数");
                return true;
            }
            return commands.onCommand(p1,p2);
        }
        sender.sendMessage("只有玩家可以输入该命令");
        return true;
    }
}