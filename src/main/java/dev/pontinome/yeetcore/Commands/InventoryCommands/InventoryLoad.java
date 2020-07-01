package dev.pontinome.yeetcore.Commands.InventoryCommands;

import dev.pontinome.yeetcore.BukkitSerialization;
import dev.pontinome.yeetcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class InventoryLoad implements CommandExecutor {

    Main plugin;

    public InventoryLoad(Main pl) {
        plugin = pl;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("pontinome.loadinv")) {
                plugin.sendMessage(player, "You lack the power to execute this command");
                return true;
            }
            if(args.length == 0) {

                //mmaybe warn player it iwll erase their inv
                try {
                    player.getInventory().setContents(getInvData(player));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            //do console stuff
        }

        return true;
    }

    /**
     * Delete hthis after fixing below class
     */

    public ItemStack[] getInvData(Player player) throws IOException {
        File f = new File(plugin.getDataFolder() + File.separator + "P" + player.getUniqueId() + ".yml"); //File thingh
        FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f);
        playerData.set("Data.InventoryUndoValue", BukkitSerialization.itemStackArrayToBase64(player.getInventory().getContents()));
        playerData.save(f);
        return BukkitSerialization.itemStackArrayFromBase64(playerData.getString("Data.Inventory"));
    }

    /*
    public ItemStack[] getInvData(Player player) throws IOException {
        if(plugin.YAMLenabled()) {
            File f = new File(plugin.getDataFolder() + File.separator + "P" + player.getUniqueId() + ".yml"); //File thingh
            FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(f);
            playerData.set("Data.InventoryUndoValue", BukkitSerialization.itemStackArrayToBase64(player.getInventory().getContents()));
            playerData.save(f);
            return BukkitSerialization.itemStackArrayFromBase64(playerData.getString("Data.Inventory"));
        } else if (plugin.gsonEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
            return null;
        } else if (plugin.mongoEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
            return null;
        } else if (plugin.mysqlEnabled()) {
            plugin.sendMessage(player, "Please tell the server admin to use the YAML format as this connection type is not accepted atm");
            return null;
        } else {
            plugin.sendMessage(player,"Error, see console for more info");
            System.out.println("You messed up, InventoryLoad:59");
            return null;
        }

     */
    /**
     * Plugin.yamlenabled is broken idk y
     */
}
