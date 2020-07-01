package dev.pontinome.yeetcore.Commands.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

import java.util.Arrays;

public class FriendChat implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.hasPermission("friendschat.view")) {
                String msg = Arrays.toString(args);
                msg = msg.substring(1, msg.length() -1 );
                p.sendMessage(ChatColor.BLUE + "FC>>" + ChatColor.WHITE + "<" + player.getDisplayName() + ">" + msg);
            }
        }
        return true;
    }
}
