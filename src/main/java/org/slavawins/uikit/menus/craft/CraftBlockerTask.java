package org.slavawins.uikit.menus.craft;

import org.bukkit.scheduler.BukkitRunnable;

public class CraftBlockerTask extends BukkitRunnable {
    private final CraftBaseMenu caseMenu;

    public CraftBlockerTask(CraftBaseMenu caseMenu) {
        this.caseMenu = caseMenu;
    }

    @Override
    public void run() {
        caseMenu.Tick();

    }
}
