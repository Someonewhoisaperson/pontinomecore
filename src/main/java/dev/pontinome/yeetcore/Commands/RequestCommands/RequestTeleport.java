package dev.pontinome.yeetcore.Commands.RequestCommands;

import dev.pontinome.yeetcore.Datahandling.UpdateValue;
import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class RequestTeleport implements CommandExecutor {

    Main plugin;

    public RequestTeleport(Main pl) {
        plugin = pl;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            plugin.sendMessage(sender, "can only be used by in game players");
            return true;
        }
        if(args.length != 1) {
            plugin.sendMessage(sender, "Specifiy a player");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            plugin.sendMessage(sender, "That player does not exist");
            return true;
        }
        Player player = (Player) sender;
        if(getValue.getYAMLIntvalue("Data.rqtpUses", player.getUniqueId()) > 1) {
            plugin.sendMessage(player, "You dont have any tps left");
            return true;
        }
        UpdateValue.updateYAML("Data.rqtpUses", getValue.getYAMLIntvalue("Data.rqtpUses", player.getUniqueId()) + 1, player.getUniqueId());
        plugin.sendMessage(player, "Type /rqtp confirm to confirm your teleport");
        part2(player, target);
        player.teleport(target.getLocation());
        return true;

    }
    public void part2(Player player, Player target) {
        plugin.sendMessage(player,"You have confirmed your teleport");
        plugin.sendMessage(player, "Please wait until the person you are teleporting to accepts");
        target.sendMessage( player.getDisplayName() + " Has sent you a teleporation request, type '/rqtp accept' to allow for them to teleport to you ");

    }
}
