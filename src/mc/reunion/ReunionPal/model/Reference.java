package mc.reunion.ReunionPal.model;

import org.bukkit.plugin.Plugin;

import java.io.File;

public class Reference {
    /**
     * PLUGIN REFERENCES
     * <p>
     * Referencie pluginu, napríklad chat prefix, data folder, apod.
     **/
    // Plugin instance
    private static Plugin plugin = null;

    // Load method
    public static void onLoad(Plugin pluginz) {
        plugin = pluginz;
    }

    // TODO Replace >> with some ANSI special chars
    // Chat-Prefix
    public static final String PREFIX = "§c§lReunionPal §7>> ";

    // Data folder
    public static final String DATA_FOLDER_PATH = plugin.getDataFolder().getPath();
    public static final File DATA_FOLDER_FILE = plugin.getDataFolder();
}
