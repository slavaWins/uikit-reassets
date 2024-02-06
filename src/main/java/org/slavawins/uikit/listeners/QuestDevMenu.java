package org.slavawins.uikit.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.textrender.service.generator.integration.TRRegister;
import org.slavawins.uikit.LayerImage;
import org.slavawins.uikit.componet.CheckboxComponent;
import org.slavawins.uikit.componet.NumberBoardComponent;
import org.slavawins.uikit.componet.NumberComponent;
import org.slavawins.uikit.componet.TabComponent;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.awt.*;

public class QuestDevMenu extends MenuBase {
    NumberBoardComponent numberBoardComponent;

    TabComponent tab;
    private NumberComponent numberComponent;


    public QuestDevMenu() {

        LayerImage layer = new LayerImage();
        layer.left48();
        layer.addUnicode(ReassetsGet.image("/reassets/ui/window/empty.png"));
        layer.moveTostartLeft();
        layer.addUnicode( TRRegister.GetAsUnicode("test"));
        layer.moveTostartLeft();

        setTitle( layer.build() +  "UI KIT");
        //setBackgroundCenter(ReassetsGet.image("/reassets/ui/window/empty.png") + "\uD83D\uDF96\uD83D\uDF96\uD83D\uDF96\uD83D\uDF96\uD83D\uDF96\uD83D\uDF96 ❏❏❏❏❏❏❏❏  " + TRRegister.GetAsUnicode("test"), "UI KIT");

      //  setBackgroundCenter(  TRRegister.GetAsUnicode("test"), "UI KIT");
    }

    @Override
    public void onShow() {
        super.onShow();


        CheckboxComponent.AddToggleCheckBox(this, 2, 6, true, "On", "Off", this::Change);
    }

    void Change(Boolean val) {


    }
}
