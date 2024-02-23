package org.slavawins.uikit.menus.craft.bases;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsCheck;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.componet.ButtonBackComponent;
import org.slavawins.uikit.componet.ProgressArrowComponent;
import org.slavawins.uikit.componet.SlotComponent;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menus.craft.CraftBaseMenu;
import org.slavawins.uikit.menus.craft.contracts.BiReceptConverter;
import org.slavawins.uikit.menus.craft.contracts.SingleReceptConverver;

import java.util.ArrayList;
import java.util.List;

public class CombinatorConverterCraftMenu extends CraftBaseMenu {

    public  BtnMenuCoreContract fromPreview;
    public  BtnMenuCoreContract toPreview;


    public List<BiReceptConverter> recepts = new ArrayList<>();

  public   int selectRecept = 0;


  public void  drawNavigation(int x, int y){
      addButtonItem(x, y, CustomMaterial.UIKIT_ITEMS_BUTTON_LEFT.toItem(), this::PrevRecept, true);
      fromPreview = addButtonItem(x+1, y, CustomMaterial.UIKIT_ITEMS_ICON_ICON_CROP.toItem(), null, true);
      addButtonItem(x+2, y, CustomMaterial.UIKIT_ITEMS_ICON_ICON_ARROW_RIGHT.toItem(), null, true);
      toPreview = addButtonItem(x+3, y, CustomMaterial.UIKIT_ITEMS_ICON_ICON_CROP.toItem(), null, true);
      addButtonItem(x+4, y, CustomMaterial.UIKIT_ITEMS_BUTTON_RIGHT.toItem(), this::NextRecept, true);

  }
  /*
    @Override
    public void onShow() {

        timeReceptWait = 6;

        soundStart = "guns.craft.stanok_start";
        soundResult = "guns.craft.stanok_result";


       drawNavigation(3,2);

        new ButtonBackComponent(this, 6, 6, 3, this::CraftClick);
        slotInput = new SlotComponent(this, 2, 3, this::filterInputSlot);


        slotResult = new SlotComponent(this, 6, 3, null);

        progressBar = new ProgressArrowComponent(this, 4, 3, 0f, 10f);
        progressBar.setVal(1f);

        SelectorNext(1);
    }
*/
      void NextRecept(BtnMenuCoreContract btnMenuCoreContract) {
        SelectorNext(1);
    }
      void PrevRecept(BtnMenuCoreContract btnMenuCoreContract) {
        SelectorNext(-1);
    }

    public void SelectorNext(int spin) {
        onUserCanselCraftingProcces();

        selectRecept += spin;
        if (selectRecept > recepts.size() - 1) selectRecept = 0;
        if (selectRecept < 0) selectRecept = recepts.size() - 1;

        BiReceptConverter recrpt = recepts.get(selectRecept);


        fromPreview.setItem(ReassetsGet.item(recrpt.from.id, recrpt.from.amount));
        toPreview.setItem(ReassetsGet.item(recrpt.to.id, recrpt.to.amount));

    }


    @Override
    public boolean validateCanStartCrafting() {
        BiReceptConverter recrpt = recepts.get(selectRecept);


        if (!slotResult.isEmpty()) {
            if (!ReassetsCheck.isReasset(slotResult.slot.item).equalsIgnoreCase(recrpt.to.id)) {
                return false;
            }
        }

        if (!ReassetsCheck.isReasset(slotInput.slot.item).equalsIgnoreCase(recrpt.from.id)) return false;
        if (slotInput.getAmount() < recrpt.from.amount) return false;
        if (slotResult.getAmount() > 64 - recrpt.to.amount) return false;


        return true;
    }


    @Override
    public void onResult() {

        BiReceptConverter recrpt = recepts.get(selectRecept);

        if (slotInput.getAmount() < recrpt.from.amount) {
            return;
        }


        if (slotResult.isEmpty()) {
            slotResult.slot.setItem(ReassetsGet.item(recrpt.to.id, recrpt.to.amount));
        } else if (!ReassetsCheck.isReasset(slotResult.slot.item).equalsIgnoreCase(recrpt.to.id)) {
            return;
        } else {
            slotResult.addAmount(recrpt.to.amount);
        }

        slotResult.updatePackage();
        renderButtons();


        slotInput.take(recrpt.from.amount);

        if (slotInput.getAmount() >= recrpt.from.amount) {
            CraftClick(null);
        }

    }

    @Override
    public Boolean filterInputSlot(ItemStack itemStack) {
        super.filterInputSlot(itemStack);


        String reasetItemId = ReassetsCheck.isReasset(itemStack);
        if (reasetItemId == null) return false;


        BiReceptConverter recrpt = recepts.get(selectRecept);


        if (!reasetItemId.equalsIgnoreCase(recrpt.from.id)) {
            return false;
        }

        return true;
    }


}
