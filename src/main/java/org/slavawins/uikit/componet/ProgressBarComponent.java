package org.slavawins.uikit.componet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;

public class ProgressBarComponent extends BaseComponent<Float> {


    public final Float valMax;
    private final BtnMenuCoreContract bar;

    public List<ItemStack> itemsProgress = new ArrayList<>();

    public ProgressBarComponent(MenuBase menuBase, int x, int y, Float val, Float maxVal) {
        super(menuBase, x, y, val);
        this.valMax = maxVal;

        bar = menuBase.addButton(x, y, null, "slot", "d", this::testClick);

        itemsProgress.add(ReassetsGet.item("reassets/items/progressbar/progress_black_0.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressbar/progress_black_1.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressbar/progress_black_2.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressbar/progress_black_3.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressbar/progress_black_4.png"));
    }

    private void testClick(BtnMenuCoreContract btnMenuCoreContract) {
        setVal(getVal() + 1);
    }

    @Override
    public void setVal(Float i) {
        i = Math.min(i, valMax);
        i = Math.max(i, 0);
        super.setVal(i);
        Render();
    }

    @Override
    public void Render() {

        float res = ((float) getVal() / valMax) * (float) itemsProgress.size();

        int iconNumber = Math.round(res) - 1;
        iconNumber = Math.min(iconNumber, itemsProgress.size() - 1);
        iconNumber = Math.max(iconNumber, 0);

        bar.setItem(itemsProgress.get(iconNumber));
    }

    public void increment(float v) {
        setVal(getVal() + v);
    }
}
