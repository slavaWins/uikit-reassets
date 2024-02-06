package org.slavawins.uikit.listeners;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.proplugin.Fastcommand;

public class ReassetsUiComandListener extends Fastcommand {

    public ReassetsUiComandListener(String rootCommand) {
        super(rootCommand);

        onlyOp = true;

        CommandElemet com = new CommandElemet();
        com.subcommond = "ui";
        com.descrip = "Посмотреть все предметы в UI интерфейсе";
        com.event = this::ShowItemInUi;
        commands.add(com);

        com = new CommandElemet();
        com.subcommond = "devmenu";
        com.descrip = "Девменю для разработчика";
        com.event = this::devmenu;
        com.arguments.add("title");
        commands.add(com);

    }


    public void devmenu(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            QuestDevMenu menu = new QuestDevMenu();
          //  menu.setBackgroundCenter(ReassetsGet.image("/reassets/ui/window/empty.png"), args[0]);
            //menu.setTitle(args[0]);
            menu.show(((Player) sender));
        }
    }

    public void ShowItemInUi(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            new ReassetsMenu().show(((Player) sender));
        }
    }
}