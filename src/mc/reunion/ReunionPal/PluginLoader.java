package mc.reunion.ReunionPal;

import mc.reunion.ReunionPal.configuration.LocalizationConfig;
import mc.reunion.ReunionPal.model.TestCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    // Prefix
    public static final String PREFIX = "§c§lReunionPal §7>> ";

    // FileConfigurations
    LocalizationConfig lc;

    // On plugin load
    @Override
    public void onLoad() {
        send("Loading plugin...");
        // TODO Cokolvek čo chceš načítať

        send("Loading configuration");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        send("Loading LocalizationConfig");
        lc = new LocalizationConfig(this);

    }

    // On plugin enable
    @Override
    public void onEnable() {
        send("Plugin enabled!");
        send("Just some developer informations...");
        send("Version: " + this.getDescription().getVersion());
        send("Main: " + this.getDescription().getMain());
        send("Data folder path: " + this.getDataFolder().getPath());

        this.getCommand("barstart").setExecutor(new TestCommands());
    }

    // Sends colorized message, will be more visible in console
    public static void send(String msg) {
        msg = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + msg);
    }

    // Exception sender
    public static <T extends Exception> void send(T clazz) {
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + "Caught §cEXCEPTION: §7" + clazz.getMessage().trim());
        Bukkit.getServer().getConsoleSender().sendMessage("§cEXCEPTION §7StackTrace: ");
        clazz.printStackTrace();
    }
}
