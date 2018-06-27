package mc.reunion.ReunionPal.model;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bossbar {
    private String title = "§cMcReunion.eu";
    private BarColor color = BarColor.WHITE;

    private BossBar bar = Bukkit.createBossBar(title, color, BarStyle.SOLID, BarFlag.CREATE_FOG);

    public void sendBar(Player player) {
        bar.addPlayer(player);
    }
    public void hideBar(Player player) {
        bar.removePlayer(player);
    }
}
