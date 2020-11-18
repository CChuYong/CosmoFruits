package fruits.cosmo;

import fruits.cosmo.classes.TreeTypes;
import fruits.cosmo.listener.SignListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CosmoFruit extends JavaPlugin {
    public static CosmoFruit INSTANCE;
    public static HashMap<String, TreeTypes> t = new HashMap<String, TreeTypes>();
    public void onEnable(){
        this.saveDefaultConfig();
        INSTANCE = this;
        System.out.println("[CosmoFruit] Plugin Initializing..");
        load();
        getServer().getPluginManager().registerEvents(new SignListener(), this);
    }
    public void load(){
        if(getConfig().contains("타입")){
            for(String keys : getConfig().getConfigurationSection("타입").getKeys(false)){
                t.put(keys, new TreeTypes(getConfig().getInt("타입."+keys+".시간A"),getConfig().getInt("타입."+keys+".시간B"),
                        getConfig().getString("타입."+keys+".세메틱A"),
                        getConfig().getStringList("타입."+keys+".세메틱B")));
                System.out.println(keys);
            }
        }
    }
}
