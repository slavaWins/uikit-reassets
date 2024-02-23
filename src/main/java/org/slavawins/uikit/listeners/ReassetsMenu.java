package org.slavawins.uikit.listeners;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.contracts.CategoryEnum;
import org.slavawins.reassets.contracts.ItemImageContract;
import org.slavawins.reassets.controllers.RegisterImageController;
import org.slavawins.reassets.handles.IndexingHandle;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.textrender.service.generator.integration.TRRegister;
import org.slavawins.uikit.LayerImage;
import org.slavawins.uikit.componet.*;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.Map;

public class ReassetsMenu extends MenuBase {

    ScrollComponent scrollComponent;

    public ReassetsMenu() {
        setSize(6);

        LayerImage layer = new LayerImage();
        layer.left48();
        layer.addUnicode(ReassetsGet.image("/reassets/ui/window/scroll_tabs.png"));
        layer.moveTostartLeft();
        setTitle(layer.build() + " ");

    }

    String currentTab = "";

    @Override
    public void onShow() {
        super.onShow();

        TabComponent tab = new TabComponent(this, 1);
        for (Map.Entry<String, ItemStack> cat : IndexingHandle.getPluginsPrefixs().entrySet()) {
            BtnMenuCoreContract btn = addButtonItem(1, 1, cat.getValue(), null, true);
            btn.action = "tab";
            btn.setName("Плагин " + cat.getKey());
            btn.customData = cat.getKey();
            tab.add(btn);
        }
        tab.Render();


        scrollComponent = new ScrollComponent(this, 2, 4);
        for (ItemImageContract img : RegisterImageController.images) {
            if (!(img.categoryTyep == CategoryEnum.items || img.categoryTyep == CategoryEnum.models)) continue;

            BtnMenuCoreContract btn = addButtonItem(1, 1, ReassetsGet.item(img.enumName), null, true);
            btn.action = "item";
            btn.customData = img.enumName;
            scrollComponent.add(btn);
        }
        scrollComponent.filter = this::Filter;
        scrollComponent.Render();

        // CheckboxComponent.AddToggleCheckBox(this, 1, 6, true, "On", "Off", this::Change);
    }


    public boolean Filter(BtnMenuCoreContract btn) {
        return btn.customData.startsWith(currentTab);
    }

    @Override
    public void onClickButton(BtnMenuCoreContract btn, ClickType clickType, ItemStack currentItemInMouse) {

        if (btn.action.equalsIgnoreCase("item")) {
            if(currentItemInMouse==null)return;
            player.getInventory().addItem(currentItemInMouse.clone());
        }

        if (btn.action.equalsIgnoreCase("tab")) {
            // navigateTo(new DevMenu());
            currentTab = btn.customData;
            scrollComponent.setVal(1);
            scrollComponent.Render();
        }
    }


}
