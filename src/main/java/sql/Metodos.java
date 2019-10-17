package sql;

import commands.Ban;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos extends MySQLConnection {

    public static String prefix = "§aBan";
    public static ConsoleCommandSender sc = Bukkit.getConsoleSender();

    public static boolean contains(Player target) {
        try {


            PreparedStatement stm = con.prepareStatement("SELECT * FROM `banidos` WHERE `player`  = ?");
            stm.setString(1, String.valueOf(target));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void banir(Player p, Player targe, String args) {

        try {
            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);

            String message = Ban.getStringInArgs(args, 0);

            PreparedStatement stm = con.prepareStatement("INSERT INTO `banidos`(`player`, `staff`, `motivo`) VALUES (?,?,?)");
            stm.setString(1, target.getName());
            stm.setString(2, p.getName());
            stm.setString(3, message);
            stm.executeUpdate();
            sc.sendMessage(prefix + "§aPlayer" + target + "foi banido por" + p.getName() + ".");
        } catch (SQLException e) {
            sc.sendMessage(prefix + "§cNão foi possivel banir o player: §f" + targe.getName() + "§a do servidor!");
        }
    }


    public static String get(Player p) {
        if (contains(p)) {
            try {
                PreparedStatement stm = con.prepareStatement("SELECT * FROM `banidos` WHERE `player` = ?");
                stm.setString(1, p.getName());
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getString("motivo");
                }
                return rs.getString("motivo");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return "Player não está banido.";
        }
        return "Player não está banido";
    }

        public static boolean delete (Player p){
            if (contains(p)) {

                try {
                    PreparedStatement stm = con.prepareStatement("DELETE FROM `cash` WHERE `player` = ?");
                    stm.setString(1, p.getName());
                    stm.executeUpdate();
                } catch (SQLException e) {
                    sc.sendMessage(Metodos.prefix + "§cNão foi possivel remover o jogador §f" + p.getName() + "§c do banco de dados!");
                }
            } else {
                p.sendMessage("player não está banido");
                return false;
            }
            return false;
        }
    }



