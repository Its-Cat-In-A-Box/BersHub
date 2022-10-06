package berber.bershub.sql;

import berber.bershub.BersHub;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGetter {
    final JavaPlugin serv;
    final FileConfiguration conf;
    public SQLGetter(JavaPlugin plugin){
        serv = plugin;
        conf = serv.getConfig();
    }

    public void createTable(){
        int noOfTables = conf.getInt("SQL.numberOfTables");
        for(int i = 0; i < noOfTables; i++){

            try{
                StringBuilder thisTable = new StringBuilder().append("SQL.Table.").append(String.valueOf(i+1)).append(".Name");
                StringBuilder psPart1 = new StringBuilder();
                psPart1.append("CREATE TABLE IF NOT EXISTS ").append(conf.getString("SQL.Table." + String.valueOf(i+1))).append(".Name");

                StringBuilder psPart2 = new StringBuilder().append("(");
                int numberOfKeys = conf.getInt(thisTable.append("numberOfKeys").toString());
                for(int n = 0; n < numberOfKeys; n++){
                    psPart2.append(conf.getString(thisTable.append(".Keys.").append(n+1).append(".Name").toString())).append(" ").append(conf.getString(thisTable.append(".Keys.").append(n+1).append(".Type").toString())).append(", ");
                }
                psPart2.append("PRIMARY KEY(").append(conf.getString(thisTable.append(".Keys.1.Name").toString())).append("))");
                Bukkit.getLogger().info(psPart1.toString()+psPart2.toString());
                PreparedStatement ps = BersHub.SQL.getCon().prepareStatement(psPart1.toString()+psPart2.toString());

            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }
}


