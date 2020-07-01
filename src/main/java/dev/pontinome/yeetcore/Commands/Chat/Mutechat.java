package dev.pontinome.yeetcore.Commands.Chat;

import dev.pontinome.yeetcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Mutechat implements CommandExecutor {

    Main plugin;

    public Mutechat(Main pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
