package mc.reunion.ReunionPal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    // Prefix
    public static final String PREFIX = "§c§lReunionPal §7>> ";

    // On plugin load
    @Override
    public void onLoad() {
        send("Loading plugin...");

        // Reference init
        send("Initializing References...");


        // TODO Cokolvek čo chceš načítať
    }

    // On plugin enable
    @Override
    public void onEnable() {
        send("Plugin enabled!");
        System.out.println();
        send("Just some developer informations...");
        send("Version: " + this.getDescription().getVersion());
        send("Main: " + this.getDescription().getMain());
        send("Data folder path: "+ this.getDataFolder().getPath());
        System.out.println();
    }

    // Sends colorized message, will be more visible in console
    public static void send(String msg) {
        msg = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + " §fINFO >>"+ " §f" + msg);
    }
}
