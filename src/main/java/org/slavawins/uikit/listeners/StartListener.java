package org.slavawins.uikit.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class StartListener implements Listener {

    @EventHandler
    public void onFrameClick(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        new DevMenu().show(player);
    }


}