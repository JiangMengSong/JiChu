package com.rongyaoserver.jichu.listener;

import com.rongyaoserver.jichu.util.Static;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (null != Static.getPlayers(p.getDisplayName())){

        }
        Static.setPlayer(p.getDisplayName(),p.getUniqueId());
        Bukkit.broadcastMessage(ChatColor.GREEN +"欢迎"+ p.getDisplayName() +"加入荣耀服务器");
    }

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (null != Static.getPlayers(p.getDisplayName())){

        }
        Static.removePlayer(p.getDisplayName());
        Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() +"离开了荣耀服务器");
    }
}
