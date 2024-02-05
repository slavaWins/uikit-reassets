package org.slavawins.uikit.listeners;

import org.slavawins.uikit.comonet.CheckboxComponent;
import org.slavawins.uikit.comonet.NumberComponent;
import org.slavawins.uikit.menucore.MenuBase;

public class DevMenu extends MenuBase {
    NumberComponent numberComponent;

    @Override
    public void onShow() {
        super.onShow();

        setTitle("text \n Text2 \n xx");
        numberComponent = new NumberComponent(this, 1, 1, 22);


        CheckboxComponent.AddButtonCheckBox(this, 1, 2, true, "On", "Off", this::Change);
    }

    void Change(Boolean val) {

        numberComponent.plus(1);

    }
}
