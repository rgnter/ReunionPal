package mc.reunion.ReunionPal.advertiser;

import mc.reunion.ReunionPal.PluginLoader;
import mc.reunion.ReunionPal.model.ActionBar;
import mc.reunion.ReunionPal.model.Bossbar;
import mc.reunion.ReunionPal.model.Chat;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

import static mc.reunion.ReunionPal.PluginLoader.*;

public class Advertiser {
    public static Map<String, Boolean> active = new HashMap<>();

    public static void enable() {
        long bossbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("bossbar.repeat");
        long chat_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("chat.repeat");
        long actionbar_secs = PluginLoader.instance.messagesConfig.getConfiguration().getLong("actionbar.repeat");

        // checking timer
        bossbar_secs = checkTimer(bossbar_secs, "bossbar");
        chat_secs = checkTimer(chat_secs, "chat");
        actionbar_secs = checkTimer(actionbar_secs, "actionbar");

        long bossbar_ticks = bossbar_secs * 20;
        long chat_ticks = chat_secs * 20;
        long actionbar_ticks = actionbar_secs * 20;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                if (Bossbar.active) {
                    return;
                }
                Bossbar.advert();
            }
        }, bossbar_ticks, bossbar_ticks);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> Chat.advert(p));
            }
        }, chat_ticks, chat_ticks);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> ActionBar.advert(p));
            }
        }, actionbar_ticks, actionbar_ticks);

        // 36000ticks - 30 mins
        // 400ticks - 20 secs
    }

    public static String quickAdvert(String type) {
        switch (type) {
            case "bossbar":
                Bossbar.advert();
                break;
            case "chat":
                Bukkit.getOnlinePlayers().forEach(p -> Chat.advert(p));
                break;
            case "actionbar":
                Bukkit.getOnlinePlayers().forEach(p -> ActionBar.advert(p));
                break;
        }
        return null;
    }

    public static void disable() {
        Bukkit.getScheduler().cancelTasks(PluginLoader.instance);
    }

    public static long checkTimer(long secs, String type) {
        // 12 hours
        if (secs > 43200) {
            send(type);
            repair(type, false);
            send("Oh, looks like you entered number longer than 12 hours in " + type + ". Whoops.");
            send("Repairing it for you... You lazy pig");
            // 12 hours
            secs = 43200;
        }
        // 2 minutes
        if (secs < 120) {
            repair(type, true);
            send("Oh, looks like you entered number lesser than 2 minutes in " + type + ". Whoops.");
            send("Repairing it for you... You lazy pig");
            // 12 hours
            secs = 120;
        }
        return secs;
    }

    private static void repair(String type, boolean lesser) {
        //if the number is lesser than minimum number
        if (lesser) {
            switch (type) {
                case "bossbar":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("bossbar.repeat", 120);
                    break;
                case "chat":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("chat.repeat", 120);
                    break;
                case "actionbar":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("actionbar.repeat", 120);
                    break;
                default:
                    System.out.println("Default");
            }
        } else {
            switch (type) {
                case "bossbar":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("bossbar.repeat", 43200);
                    break;
                case "chat":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("chat.repeat", 43200);
                    break;
                case "actionbar":
                    PluginLoader.instance.messagesConfig.getConfiguration().set("actionbar.repeat", 43200);
                    break;
                default:
                    System.out.println("Default");
            }
        }
        PluginLoader.instance.messagesConfig.save();
    }
}
