package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import net.minecraft.server.v1_12_R1.ChatModifier;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_12_R1.command.CraftConsoleCommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("pal")) {
            if(args.length < 1) {
                if (sender.isOp()) {
                    sendAdminInfo(sender);
                } else {
                    sendInfo(sender);
                }
                return true;
            } else {
                if(args[0].equalsIgnoreCase("reload")) {
                    if(!sender.isOp() && sender.hasPermission("reunionpal.admin")) {
                        sender.sendMessage("§cYou need to be administrator to use this command!");
                        return true;
                    }
                    PluginLoader.instance.reloadConfig();
                    PluginLoader.instance.messagesConfig.reload();
                    PluginLoader.instance.reload();
                    sender.sendMessage("§aReloaded with love ^^");
                    return true;
                }
                else {
                    sender.sendMessage("§cUnknown subcommand. §7:_(");
                    if(args[0].contains("re")) {
                        IChatBaseComponent msg = IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"Did you meant \",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/pal reload\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to suggest command\"}},{\"text\":\"reload\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/pal reload\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to suggest command\"}},{\"text\":\"?\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/pal reload\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to suggest command\"}}]");
                        PacketPlayOutChat chat = new PacketPlayOutChat(msg);
                        if(sender instanceof Player) {
                            Player player = (Player) sender;
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(chat);
                        }else {
                            sender.sendMessage("§7Did you meant §areload§7?");
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length > 0) {
            return Arrays.asList("reload");
        }else {
            return Arrays.asList("pal");
        }
    }

    public void sendInfo(CommandSender sender) {
        sender.sendMessage(PluginLoader.PREFIX +  " §av" + PluginLoader.instance.getDescription().getVersion());
    }
    public void sendAdminInfo(CommandSender sender) {
        sender.sendMessage(PluginLoader.PREFIX +  " §av" + PluginLoader.instance.getDescription().getVersion());
        sender.sendMessage("§7Looks like you are administrator. List of administrator commands:");
        sender.sendMessage("§6/pal reload §7- Reloads all configurations");
    }
}
