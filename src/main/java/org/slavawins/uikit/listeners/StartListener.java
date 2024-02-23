package org.slavawins.uikit.listeners;


import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slavawins.reassets.integration.ReassetsItemCreateEvent;

public final class StartListener implements Listener {

    @EventHandler
    public void onFrameClick(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        // new CraftBaseMenu().show(player);

    }
/*
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryCreativeEvent event) {

        System.out.println("x0");
        System.out.println(event);

        System.out.println("x1");
        if (event.getInventory() == null) return;

        System.out.println("x12");
        Player player = (Player) event.getWhoClicked();

        System.out.println("x23");
        if (player.getGameMode() != GameMode.CREATIVE) return;
        System.out.println("x4");
        if (!player.isOp()) return;
        System.out.println("x5");
        if (!player.isSneaking()) return;
        System.out.println(event.getInventory().getType());
        if (event.getInventory().getType() != InventoryType.CREATIVE) return;
        System.out.println("x7");

        event.setCancelled(true);

        new ReassetsMenu().show(player);


    }
    */


}