package org.slavawins.uikit;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.IEnumString;
import org.slavawins.reassets.integration.ReassetsGet;

public enum CustomMaterial implements IEnumString {


    UIKIT_ITEMS_BUTTON_LEFT,
    UIKIT_ITEMS_BUTTON_RIGHT,
    UIKIT_ITEMS_BUTTON_CHECKBOX_OFF,
    UIKIT_ITEMS_BUTTON_CHECKBOX_ON,
    UIKIT_ITEMS_BUTTON_TOGLE_OFF,
    UIKIT_ITEMS_BUTTON_TOGLE_ON,
    UIKIT_ITEMS_ICON_ICON_CARD,
    UIKIT_ITEMS_ICON_ICON_CIRCLE,
    UIKIT_ITEMS_ICON_ICON_CLOCK,
    UIKIT_ITEMS_ICON_ICON_CLOSE,
    UIKIT_ITEMS_ICON_ICON_COG,
    UIKIT_ITEMS_ICON_ICON_CROP,
    UIKIT_ITEMS_ICON_ICON_DIALOG,
    UIKIT_ITEMS_ICON_ICON_DIALOG_ALT,
    UIKIT_ITEMS_ICON_ICON_FIRE,
    UIKIT_ITEMS_ICON_ICON_GAME,
    UIKIT_ITEMS_ICON_ICON_INFO,
    UIKIT_ITEMS_ICON_ICON_INVALID,
    UIKIT_ITEMS_ICON_ICON_MESS,
    UIKIT_ITEMS_ICON_ICON_PLUS,
    UIKIT_ITEMS_ICON_ICON_RENAME,
    UIKIT_ITEMS_ICON_ICON_USER,
    UIKIT_ITEMS_ICON_ICON_VALID;

    public ItemStack toItem() {
        return ReassetsGet.item(toString());
    }

}