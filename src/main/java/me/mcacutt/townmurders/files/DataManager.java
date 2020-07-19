package me.mcacutt.townmurders.files;

import me.mcacutt.townmurders.TownMurders;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private FileConfiguration dataConfigFileConfiguration;
    private FileConfiguration configFileConfiguration;
    File dataConfigfile;
    File configFile;

    public DataManager(TownMurders plugin) {
        plugin.saveDefaultConfig();
        configFile = new File(plugin.getDataFolder(), "config.yml");
        dataConfigfile = new File(plugin.getDataFolder(), "data.yml");
        dataConfigFileConfiguration = YamlConfiguration.loadConfiguration(dataConfigfile);
        configFileConfiguration = plugin.getConfig();
    }

    public FileConfiguration getConfigFile() {
        return configFileConfiguration;
    }

    public FileConfiguration getDataConfig() {
        return dataConfigFileConfiguration;
    }

    public void saveDataConfigFile() {
        try {
            dataConfigFileConfiguration.save(dataConfigfile);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    public void saveConfigFile() {
        try {
            configFileConfiguration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
