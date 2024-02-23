package org.slavawins.uikit.componet.interfaces;

import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.componet.ProgressBarComponent;
import org.slavawins.uikit.menucore.MenuBase;

public class ProgressPressComponent extends ProgressBarComponent {


    public ProgressPressComponent(MenuBase menuBase, int x, int y, Float val, Float maxVal) {
        super(menuBase, x, y, val, maxVal);

        itemsProgress.clear();
        for (int i = 0; i <= 4; i++) {
            itemsProgress.add(ReassetsGet.item("reassets/items/progresspress/bar" + i + ".png"));
        }
    }
}
