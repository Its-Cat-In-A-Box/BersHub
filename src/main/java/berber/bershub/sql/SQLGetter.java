package berber.bershub.sql;

import berber.bershub.BersHub;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SQLGetter {
    final JavaPlugin serv;
    final FileConfiguration conf;
    public SQLGetter(JavaPlugin plugin){
        serv = plugin;
        conf = serv.getConfig();
    }

    public void createTable(){
        int noOfTables = conf.getList("SQL.Tables").size();

        for(int i = 0; i < noOfTables; i++){
            try{
                HashMap thisTable = (HashMap) conf.getList("SQL.Tables").get(i);
                StringBuilder psPart1 = new StringBuilder();
                psPart1.append("CREATE TABLE IF NOT EXISTS ").append(thisTable.get("Name"));
                StringBuilder psPart2 = new StringBuilder().append("(");
                ArrayList tableKeys = (ArrayList) thisTable.get("Keys");
                int numberOfKeys = tableKeys.size();
                //Bukkit.getLogger().info(String.valueOf(conf.getList("SQL.Tables").get(0))));
                for(int n = 0; n < numberOfKeys; n++){
                    HashMap curHas = (HashMap) tableKeys.get(n);
                    psPart2.append(curHas.get("Name")).append(" ").append(curHas.get("Type")).append(", ");
                }
                HashMap firstHas = (HashMap) tableKeys.get(0);
                psPart2.append("PRIMARY KEY(").append(firstHas.get("Name")).append("))");
                Bukkit.getLogger().info(psPart1.toString()+psPart2.toString());
                PreparedStatement ps = BersHub.SQL.getCon().prepareStatement(psPart1.toString()+psPart2.toString());
                ps.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }
}


