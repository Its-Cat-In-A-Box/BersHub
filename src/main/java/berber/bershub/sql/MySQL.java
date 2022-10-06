package berber.bershub.sql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    Boolean enabled;
    String host;
    String port;
    String database;
    String username;
    String password;
    Connection connection;
    public MySQL(JavaPlugin server){
        FileConfiguration conf = server.getConfig();
        enabled = conf.getBoolean("SQL.Enabled");
        host = conf.getString("SQL.Host");
        port = conf.getString("SQL.Port");
        database = conf.getString("SQL.Database");
        username = conf.getString("SQL.Username");
        password = conf.getString("SQL.Password");
    }
    public boolean isConnected(){
        if (!enabled) return false;
        return(connection != null);
    }

    public int connect(){
        if (!isConnected() && enabled){
            try{
                connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?useSSL=false",username,password);
                return 0;
            }catch (SQLException e){
                return 1;
            }
        }
        return 2;
    }

    public int disconnect(){
        if (isConnected()){
            try{
                connection.close();
                return 0;
            } catch (SQLException e) {
                return 1;
            }
        }
        return 0;
    }

    public Connection getCon(){
        return connection;
    }

}
