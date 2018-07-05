package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void advert(Player player) {
        String title = PluginLoader.instance.messagesConfig.getConfiguration().getString("actionbar.title");

        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\" + " + title + " + \"}");
        PacketPlayOutTitle actitle = new PacketPlayOutTitle();
    }
}
