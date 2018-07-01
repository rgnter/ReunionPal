package mc.reunion.ReunionPal;

import mc.reunion.ReunionPal.advertiser.Advertiser;
import mc.reunion.ReunionPal.configuration.MessagesConfig;
import mc.reunion.ReunionPal.model.Commands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    // Prefix
    public static final String PREFIX = "§c§lReunionPal §7>> ";

    // instance
    public static PluginLoader instance;
    //Adversiter
    Advertiser ads = new Advertiser();

    // FileConfigurations
    public MessagesConfig messagesConfig;

    // On plugin load
    @Override
    public void onLoad() {
        send("Loading plugin...");
        // TODO Cokolvek čo chceš načítať

        send("Loading default configuration");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        send("Loading messages configuration");
        messagesConfig = new MessagesConfig("messages",this);
       // messagesConfig.saveDefaultConfig();

    }

    // On plugin enable
    @Override
    public void onEnable() {
        instance = this;
        send("Plugin enabled!");
        send("Just some developer informations...");
        send("Version: " + this.getDescription().getVersion());
        send("Main: " + this.getDescription().getMain());
        send("Data folder path: " + this.getDataFolder().getPath());

        send("--");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Bossbar title: " + messagesConfig.getConfiguration().getString("bossbar.title"));
        send("Bossbar repeats every: " + messagesConfig.getConfiguration().getLong("bossbar.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("bossbar.repeat")/60)+"mins");
        send("--");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Chat title: " + messagesConfig.getConfiguration().getString("chat.title"));
        send("Chat repeats every: " + messagesConfig.getConfiguration().getLong("chat.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("chat.repeat")/60)+"mins");
        send("--");
        send("Useful, i know");
        ads.enable();

        // commands
        this.getCommand("pal").setExecutor(new Commands());
    }

    // Sends colorized message, will be more visible in console
    public static void send(String msg) {
        msg = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + msg);
    }
    public static <T extends Object> void send(T msg) {
        String msgs = msg.toString().trim();
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + msgs);
    }
    // Exception sender
    public static <T extends Exception> void send(T clazz) {
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + "Caught §cEXCEPTION: §7" + clazz.getMessage().trim());
        Bukkit.getServer().getConsoleSender().sendMessage("§cEXCEPTION §7StackTrace: ");
        clazz.printStackTrace();
    }
    public void sendInfo() {
        send("--");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Bossbar title: " + messagesConfig.getConfiguration().getString("bossbar.title"));
        send("Bossbar repeats every: " + messagesConfig.getConfiguration().getLong("bossbar.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("bossbar.repeat")/60)+"mins");
        send("--");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Chat title: " + messagesConfig.getConfiguration().getString("chat.title"));
        send("Chat repeats every: " + messagesConfig.getConfiguration().getLong("chat.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("chat.repeat")/60)+"mins");
        send("--");
        send("Useful, i know");
    }

    public void reload() {
        Advertiser.disable();
        Advertiser.enable();
    }
}
