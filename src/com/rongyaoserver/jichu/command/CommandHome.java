package com.rongyaoserver.jichu.command;

import com.rongyaoserver.jichu.api.CommandApi;
import com.rongyaoserver.jichu.entity.Home;
import com.rongyaoserver.jichu.mapper.HomeMapper;
import com.rongyaoserver.jichu.opera.PlayerOpera;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHome implements CommandApi {
    public static final int homeCount = 3;

    private boolean setHome(Player player, String[] args){
        List<Home> homeList = HomeMapper.getHomeList(player);
        if (null == homeList || homeCount <= homeList.size()) player.sendMessage(ChatColor.RED + "已超过设置的家上限");
        else {
            for (Home home : homeList)
                if (home.getH_name().equals(args[0])){
                    player.sendMessage(ChatColor.RED + "当前家已存在");;
                    return true;
                }
            if (!HomeMapper.setHome(player,args[0])) player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "设置失败,请联系管理员");
            else player.sendMessage(ChatColor.GREEN + "'" + args[0] + "'" + "设置成功");
        }
        return true;
    }

    private boolean goHome(Player player, String[] args){
        Home h = HomeMapper.getHome(player,args[0]);
        if (null != h){
            player.teleport(
                    new Location(
                            Bukkit.getWorld(h.getH_world()),
                            h.getH_x(), h.getH_y(), h.getH_z()
                    )
            );
            player.sendMessage(ChatColor.GREEN + "返回" + "'" + args[0] + "'" + "成功");
        }
        else player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "不存在");
        return true;
    }

    private boolean removeHome(Player player, String[] args){
        if (null == HomeMapper.getHome(player,args[0])){
            player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "不存在");
        } else if (!HomeMapper.delHome(player,args[0])){
            player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + "移除失败,请联系管理员");
        } else {
            player.sendMessage(ChatColor.GREEN + "'" + args[0] + "'" + "移除成功");
        }
        return true;
    }

    private void getHome(Player player){
        List<Home> homeList = HomeMapper.getHomeList(player);
        if (null != homeList && homeList.size() != 0)
            for (Home home : homeList) player.sendMessage(home.getH_name());
        else player.sendMessage(ChatColor.GREEN + "未设置家,请使用/anjia [家的名字] 设置");
    }

    @Override
    public boolean onCommand(Player player, String label, String[] args) {
        if (checkArgs(args,1)){
            if (label.equals("anjia")) return setHome(player,args);
            else if (label.equals("huijia")) return goHome(player,args);
            else if (label.equals("chaijia")) return removeHome(player,args);
        }else if (checkArgs(args,0)){
            if (label.equals("jia")) getHome(player);
            return true;
        }
        return false;
    }
}