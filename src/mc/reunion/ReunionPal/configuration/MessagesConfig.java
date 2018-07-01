package mc.reunion.ReunionPal.configuration;

import mc.reunion.ReunionPal.PluginLoader;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.Charset;

import static mc.reunion.ReunionPal.PluginLoader.send;

public class MessagesConfig {
    private File file;
    private FileConfiguration configuration;
    private Plugin plugin;

    public MessagesConfig(String name, Plugin plugin) {
        this.plugin = plugin;
        name = name.trim() + ".yml";
        try {
            file = new File(plugin.getDataFolder(), name);
            // if file does not exists then create it and load default config
            if (!file.exists()) {
                file.createNewFile();
                // loading default config
                loadDefaulty();
            }
            configuration = YamlConfiguration.loadConfiguration(file);
        } catch (Exception x) {
            send(x);
        }
    }
    public void reload() {
        try {
            configuration.load(file);
            configuration = YamlConfiguration.loadConfiguration(file);
            PluginLoader.instance.sendInfo();
        } catch (IOException x) {
            send(x);
        }
        catch (InvalidConfigurationException x) {
            send(x);
        }
    }
    public void saveDefaultConfig() {
        if (!this.file.exists()) {
            this.getPlugin().saveResource("messages.yml", false);
        }

    }

    public MessagesConfig(Plugin plugin) {
        new MessagesConfig("messages", plugin);
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    /***
     * Default localization file loader
     * */
    public void loadDefaulty() {
        // Default file reader
        InputStreamReader in_reader = new InputStreamReader(
                plugin.getResource("messages.yml"),
                Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(in_reader);

        // Localization file writer
        OutputStreamWriter out_writer = null;
        try {
            out_writer = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
        } catch (IOException x) {
            send(x);
        }
        Validate.notNull(out_writer);
        BufferedWriter writer = new BufferedWriter(out_writer);

        // Reading and writing stuff..
        try {
            String line;
            do {
                // reading line
                line = reader.readLine();
                if (line != null) {
                    //writing line
                    writer.write(line +"\n");
                }
            } while (line != null);
        } catch (IOException x) {
            send(x);
        } finally {
            try {
                writer.close();
                reader.close();
                in_reader.close();
                out_writer.close();
            } catch (IOException x) {
                send(x);
            }
        }
    }
}
