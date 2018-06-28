package mc.reunion.ReunionPal.advertiser;

import mc.reunion.ReunionPal.PluginLoader;
import mc.reunion.ReunionPal.model.Bossbar;
import org.bukkit.Bukkit;

public class Advertiser {
    public static void enable() {
        long bossbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("bossbar.repeat") * 20;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> Bossbar.advert(p));
            }
        }, bossbar_secs, bossbar_secs);

        // 36000ticks - 30 mins
        // 400ticks - 20 secs
    }
}
