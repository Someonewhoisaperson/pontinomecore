package dev.pontinome.yeetcore.Datahandling;

import dev.pontinome.yeetcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UpdateValue {

    public static void updateYAML(String path, String value, UUID uuid){
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file config
        playerData.set(path, value);
        try {
            playerData.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateYAML(String path, int value, UUID uuid){
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file config
        playerData.set(path, value);
        try {
            playerData.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void updateYAML(String path, boolean value, UUID uuid){
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + uuid + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f); //the file config
        playerData.set(path, value);
        try {
            playerData.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
