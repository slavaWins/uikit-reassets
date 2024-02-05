package org.slavawins.uikit.comonet;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.function.Consumer;

public class CheckboxComponent {


    public static void AddCustomButtonCheckBox(MenuBase menuBase, int x, int y, boolean current, String on, String off,
                                               Consumer<Boolean> onChange,
                                               String iconOn,
                                               String iconOff
    ) {


        ItemStack item = null;

        if (current) {
            item = ReassetsGet.item(iconOn, on);
        } else {
            item = ReassetsGet.item(iconOff, off);
        }

        BtnMenuCoreContract btn = menuBase.AddButtonItem(x, y, item,  null, true);

        btn.event = (BtnMenuCoreContract b) -> {
            if (b.customData.equalsIgnoreCase("1")) {
                b.setItem(ReassetsGet.item(iconOff));
                b.customData = "0";
            } else {
                b.customData = "1";
                b.setItem(ReassetsGet.item(iconOn));
            }
            onChange.accept(b.customData.equalsIgnoreCase("1"));
        };

        btn.customData = "0";
        if (current) {
            btn.customData = "1";
        }
    }

    public static void AddButtonCheckBox(MenuBase menuBase, int x, int y, boolean current, String on, String off, Consumer<Boolean> onChange) {
        AddCustomButtonCheckBox(menuBase, x, y, current, on, off, onChange, CustomMaterial.UIKIT_ITEMS_BUTTON_TOGLE_ON.toString(), CustomMaterial.UIKIT_ITEMS_BUTTON_TOGLE_OFF.toString());
    }

    public static void AddToggleCheckBox(MenuBase menuBase, int x, int y, boolean current, String on, String off, Consumer<Boolean> onChange) {
        AddCustomButtonCheckBox(menuBase, x, y, current, on, off, onChange, CustomMaterial.UIKIT_ITEMS_BUTTON_CHECKBOX_ON.toString(), CustomMaterial.UIKIT_ITEMS_BUTTON_CHECKBOX_OFF.toString());
    }

}
