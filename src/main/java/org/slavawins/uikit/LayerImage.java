package org.slavawins.uikit;

import org.bukkit.ChatColor;

public class LayerImage {


    public String res = ChatColor.WHITE+"" ;
    public String build() {
        res+="           ";
        res+=ChatColor.DARK_GRAY;
        return  res;
    }


    public void addUnicode(String test) {
        res+=test;
    }

    public void left48() {
        res += "\uD83D\uDF96";
    }

    public void moveTostartLeft() {
        for(int i=1;i<=4;i++)left48();
      //  res += "❏";
        //res += "❏";
        res += "❏";
    }
}
