package com.rongyaoserver.jichu.time;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.TimerTask;

public class ChuanTimerTask extends TimerTask {

    private Player p1;
    private Player p2;

    public ChuanTimerTask(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void run() {
        System.out.println(p1.getDisplayName() + p2);
        p1.sendMessage(ChatColor.RED + "传送超时,已取消");
        p2.sendMessage(ChatColor.RED + "传送超时,已取消");
        cancel();
    }
}
