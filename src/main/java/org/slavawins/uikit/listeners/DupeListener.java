package org.slavawins.uikit.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.slavawins.uikit.helpers.DupeProtection;

public final class DupeListener implements Listener {

    @EventHandler
    public void drop(PlayerDropItemEvent event) {


        if (DupeProtection.check(event.getItemDrop().getItemStack())) {

            event.getItemDrop().remove();
        }

    }
/*
    @EventHandler
    public void join(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        if (!player.getName().equalsIgnoreCase("wins")) return;

        ItemStack stack = new ItemStack(Material.DIAMOND, 2);
        DupeProtection.addItem(stack);
        player.getInventory().addItem(stack);

        stack = new ItemStack(Material.IRON_BOOTS, 1);
        DupeProtection.addItem(stack);
        player.getInventory().addItem(stack);

        stack = new ItemStack(Material.BOW, 1);
        DupeProtection.addItem(stack);
        player.getInventory().addItem(stack);
        player.getInventory().addItem(stack);

        stack = new ItemStack(Material.COPPER_INGOT, 2);
        DupeProtection.addItem(stack);
        player.getInventory().addItem(stack);

    }
*/
    @EventHandler(priority = EventPriority.LOW)
    public void xz(InventoryClickEvent e) {

        if(e.getClickedInventory()==null)return;
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) {

            if (DupeProtection.check(e.getCurrentItem())) {


                e.setCancelled(true);

                e.setCurrentItem(null);
                DupeProtection.scanPlayer((Player) e.getWhoClicked());
                return;
            }
        }


        if (e.getClickedInventory().getType() == InventoryType.PLAYER) {

            if (DupeProtection.check(e.getCursor())) {

                e.setCancelled(true);
                e.setCursor(null);
                DupeProtection.scanPlayer((Player) e.getWhoClicked());
                return;
            }
        }
    }



    @EventHandler(priority = EventPriority.LOW)
    public void eventOnClick(InventoryMoveItemEvent e) {

        if (e.getInitiator() != null && e.getInitiator().getType() == InventoryType.PLAYER) {
            DupeProtection.scaniventory(e.getInitiator());

        }

        if (DupeProtection.check(e.getItem())) {
            e.getItem().setAmount(0);

        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void open(InventoryOpenEvent event) {

        DupeProtection.scaniventory(event.getPlayer().getInventory());

    }

    @org.bukkit.event.EventHandler
    public final void eventOnClick(InventoryPickupItemEvent e) {
        if (DupeProtection.check(e.getItem().getItemStack())) {
            e.getItem().remove();
        }
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);

        if (DupeProtection.check(item)) {
            item.setAmount(0);
            player.getInventory().remove(item);
        }
        // Ваш код для обработки добавления предмета в инвентарь игрока
    }
}