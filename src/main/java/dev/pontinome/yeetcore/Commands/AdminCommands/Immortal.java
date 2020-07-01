package dev.pontinome.yeetcore.Commands.AdminCommands;

import dev.pontinome.yeetcore.Datahandling.UpdateValue;
import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Immortal implements CommandExecutor {

    Main plugin;

    public Immortal(Main pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( !(sender instanceof Player) && (args.length < 1))  {
            //if the sender is not a player and the args is less then 1 then ignore since only players can toggle
            plugin.sendMessage(sender, "Please specify a player");
            return true;
        }
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            player = null;
        }
        if(args.length == 0) {
            if(getValue.getYAMLBoolvalue("Settings.Immortal", player.getUniqueId())) {
                //if settings.immortal is true then toggle it to false
                immortalityHandler(false, player);
                return true;
            } else {
                //if not true then false so toggle to true
                immortalityHandler(true, player);
                return true;
            }
        } else if(args.length == 1) {
            if(((args[0].equalsIgnoreCase("off")) || args[0].equalsIgnoreCase("false")) && (sender instanceof Player) ) {
                //if the argument is off or false & a player sent the command, then they are talking about themselves
                immortalityHandler(false, player);
            } else if (((args[0].equalsIgnoreCase("on")) || args[0].equalsIgnoreCase("true")) && (sender instanceof Player) ) {
                immortalityHandler(true, player);
            } else {
                //assume they select other player
                if(plugin.UUIDorPlayer(args[0]) == 0) {
                    immortalityHandler(true, Bukkit.getPlayer(args[0]));
                    plugin.sendMessage(player,"You made them immortal");
                }
            }
        }
        return true;
    }

    public void immortalityHandler(boolean Immortaltoggle, Player player) {
        if(Immortaltoggle) {
            plugin.sendMessage(player, "You are now immortal");
        } else {
            plugin.sendMessage(player, "You are no longer immortal");
        }
        UpdateValue.updateYAML("Settings.Immortal", Immortaltoggle, player.getUniqueId());
    }
}
