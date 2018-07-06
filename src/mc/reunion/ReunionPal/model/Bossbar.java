package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bossbar {
    private static String title = "";
    private static BarColor color = BarColor.WHITE;
    public static boolean active = false;
    private static int progressbar_id = -1;
    private static BossBar bar = Bukkit.createBossBar(title, color, BarStyle.SOLID, BarFlag.CREATE_FOG);

    public static void advert() {
        if(active) {
            return;
        }
        active = true;
        Bukkit.getOnlinePlayers().        // showing to players
                forEach(p -> sendBar(p));

        bar.setTitle(PluginLoader.instance.messagesConfig.getConfiguration().getString("bossbar.title"));

        // hiding after 15 seconds
        Bukkit.getScheduler().scheduleSyncDelayedTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                active = false;
                Bukkit.getOnlinePlayers().forEach(p -> hideBar(p));
                resetBar();
            }
        }, (10 * 20));

        progressbar_id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginLoader.instance, new Runnable() {
            @Override
            public void run() {
                if(!active) {
                    Bukkit.getScheduler().cancelTask(progressbar_id);
                    return;
                }
                if(bar.getProgress() < 0) {
                    return;
                }
                bar.setProgress(bar.getProgress() - 0.1);
            }
        }, 20, 20);
    }

    private static void sendBar(Player player) {
        bar.addPlayer(player);
    }

    private static void hideBar(Player player) {
        bar.removePlayer(player);
    }

    private static void decreaseBar() {

    }

    private static void resetBar() {
        bar.setProgress(1);
    }

    public static boolean running() {
        return active;
    }
}
