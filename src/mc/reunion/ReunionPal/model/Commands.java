package mc.reunion.ReunionPal.model;

import mc.reunion.ReunionPal.PluginLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
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
                    String es = "\|";
                    if(!sender.isOp() && sender.hasPermission("reunionpal.admin")) {
                        sender.sendMessage("§cYou need to be administrator to use this command!");
                        return true;
                    }
                    PluginLoader.instance.reloadConfig();
                    PluginLoader.instance.messagesConfig.reload();
                    PluginLoader.instance.reload();
                    sender.sendMessage("§aReloaded with love ^^");
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        return Arrays.asList("pal");
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
