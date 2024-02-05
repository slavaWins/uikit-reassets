package org.slavawins.uikit.comonet;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;

public class BaseComponent<T> {

     final int y;
     final int x;
    MenuBase menuBase;
    T current;

    public BaseComponent(MenuBase menuBase, int x, int y, T val) {
        this.menuBase = menuBase;
        this.x = x;
        this.y = y;
        this.current = val;
        Render();
    }

    public void Render() {

    }


    public void setVal(T i) {

        current = i;
    }

    public T getVal() {
        return current;
    }
}
