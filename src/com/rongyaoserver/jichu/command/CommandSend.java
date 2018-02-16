package com.rongyaoserver.jichu.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandSend extends CommandParent {

    public CommandSend(String label, String[] args) {
        super(label, args);
    }

    @Override
    public boolean onCommand(Player p1, Player p2) {
        if (null == p2){
            p1.sendMessage(ChatColor.RED + "该玩家不存在或已离线");
            return true;
        }
        if (label.equals("chuansong")){
            p1.sendMessage(ChatColor.GREEN + "已发送传送请求,请等待对方处理");
            p2.sendMessage(ChatColor.GREEN + p1.getName()+"请求传送到你身边,使用/tongyi或/jujue处理");
        }
        return true;
    }
}
