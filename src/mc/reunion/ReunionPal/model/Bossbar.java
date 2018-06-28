package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import static mc.reunion.ReunionPal.PluginLoader.*;

public class Bossbar {
    private static String title = "";
    private static BarColor color = BarColor.WHITE;

    private static BossBar bar = Bukkit.createBossBar(title, color, BarStyle.SOLID, BarFlag.CREATE_FOG);

    public static void advert(Player player) {
        bar.setTitle(PluginLoader.instance.messagesConfig.getConfiguration().getString("bossbar.title"));
        sendBar(player);
        Thread wajter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // play sound
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_GUITAR, 1, 1);
                    // set visible to player

                    // seconds left
                    int left = 10;

                    do {
                        left--;
                        if(left != -1) {
                            if(left != 0) {
                                decreaseBar();
                            }
                            else {
                                resetBar();
                                hideBar(player);
                            }
                        }
                        Thread.sleep(1000);
                    } while (left >= 0);

                } catch (InterruptedException x) {
                    send(x);
                } finally {
                    hideBar(player);
                    resetBar();
                }
            }
        });
        wajter.start();
    }

    private static void sendBar(Player player) {
        bar.addPlayer(player);
    }

    private static void hideBar(Player player) {
        bar.removePlayer(player);
    }

    private static void decreaseBar() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i <= 10; i++) {
                        bar.setProgress(bar.getProgress() - 0.01);
                        Thread.sleep(20);
                    }
                } catch (InterruptedException x) {
                    send(x);
                }
            }
        });
        t.start();
    }

    private static void resetBar() {
        bar.setProgress(1);
    }
}
