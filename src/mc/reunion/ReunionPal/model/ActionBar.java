package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void advert(Player player) {
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + PluginLoader.instance.messagesConfig.getConfiguration().getString(
                ChatColor.translateAlternateColorCodes('&', "actionbar.title")) + " \"}");
        PacketPlayOutTitle actitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, chat);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(actitle);
    }
}
