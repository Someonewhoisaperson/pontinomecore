package dev.pontinome.yeetcore.Events;

import dev.pontinome.yeetcore.Datahandling.getValue;
import dev.pontinome.yeetcore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class onEntityDamage implements Listener {



    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            double thing = player.getHealth() - event.getDamage();
            player.sendMessage(String.valueOf(thing));
            if(getValue.getYAMLBoolvalue("Settings.Immortal", player.getUniqueId())){
                if(player.getHealth() - event.getDamage() < 0) {
                    event.setCancelled(true);
                    player.sendMessage("You would have died but you are immortal");
                }
            }
        } else {
            //idk probs add the villager thing in
        }

    }

}
