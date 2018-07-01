package mc.reunion.ReunionPal.advertiser;

import mc.reunion.ReunionPal.PluginLoader;
import mc.reunion.ReunionPal.model.Bossbar;
import mc.reunion.ReunionPal.model.Chat;
import org.bukkit.Bukkit;

public class Advertiser {
    public static void enable() {
        long bossbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("bossbar.repeat") * 20;
        long chat_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("chat.repeat") * 20;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> Bossbar.advert(p));
            }
        }, bossbar_secs, bossbar_secs);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> Chat.advert(p));
            }
        }, chat_secs, chat_secs);

        // 36000ticks - 30 mins
        // 400ticks - 20 secs
    }
    public static void disable() {
        Bukkit.getScheduler().cancelAllTasks();
    }
}
