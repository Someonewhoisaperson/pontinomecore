package dev.pontinome.yeetcore.Events;

import dev.pontinome.yeetcore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChatEvent {

    Main plugin;

    public onChatEvent(Main pl) {
        plugin = pl;
    }

    @EventHandler
    public void onChatEvent (AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(message.startsWith("#")) {
            //do thing
        }
    }

}
