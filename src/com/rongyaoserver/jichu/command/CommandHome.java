package com.rongyaoserver.jichu.command;

import com.rongyaoserver.jichu.entity.Home;
import com.rongyaoserver.jichu.mapper.HomeMapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHome extends CommandParent {

    public CommandHome(String label, String[] args) {
        super(label, args);
    }

    private int HomeCount = 3;

    @Override
    public boolean onCommand(Player p1, Player p2) {
        if (label.equals("anjia")){
            List<Home> homeList = HomeMapper.getHomeList(p1);
            if (null == homeList || HomeCount <= homeList.size()){
                p1.sendMessage(ChatColor.RED + "已超过设置的家上限");;
                return true;
            }
            for (Home home : homeList){
                if (home.getH_name().equals(args[0])){
                    p1.sendMessage(ChatColor.RED + "当前家已存在");;
                    return true;
                }
            }
            if (!HomeMapper.setHome((Player) p1,args[0])){
                p1.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "设置失败,请联系管理员");
                return true;
            }
            p1.sendMessage(ChatColor.GREEN + "'" + args[0] + "'" + "设置成功");
            return true;
        }else if (label.equals("huijia")){
            Home h = HomeMapper.getHome(p1,args[0]);
            if (null == h){
                p1.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "不存在");
                return true;
            }
            ((Player)p1).teleport(
                    new Location(
                            Bukkit.getWorld(h.getH_world()),
                            h.getH_x(), h.getH_y(), h.getH_z()
                    )
            );
            p1.sendMessage(ChatColor.GREEN + "返回" + "'" + args[0] + "'" + "成功");
            return true;
        }else if (label.equals("chaijia")){
            if (null == HomeMapper.getHome(p1,args[0])){
                p1.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "不存在");
                return true;
            }
            if (!HomeMapper.delHome(p1,args[0])){
                p1.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "移除失败,请联系管理员");
                return true;
            }
            p1.sendMessage(ChatColor.GREEN + "'" + args[0] + "'" + "移除成功");
        }
        return false;
    }
}
