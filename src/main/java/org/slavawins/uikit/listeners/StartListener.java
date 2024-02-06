package org.slavawins.uikit.listeners;


import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.md_5.bungee.api.chat.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.reassets.integration.ReassetsItemCreateEvent;
import org.slavawins.textrender.service.generator.contract.TextFileContract;
import org.slavawins.textrender.service.generator.integration.TRRegister;

public final class StartListener implements Listener {

    @EventHandler
    public void onFrameClick(PlayerJoinEvent event) {

        Player player = event.getPlayer();



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