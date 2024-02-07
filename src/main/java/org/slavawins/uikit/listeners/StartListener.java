package org.slavawins.uikit.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slavawins.reassets.integration.ReassetsItemCreateEvent;
import org.slavawins.uikit.menus.craft.CraftMenu;

public final class StartListener implements Listener {

    @EventHandler
    public void onFrameClick(PlayerJoinEvent event) {

        Player player = event.getPlayer();
       // new CraftMenu().show(player);


    }

    @EventHandler
    public void onItemCr(ReassetsItemCreateEvent event) {

/*
        System.out.println("ADD ITEM");
        ItemMeta meta =  event.getItem().getItemMeta();
        meta.setDisplayName("XXX");
        event.getItem().setItemMeta(meta);
*/

    }


}