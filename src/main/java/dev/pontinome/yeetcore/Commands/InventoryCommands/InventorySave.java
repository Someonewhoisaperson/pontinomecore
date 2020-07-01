package dev.pontinome.yeetcore.Commands.InventoryCommands;

import dev.pontinome.yeetcore.BukkitSerialization;
import dev.pontinome.yeetcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class InventorySave implements CommandExecutor {

    Main plugin;

    public InventorySave(Main pl) {
        plugin = pl;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.getGameMode().equals(GameMode.SURVIVAL)) {
                plugin.sendMessage(player, "Command is Survival only");
                return true;
            }
            if(!player.hasPermission("pontinome.saveinv")) {
                plugin.sendMessage(player, "You lack the power to execute this command");
                return true;
            }
            if(args.length == 0) {
                saveInvToData(player);
                plugin.sendMessage(player, "Your inventory has been saved");
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    plugin.sendMessage(player, "That player was not found");
                    return true;
                }
                if(!player.hasPermission("pontinome.saveinv.forothers")) {
                    plugin.sendMessage(player, "You lack the power to execute this command");
                    return true;
                }
                saveInvToData(target);
                plugin.sendMessage(player,"You saved " + target.getName() + "'s inventory");
            }

        } else if (sender instanceof ConsoleCommandSender) {
            //do console stuff
        } else {
            sender.sendMessage("You cannot execute this command");
            return true;
        }

        return true;
    }
    public void saveInvToData(Player player) {
        Inventory playerinv = player.getInventory();
        String invdata = BukkitSerialization.itemStackArrayToBase64(playerinv.getContents());
        if(plugin.YAMLenabled()) {
            File f = new File(plugin.getDataFolder() + File.separator + "P" + player.getUniqueId() + ".yml"); //File thingh
            FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f);
            playerData.set("Data,Inventory", invdata);
            try {
                playerData.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (plugin.gsonEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
        } else if (plugin.mongoEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
        } else if (plugin.mysqlEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
        }
    }
}
