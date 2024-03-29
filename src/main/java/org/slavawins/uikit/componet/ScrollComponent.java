package org.slavawins.uikit.componet;

import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ScrollComponent extends BaseComponent<Number> {


    private final int height;
    public Function<BtnMenuCoreContract, Boolean> filter = (BtnMenuCoreContract btn) -> {
        return true;
    };


    BtnMenuCoreContract up;
    BtnMenuCoreContract down;


    public ScrollComponent(MenuBase menuBase, int y, int height) {
        super(menuBase, 0, y, 1);
        this.height = height;
        current = 1;

        up = menuBase.addButtonItem(9, y, CustomMaterial.UIKIT_ITEMS_BUTTON_LEFT.toItem(), this::UpClick, true);
        down = menuBase.addButtonItem(9, y + height, CustomMaterial.UIKIT_ITEMS_BUTTON_RIGHT.toItem(), this::DownClick, true);
    }

    private void UpClick(BtnMenuCoreContract btnMenuCoreContract) {

        plus(-1);
        Render();
    }

    private void DownClick(BtnMenuCoreContract btnMenuCoreContract) {

        plus(1);
        Render();
    }


    List<BtnMenuCoreContract> filtredContecnt = new ArrayList<>();
    List<BtnMenuCoreContract> contents = new ArrayList<>();

    @Override
    public void Render() {
        if( menuBase.guiInventory==null)return;

        // Number current - текущая страница
        int pageSize = 8 * (1 + height);


        filtredContecnt.clear();

        for (BtnMenuCoreContract btn : contents) {
            if (filter.apply(btn)) {
                filtredContecnt.add(btn);
            }
        }


        int start = (current.intValue() -1) * pageSize; // начальная позиция элементов текущей страницы // 0
        // int end = Math.min(start + pageSize, contents.size()); // конечная позиция элементов текущей страницы //7


        int number = start;


        for (int _y = y; _y <= y + height; _y++) {
            for (int _x = 1; _x <= 8; _x++) {


                if (filtredContecnt.size() < number + 1) { //3 < 0 + 2 + 1
                    menuBase.guiInventory.setItem(menuBase.posToId(_x, _y), null);
                    continue;
                }

                BtnMenuCoreContract item = filtredContecnt.get(number); // 0+2+-1
                item.x = _x;
                item.y = _y;
                item.updateId();
                item.setVisible(true);

                number++;

            }
        }
        // menuBase.RenderButtons();
    }

    @Override
    public void setVal(Number i) {

        if (i.intValue() < 1) return;
        if (i.intValue() > getPageCount()) return;

        super.setVal(i);
    }

    private int getPageCount() {
        return (int) Math.ceil((float) filtredContecnt.size() / (8 * height));
    }


    public void plus(Number i) {
        int val = current.intValue();
        val += i.intValue();

        setVal(val);
    }

    public void add(BtnMenuCoreContract btnMenuCoreContract) {
        btnMenuCoreContract.x = 1;
        btnMenuCoreContract.y = y;
        btnMenuCoreContract.visible = false;
        this.contents.add(btnMenuCoreContract);
    }
}
