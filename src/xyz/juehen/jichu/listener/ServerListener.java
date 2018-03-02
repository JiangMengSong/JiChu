package xyz.juehen.jichu.listener;

import xyz.juehen.jichu.util.Validity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerListener implements Listener{

    private static Map<String,UUID> pMap = new HashMap<>();
    public static Player getPla(String pName) {
        return Validity.isEmpty(pName) ? null : Bukkit.getPlayer(pMap.get(pName));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (null != pMap.get(p.getDisplayName())){
            pMap.put(p.getDisplayName(),p.getUniqueId());
            Bukkit.broadcastMessage(ChatColor.GREEN +"欢迎"+ p.getDisplayName() +"加入荣耀服务器");
        }else{
            return;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (null != pMap.get(p.getDisplayName())){
            pMap.remove(p.getDisplayName());
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() +"离开了荣耀服务器");
    }
}
