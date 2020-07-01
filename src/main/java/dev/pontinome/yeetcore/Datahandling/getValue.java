package dev.pontinome.yeetcore.Datahandling;

import dev.pontinome.yeetcore.Main;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class getValue {

    public static String getYAMLvalue(String path, UUID uuid) {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file config
        return playerData.getString(path);

    }
    public static int getYAMLIntvalue(String path, UUID uuid) {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file config
        return playerData.getInt(path);

    }
    public static GameMode getYAMLGamemodevalue(String path, UUID uuid){
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file confi
        return GameMode.getByValue(getYAMLIntvalue(path, uuid));
    }
    public static boolean getYAMLBoolvalue(String path, UUID uuid) {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file confi
        return playerData.getBoolean(path);
    }


}
