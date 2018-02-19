package com.rongyaoserver.jichu.command;

import com.rongyaoserver.jichu.api.CommandApi;
import com.rongyaoserver.jichu.listener.ServerListener;
import com.rongyaoserver.jichu.thread.SendThread;
import com.rongyaoserver.jichu.opera.PlayerOpera;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandSend implements CommandApi {

    @Override
    public boolean onCommand(Player player, String label, String[] args) {
        SendThread timerTask;
        if (label.equals("chuansong")){
            if (!checkArgs(args,1)) return false;
            Player p2 = ServerListener.getPla(args[0]);
            if (null == p2){
                player.sendMessage(ChatColor.RED + "该玩家不存在或已离线");
            } else if (player.equals(p2)){
                player.sendMessage(ChatColor.RED + "你不可以传送自己");
            } else if (null != PlayerOpera.getPlaReq(p2.getDisplayName())){
                player.sendMessage(ChatColor.RED + "玩家正在处理另一个请求,请稍后发送");
                return true;
            }else {
                timerTask = new SendThread(player, p2);
                PlayerOpera.setPlaReq(p2.getDisplayName(),timerTask);
                player.sendMessage(ChatColor.GREEN + "已发送传送请求,请等待对方处理");
                p2.sendMessage(ChatColor.GREEN + player.getName()+"请求传送到你身边,使用/tongyi或/jujue处理");
            }
            return true;
        }else{
            if (!checkArgs(args,0)) return false;
            timerTask = (SendThread) PlayerOpera.getPlaReq(player.getDisplayName());
            if (null != PlayerOpera.getPlaReq(player.getDisplayName())) timerTask.sendProcess(label);
            else player.sendMessage(ChatColor.RED + "当前没有玩家请求传送");
        }
        return true;
    }


}
