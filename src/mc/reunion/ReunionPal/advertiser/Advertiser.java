package mc.reunion.ReunionPal.advertiser;

import mc.reunion.ReunionPal.PluginLoader;
import mc.reunion.ReunionPal.model.ActionBar;
import mc.reunion.ReunionPal.model.Bossbar;
import mc.reunion.ReunionPal.model.Chat;
import org.bukkit.Bukkit;

import static mc.reunion.ReunionPal.PluginLoader.*;
public class Advertiser {
    public static void enable() {
        long bossbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("bossbar.repeat");
        long chat_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("chat.repeat");
        long actionbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("actionbar.repeat");

        // checking timer
        bossbar_secs = checkTimer(bossbar_secs, "bossbar");
        chat_secs = checkTimer(chat_secs, "chat");
        actionbar_secs = checkTimer(actionbar_secs, "actionbar");


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

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> ActionBar.advert(p));
            }
        }, 40, chat_secs);

        // 36000ticks - 30 mins
        // 400ticks - 20 secs
    }

    public static void disable() {
        Bukkit.getScheduler().cancelAllTasks();
    }

    public static long checkTimer(long secs, String type) {
        // One day
        if (secs > 43200) {
            send("Oh, looks like you entered number longer than 12 hours in " + type + ". Whoops.");
            // 12 hours
            secs = 43200;
        }
        if (secs < 60) {
            send("Oh, looks like you entered number lesser than 1 minute in " + type + ". Whoops.");
            // 12 hours
            secs = 120;
        }
        return secs;
    }
}
