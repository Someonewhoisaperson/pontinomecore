package dev.pontinome.yeetcore.Events;

import dev.pontinome.yeetcore.BukkitSerialization;
import dev.pontinome.yeetcore.Commands.InventoryCommands.InventorySave;
import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;

public class onPlayerDisconnect implements Listener {



    @EventHandler
    public void onPlayerDisconnectEvent (PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(getValue.getYAMLBoolvalue("Settings.Saveinventoryonquit", player.getUniqueId())) {
           saveToData(player);
           Main.plugin.getLogger().info("Saved "  + player.getName() + "'s inventory because they had the setting on" );
        }

    }
    public void saveToData(Player player) {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + player.getUniqueId() + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f);
        Inventory playerinv = player.getInventory();
        String invdata = BukkitSerialization.itemStackArrayToBase64(playerinv.getContents());
        playerData.set("Data.Inventory", invdata);
        playerData.set("Data.Health", player.getHealth());
        try {
            playerData.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
