package xyz.juehen.jichu.mapper;

import xyz.juehen.jichu.entity.Home;
import xyz.juehen.jichu.util.DBHelper;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.List;

public class HomeMapper {

    public static boolean setHome(Player player, String homeName){
        Location loc = player.getLocation();
        String sql = "insert into `u_home`(h_x,h_y,h_z,h_world,h_date,h_name,h_user)values (?,?,?,?,?,?,?)";
        return new DBHelper().executeUpdate(sql,new Object[]{
                loc.getX(),loc.getY(),loc.getZ(),
                loc.getWorld().getName(),new Date(),homeName,
                player.getName()
        });
    }

    public static Home getHome(CommandSender sender, String homeName){
        try {
            String sql = "select * from `u_home` where h_user = ? and h_name = ?";
            List<Home> homeList = new DBHelper().executeQuery(sql,new Object[]{sender.getName(),homeName},Home.class);
            if (homeList.size() != 1)
                return null;
            return homeList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean delHome(CommandSender sender, String homeName){
        String sql = "delete from `u_home` where h_name = ? and h_user = ?";
        return new DBHelper().executeUpdate(sql,new Object[]{homeName,sender.getName()});
    }

    public static List<Home> getHomeList(CommandSender sender){
        String sql = "select h_name from `u_home` where h_user = ?";
        try {
            return new DBHelper().executeQuery(sql,new Object[]{sender.getName()},Home.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
