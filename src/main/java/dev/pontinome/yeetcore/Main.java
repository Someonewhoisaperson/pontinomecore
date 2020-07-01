package dev.pontinome.yeetcore;

import dev.pontinome.yeetcore.Commands.AdminCommands.Immortal;
import dev.pontinome.yeetcore.Commands.Chat.FriendChat;
import dev.pontinome.yeetcore.Commands.InventoryCommands.InventoryLoad;
import dev.pontinome.yeetcore.Commands.InventoryCommands.InventorySave;
import dev.pontinome.yeetcore.Commands.RequestCommands.RequestTeleport;
import dev.pontinome.yeetcore.Events.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        this.getCommand("inventorysave").setExecutor(new InventorySave(this));
        this.getCommand("inventoryload").setExecutor(new InventoryLoad(this));
        this.getCommand("Immortal").setExecutor(new Immortal(this));
        this.getCommand("Friendschat").setExecutor(new FriendChat());
        this.getCommand("RequestTeleport").setExecutor(new RequestTeleport(this));
        this.getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new onDeathEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new onEntityDamage(), this);
        this.getServer().getPluginManager().registerEvents(new onPlayerDisconnect(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


        //this.getServer().getPluginManager().registerEvents(new onBlockBreak(this), this);


    public boolean mysqlEnabled() {
        if(!(getConfig().getString("data-storage-type").equalsIgnoreCase("mysql"))) {
            return false;
        } else {
            return true;
        }
    }
    public boolean mongoEnabled() {
        if(!(getConfig().getString("data-storage-type").equalsIgnoreCase("mongoDB")) || getConfig().getString("data-storage-type").equalsIgnoreCase("mongo") ) {
            return false;
        } else {
            return true;
        }
    }
    public boolean gsonEnabled() {
        if(!(getConfig().getString("data-storage-type").equalsIgnoreCase("gson"))) {
            return false;
        } else {
            return true;
        }
    }
    public boolean YAMLenabled() {
        if(!(mysqlEnabled()) && (mongoEnabled()) && (gsonEnabled())) { //avoids loading up config
            return true; //because if none of the above 3 are enabled then this will be enabled, dosent work if user puts a bad value in

        } else {
            return false;
        }
    }
    public void sendMessage(Player player, String msg) {
        player.sendMessage(getConfig().getString("messages-prefix") + msg);
    }
    public void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(getConfig().getString("messages-prefix") + msg);
    }
    public int UUIDorPlayer(String input) {
        if(input.length() < 17) {
            //player names have max char length off 16
            return 0;
        } else {
            //if larger then they are using uuids
            return 1;
        }
    }

}
