package commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sql.Metodos;

public class Ban implements CommandExecutor {

    public static String getStringInArgs(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i != start) sb.append(" ");
            sb.append(args[i]);
        }
        return sb.toString();
    }

    public static boolean ban(Player p, String[] args, String message) {
        if (args.length == 1) {

            String targetName = args[0];
            OfflinePlayer targetOffline = Bukkit.getOfflinePlayer(targetName);
            Player target = Bukkit.getPlayer(targetName);
            String motivo = getStringInArgs(args, 0);

            if (targetOffline.isOnline()) {
                target.kickPlayer("Você foi banido");
            }
            target.setBanned(true);
            Metodos.banir(p, target, motivo);
        }
        return false;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("Tuca.Banir")) {

            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);
            String message = getStringInArgs(args, 0);
            ban(target, args, message);
        }
        return false;
    }

    public static String getMotivo(String[] mensagem, int start) {
        return getStringInArgs(mensagem, start);
    }
}