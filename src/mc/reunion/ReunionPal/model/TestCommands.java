package mc.reunion.ReunionPal.model;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static mc.reunion.ReunionPal.PluginLoader.send;
public class TestCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("barstart")) {
            Bossbar b = new Bossbar();
            b.sendBar((Player) sender);

            Thread wajt = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException x) {
                        send(x);
                    }
                }
            });
            wajt.start();
            try {
                wajt.join();
                b.hideBar((Player) sender);
            } catch (InterruptedException x) {
                send(x);
            }

        }
        return true;
    }
}
