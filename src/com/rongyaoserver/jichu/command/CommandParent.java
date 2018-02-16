package com.rongyaoserver.jichu.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandParent{
    protected String label; // 命令代码
    protected String[] args; // 命令参数

    public CommandParent(String label, String[] args) {
        this.label = label;
        this.args = args;
    }

    /**
     * 玩家命令交互事件
     * @param p1 申请交互的玩家
     * @param p2 被申请交互的玩家
     */
    public abstract boolean onCommand(Player p1, Player p2);
}
