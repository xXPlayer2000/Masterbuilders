package me.devcode.masterbuilders.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.devcode.masterbuilders.Masterbuilders;

public class JoinListener implements Listener{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Masterbuilders.getInstance().getPlayerUtils().addPlayer(e.getPlayer());
    }

}
