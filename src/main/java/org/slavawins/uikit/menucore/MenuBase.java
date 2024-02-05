package org.slavawins.uikit.menucore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slavawins.uikit.Uikit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class MenuBase implements Listener {


    public static String MENU_META_KEY = "menuBase";
    public Player player;

    public String menuId = "";
    public int rows = 6;
    public String title = "nontitle";
    public List<BtnMenuCoreContract> listBtns = new ArrayList<>();
    public Inventory guiInventory;

    public boolean eventDisabled = false;
    public boolean isLockedAll = true;
    public boolean isChestMode = true;

    public final void setSize(int i) {
        this.rows = Math.min(i, 6);
    }


    public void navigateTo(MenuBase devMenu) {
        eventDisabled = true;
        clearButtons();

        guiInventory.clear();
        guiInventory = null;

        delete();

        devMenu.show(player);
    }

    /**
     * Установить бэкграунд для инвентаря. Центрированый контейнер
     *
     * @param back
     * @param text
     */
    public final void setBackground(String back, String text) {
        if (back == null) back = "";

        String val = ChatColor.WHITE + "❏" + back;
        if (text != null) val += text;
        setTitle(val);
    }

    public final void setBackgroundCenter(String back, String text) {
        if (back == null) back = "";

        String val = ChatColor.WHITE + "\uD83D\uDF96" + back;
        if (text != null) {
            for (int i = 0; i <= 1; i++) {
                val += "\uD83D\uDF96";
            }
            val += "❏" + ChatColor.DARK_GRAY + text;
        }
        setTitle(val);
    }

    public final void recreateInv() {
        if (guiInventory == null) return;
        Inventory guiInventoryNew = Bukkit.createInventory(null, rows * 9, title);
        guiInventoryNew.setContents(guiInventory.getContents());
        guiInventory.clear();
        Inventory old = guiInventory;
        guiInventory = guiInventoryNew;
        guiInventoryNew = null;

        player.closeInventory();
        player.openInventory(guiInventory);
    }

    public final void setTitle(String val) {
        this.title = val;

    }

    public final void setTitleUnicode(String val) {
        this.title = ChatColor.WHITE + "" + val;
    }

    public final void setLockedAll(boolean val) {
        this.isLockedAll = val;
    }


    public final int posToId(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > rows) {
            return -1;
        }
        int id = 9 * y - 9 + x - 1;
        return id;
    }

    public final int getX(int id) {
        if (id < 0 || id > rows * 9 - 1) {
            return -1;
        }
        int x = ((id) % 9) + 1;
        return x;
    }

    public final int getY(int id) {
        if (id < 0 || id > rows * 9 - 1) {
            return -1;
        }
        int y = (int) Math.ceil(id / 9) + 1;
        return y;
    }

    public final BtnMenuCoreContract addButton(int x, int y, Material mat, String name, String descr, Consumer<BtnMenuCoreContract> event) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(descr);

        meta.setLore(lore);

        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return addButtonItem(x, y, item, event, true);
    }

    public final BtnMenuCoreContract addButtonItem(int x, int y, ItemStack item, Consumer<BtnMenuCoreContract> event, boolean isLockedBtn) {


        BtnMenuCoreContract btn = new BtnMenuCoreContract();
        btn.x = x;
        btn.y = y;
        btn.item = item;
        btn.action = "_";
        btn.event = event;
        btn.id = posToId(x, y);
        btn.isLocked = isLockedBtn;
        btn.menuBase = this;
        this.listBtns.add(btn);
        return btn;
    }


    public final void init() {

        if (guiInventory != null) return;

        // Создаем новый инвентарь с заданным размером и заголовком

        guiInventory = Bukkit.createInventory(null, 9 * Math.min(6, rows), title);


        //System.out.println("guiInventory init size: " + (9 * rows));

    }


    public void clearButtons() {
        for (BtnMenuCoreContract btn : listBtns) {
            guiInventory.setItem(btn.id, null);
        }
    }

    public void renderButtons() {
        for (BtnMenuCoreContract btn : listBtns) {

            if (!btn.visible) {
                if (btn.item != null) {

                    int exist = findIdByItem(btn.item);
                    if (exist > -1) {
                        guiInventory.setItem(exist, null);
                    }
                }
                continue;
            }

            if (btn.id > rows * 9 || btn.id < 0) {
                System.out.println("Error item btn possition to " + " - " + btn.action + " in pos " + btn.id + " / x:y = " + btn.x + ":" + btn.y);
                continue;
            }
            if (btn.id > 54) {
                System.out.println(ChatColor.RED + "ERROR UIKIT SIZE INV MENU 54");
                continue;
            }
            guiInventory.setItem(btn.id, btn.item);
        }
    }

    public void onShow() {

    }

    public final void show(Player player) {
        this.player = player;

        init();


        onShow();
        renderButtons();


        menuId = UUID.randomUUID().toString();

        //System.out.println("id: " + menuId);

        PMetaHelper.setPlayerMetadata(player, "menuId", menuId);
        PMetaHelper.setPlayerMetadata(player, "menuClass", getClass().getSimpleName());
        PMetaHelper.setPlayerMetadata(player, MENU_META_KEY, "true");


        Uikit.getInstanse().getServer().getPluginManager().registerEvents(this, Uikit.getInstanse());


        // Открываем инвентарь для игрока
        player.openInventory(guiInventory);
    }


    private final void delete() {

        PMetaHelper.remove(player, MENU_META_KEY);

        //  player.sendMessage("closed");

        //PlayerInteractEvent.getHandlerList().unregister(this);
        HandlerList.unregisterAll(this);

        PMetaHelper.remove(player, "menuId");
        PMetaHelper.remove(player, "menuClass");
        PMetaHelper.remove(player, MENU_META_KEY);

        guiInventory = null;


    }

    public final BtnMenuCoreContract getBtn(int id) {
        for (BtnMenuCoreContract btn : listBtns) {
            if (btn.id == id) return btn;
        }
        return null;
    }

    public void onClickButton(BtnMenuCoreContract btn, ClickType clickType, ItemStack currentItemInMouse) {
        //  player.sendMessage("CBTN:" + btn.action);
    }

    public void onClickEmpty(int id, ClickType clickType, ItemStack currentItemInMouse) {
        //  player.sendMessage("CBTN to empty:" + id);
    }


    @org.bukkit.event.EventHandler
    public final void eventOnClick(InventoryClickEvent e) {


        if (guiInventory == null) return;

        Player player = (Player) e.getWhoClicked();
        if (!player.hasMetadata(MENU_META_KEY)) return;


        if (!PMetaHelper.get(player, "menuId").equalsIgnoreCase(menuId)) {
            //    System.out.println("is not my id: " + PMetaHelper.get(player, "menuId"));
            return;
        }


        if (isLockedAll && !isChestMode) e.setCancelled(true);


        Inventory clickedInventory = e.getClickedInventory();

        if (clickedInventory == null) return;

        if (!clickedInventory.equals(guiInventory)) {
            //  System.out.println("click in other inv");
            return;//клиенул в другом инвентаре
        }


        BtnMenuCoreContract btn = getBtn(e.getSlot());
        if (btn != null) {
            onClickButton(btn, e.getClick(), e.getCurrentItem());
            if (btn.event != null) {
                btn.event.accept(btn);
            }
            if (btn.isLocked || isLockedAll) e.setCancelled(true);

        } else {
            onClickEmpty(e.getSlot(), e.getClick(), e.getCurrentItem());
            if (isLockedAll) e.setCancelled(true);
        }

    }


    public void onCloseEvent() {

    }

    @org.bukkit.event.EventHandler
    public final void onCloseListner(InventoryCloseEvent e) {

        if (guiInventory == null) return;
        if (!e.getInventory().equals(guiInventory)) return;

        onCloseEvent();

        guiInventory.clear();
        guiInventory = null;

        delete();
    }

    public int findIdByItem(ItemStack itemStack) {
        for (int i = 0; i < guiInventory.getContents().length; i++) {

            ItemStack item = guiInventory.getContents()[i];
            if (item == null) continue;
            if (item == (itemStack)) return i;
        }
        return -1;
    }

    public final void onSetVisible(BtnMenuCoreContract b) {
        if (guiInventory == null) return;


        if (!b.visible) {
            int exist = findIdByItem(b.item);
            if (exist == -1) return;
            guiInventory.setItem(exist, null);
            return;
        }

        if (b.item != null) {

            int exist = findIdByItem(b.item);
            if (exist > -1) {
                guiInventory.setItem(exist, null);
            }
            guiInventory.setItem(posToId(b.x, b.y), b.item);

        }

    }

    public void setItemInButton(BtnMenuCoreContract b, ItemStack item) {
        guiInventory.remove(b.item);
        b.item = null;
        b.item = item;
        guiInventory.setItem(posToId(b.x, b.y), item);
    }
}
