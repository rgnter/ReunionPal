package mc.reunion.ReunionPal;

import mc.reunion.ReunionPal.model.Reference;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    // On plugin load
    @Override
    public void onLoad() {
        info("Loading plugin...");

        // Reference init
        debug("Initializing References...");
        Reference.onLoad(this);


        // TODO Cokolvek čo chceš načítať
    }

    // On plugin enable
    @Override
    public void onEnable() {
        info("Plugin enabled!");
        System.out.println();
        debug("Just some developer informations...");
        debug("Version: " + this.getDescription().getVersion());
        debug("Main: " + this.getDescription().getMain());
        debug("Data folder path: "+Reference.DATA_FOLDER_PATH);
        System.out.println();
    }

    // Sends colorized message, will be more visible in console
    public static void info(String msg) {
        msg = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(Reference.PREFIX + " §fINFO >>"+ " §f" + msg);
    }
    public static void debug(String msg) {
        msg = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(Reference.PREFIX + "§7" + msg);
    }
}
