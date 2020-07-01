package dev.pontinome.yeetcore.Events;

import dev.pontinome.yeetcore.Datahandling.UpdateValue;
import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeathEvent implements Listener {
    Main plugin;

    public onDeathEvent(Main pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(getValue.getYAMLBoolvalue("Settings.CountDeaths", player.getUniqueId())) {
            plugin.getLogger().info(player.getName() + "Died so death counter was incremented");
            UpdateValue.updateYAML("Stats.Deaths", getValue.getYAMLIntvalue("Stats.Deaths", player.getUniqueId()) + 1 , player.getUniqueId());
        } else {
            plugin.getLogger().info(player.getName() + " Died but death was not counted due to settings UUID:   " + player.getUniqueId());
        }
    }


}
