package dev.pontinome.yeetcore.Events;

import dev.pontinome.yeetcore.BukkitSerialization;
import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import dev.pontinome.yeetcore.Datahandling.UpdateValue;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

import static dev.pontinome.yeetcore.BukkitSerialization.itemStackArrayToBase64;

public class onPlayerJoin implements Listener {
   /* Main plugin;

    public onPlayerJoin(Main pl) {
        plugin = pl;
    }*/

    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent event) throws IOException {
        //code run when a player joins the server

        //setup basic varibles which will be needed
        Player player = event.getPlayer();
        InetSocketAddress IP = player.getAddress();
        //String uuid = "P931c4e6f-89b7-44a3-995d-7ddb13860672";
        //UUID a = UUID.fromString(uuid);
        //create the player data file
        createYAMLPlayerData(event);
        UpdateValue.updateYAML("Data.Joins", getValue.getYAMLIntvalue("Data.Joins", player.getUniqueId()) + 1, player.getUniqueId());
        player.setGameMode(getValue.getYAMLGamemodevalue("Data.Gamemode", player.getUniqueId()));
        /*if(player.getDisplayName().equalsIgnoreCase("*thegamerash1")) {
            player.getInventory().setContents(getInvData(Bukkit.getPlayer(a)));
            File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + a + ".yml");
            FileConfiguration stzData = (FileConfiguration)YamlConfiguration.loadConfiguration(f);
            Location stzloc = (Location) stzData.get("Location.location");
            player.teleport(stzloc);
            player.setHealth(stzData.getDouble("Data.Health"));

        }*/









    }

    /*public void setupPlayerData(PlayerJoinEvent e) {
        if(plugin.mysqlEnabled()) {
            //do mysql setup
        } else if (plugin.mongoEnabled()) {
            //do mongo thing
        } else if(plugin.gsonEnabled()) {
            //do gson thing
        } else if (plugin.YAMLenabled()) {
            //do yaml thing
            createYAMLPlayerData(e);
        }
    }*/


    public void createYAMLPlayerData(PlayerJoinEvent e) {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + e.getPlayer().getUniqueId() + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration)YamlConfiguration.loadConfiguration(f); //the file config
        if (!f.exists()) { //checks if the file exists, if not it runs the code below
            try {
                f.createNewFile(); //creates a new file with the propertioes defined above
                playerData.createSection("Data"); //creates a section called data
                playerData.set("Data.JoinName", e.getPlayer().getName()); //adds theior name to thge data file
                playerData.set("Data.UUID", e.getPlayer().getUniqueId().toString()); //adds the uuid
                playerData.set("Data.JoinIP", e.getPlayer().getAddress().toString());
                playerData.set("Data.Inventory", itemStackArrayToBase64(e.getPlayer().getInventory().getContents()));
                playerData.set("Data.InventoryUndoValue", itemStackArrayToBase64(e.getPlayer().getInventory().getContents()));
                playerData.set("Data.Health", e.getPlayer().getHealth());
                playerData.set("Data.rqtpUses", 0);
                playerData.createSection("Stats"); //creates stats section
                playerData.set("Stats.Deaths", 0);
                playerData.set("Stats.Kills", 0);
                playerData.set("Stats.MobKills", 0);
                playerData.set("Stats.Joins", 0);
                playerData.createSection("Settings");
                playerData.set("Settings.Immortal", false);
                playerData.set("Settings.Gamemode", 0);
                playerData.set("Settings.CountDeaths", true);
                playerData.set("Settings.Saveinventoryonquit", false);
                playerData.createSection("Location");
                Player player;
                Location location = e.getPlayer().getLocation();
                playerData.set("Location.location", location);
                playerData.save(f);
                Main.plugin.getLogger().info("Created file for " + e.getPlayer().getName());
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }

    }
    public ItemStack[] getInvData(Player player) throws IOException {
        File f = new File(Main.plugin.getDataFolder() + File.separator + "P" + player.getUniqueId() + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f);
        playerData.set("Data.InventoryUndoValue", BukkitSerialization.itemStackArrayToBase64(player.getInventory().getContents()));
        playerData.save(f);
        return BukkitSerialization.itemStackArrayFromBase64(playerData.getString("Data.Inventory"));
    }


}
