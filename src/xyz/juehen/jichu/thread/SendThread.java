package xyz.juehen.jichu.thread;

import xyz.juehen.jichu.opera.PlayerOpera;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;

public class SendThread extends TimerTask {

    private Player p1;
    private Player p2;

    public SendThread(Player p1,Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        new Timer().schedule(this,10000L);
    }

    public void sendProcess(String label) {
        if (label.equals("tongyi")){
            p1.teleport(p2.getLocation());
            p1.sendMessage(ChatColor.GREEN + "传送成功");
            p2.sendMessage(ChatColor.GREEN + "传送成功");
        }else {
            p1.sendMessage(ChatColor.RED + p2.getDisplayName() +"拒绝了你的传送请求");
            p2.sendMessage(ChatColor.GREEN + "你拒绝了" + p1.getDisplayName() +"的传送请求");
        }
        PlayerOpera.remPlaReq(p2.getDisplayName());
        cancel();
    }

    @Override
    public void run() {
        PlayerOpera.remPlaReq(p2.getDisplayName());
        p1.sendMessage(ChatColor.RED + "传送超时,已取消");
        p2.sendMessage(ChatColor.RED + "传送超时,已取消");
        cancel();
    }
}
