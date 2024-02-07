package org.slavawins.uikit.menus.craft;

import org.bukkit.scheduler.BukkitRunnable;

public class CraftBlockerTask extends BukkitRunnable {
    private final CraftMenu caseMenu;

    public CraftBlockerTask(CraftMenu caseMenu) {
        this.caseMenu = caseMenu;
    }

    @Override
    public void run() {
        caseMenu.Tick();

    }
}
