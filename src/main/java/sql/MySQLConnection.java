package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tuca.Tuca;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class MySQLConnection {

    protected static Connection con = null;
    public static ConsoleCommandSender sc = Bukkit.getConsoleSender();


    protected static void open(){
        String url = "jdbc:mysql://localhost:3306/servidor";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            sc.sendMessage(Metodos.prefix + "§aConexão com MySQL aberta!");
        } catch (SQLException e) {
            sc.sendMessage(Metodos.prefix + "§cConexão com MySQL não foi possivel!");
            Tuca.getInstance().getPluginLoader().disablePlugin(Tuca.getInstance());
        }
    }

    protected static void close(){
        if (con != null){
            try {
                con.close();
                sc.sendMessage(Metodos.prefix + "§aConexão fechada com sucesso!");
            } catch (SQLException e) {
                sc.sendMessage(Metodos.prefix + "§cNão foi possivel fechar a conexão!");
            }
        }
    }

    protected static void createTable(){
        if (con != null){

            try {
                PreparedStatement stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `banidos` (`id` INT NOT NULL AUTO_INCREMENT,`player` VARCHAR(24) NULL, `staff` DOUBLE NULL`, `motivo`, VARCHAR(100),PRIMARY KEY (`id`));");
                stm.executeUpdate();
                sc.sendMessage(Metodos.prefix + "§aTabela carregada");
            } catch (SQLException e) {
                e.printStackTrace();
                sc.sendMessage(Metodos.prefix + "§cNão foi possivel carregar a tabela");
            }
        }
    }
}

