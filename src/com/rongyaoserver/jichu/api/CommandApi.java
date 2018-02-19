package com.rongyaoserver.jichu.api;

import com.rongyaoserver.jichu.opera.PlayerOpera;
import org.bukkit.entity.Player;

public interface CommandApi {
    PlayerOpera plaOpera = new PlayerOpera();
    /** 玩家命令事件 */
    boolean onCommand(Player player, String label, String[] args);
    default boolean checkArgs(String[] args, int number){
        if (null == args) return false;
        return args.length == number ? true : false;
    }
}
