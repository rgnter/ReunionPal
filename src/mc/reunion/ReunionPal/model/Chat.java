package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Chat {

    public static void advert(Player player) {
        sendMessage(player);
    }

    private static void sendMessage(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes(
                '&',
                PluginLoader.instance.messagesConfig.getConfiguration().getString(
                        "chat.title")));
    }
}
