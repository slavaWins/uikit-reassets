package org.slavawins.uikit.componet;

import org.slavawins.uikit.menucore.MenuBase;

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
        //Render();
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
