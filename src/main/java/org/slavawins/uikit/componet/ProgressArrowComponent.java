package org.slavawins.uikit.componet;

import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.MenuBase;

public class ProgressArrowComponent extends  ProgressBarComponent{


    public ProgressArrowComponent(MenuBase menuBase, int x, int y, Float val, Float maxVal) {
        super(menuBase, x, y, val, maxVal);

        itemsProgress.clear();
        itemsProgress.add(ReassetsGet.item("reassets/items/progressarrow/progress_arrow_0.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressarrow/progress_arrow_1.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressarrow/progress_arrow_2.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressarrow/progress_arrow_3.png"));
        itemsProgress.add(ReassetsGet.item("reassets/items/progressarrow/progress_arrow_4.png"));
    }
}
