package org.slavawins.uikit.componet;

import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;

public class TabComponent extends BaseComponent<Number> {


    BtnMenuCoreContract left;
    BtnMenuCoreContract right;


    public TabComponent(MenuBase menuBase, int y) {
        super(menuBase, 0, y, 1);

        left = menuBase.addButtonItem(1, y, CustomMaterial.UIKIT_ITEMS_BUTTON_LEFT.toItem(), this::Left, true);
        right = menuBase.addButtonItem(9, y, CustomMaterial.UIKIT_ITEMS_BUTTON_RIGHT.toItem(), this::Right, true);
    }

    private void Left(BtnMenuCoreContract btnMenuCoreContract) {

        plus(-1);
        Render();
    }

    private void Right(BtnMenuCoreContract btnMenuCoreContract) {
        System.out.println("right");
        plus(1);
        Render();
    }


    List<BtnMenuCoreContract> contents = new ArrayList<>();

    @Override
    public void Render() {

        // Number current - текущая страница
        int pageSize = 7;

        int start = (current.intValue() - 1) * pageSize; // начальная позиция элементов текущей страницы // 0
        int end = Math.min(start + pageSize, contents.size()); // конечная позиция элементов текущей страницы //7



        for (int i = 2; i <= 8; i++) {



            if (contents.size() < (start + i - 2)+1) { //3 < 0 + 2 + 1
                menuBase.guiInventory.setItem(menuBase.posToId(i,y), null);
                continue;
            }

            BtnMenuCoreContract item = contents.get(start + i - 2); // 0+2+-1
            item.x = i;
            item.y = y;
            item.updateId();
            item.setVisible(true);
        }
        //  menuBase.RenderButtons();
    }

    @Override
    public void setVal(Number i) {

        if (i.intValue() < 1) return;
        if (i.intValue() > getPageCount()) return;

        super.setVal(i);
    }

    private int getPageCount() {
        return (int) Math.ceil((float) contents.size() / 7f);
    }


    public void plus(Number i) {
        int val = current.intValue();
        val += i.intValue();

        setVal(val);
    }

    public void add(BtnMenuCoreContract btnMenuCoreContract) {
        btnMenuCoreContract.x = 2;
        btnMenuCoreContract.y = y;
        btnMenuCoreContract.visible = false;
        this.contents.add(btnMenuCoreContract);
    }
}
