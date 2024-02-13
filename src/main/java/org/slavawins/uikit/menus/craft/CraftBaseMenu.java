package org.slavawins.uikit.menus.craft;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.slavawins.uikit.Uikit;
import org.slavawins.uikit.componet.*;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

public class CraftBaseMenu extends MenuBase {

    public String soundStart = "minecraft:entity.iron_golem.damage";
    public String soundResult = "minecraft:entity.iron_golem.repair";

    public CraftBaseMenu() {

        isLockedPlayerInv = false;

        isChestMode = false;
        isLockedAll = true;
    }

    private BukkitTask task;

    /**
     * Прогресс бар
     */
    public ProgressBarComponent progressBar;

    /**
     * Слот с результатом крафта
     */
    public SlotComponent slotResult;

    /**
     * Слот в который ложится основной предмет
     */
    public SlotComponent slotInput;


    /**
     * Запущен ли сейчас процесс крафта
     */
    public boolean isRun = false;

    /**
     * Время крафтинга рецепта
     */
    public float timeReceptWait = 10;


    /**
     * Метод для запуска процесса крафтинга.
     *
     * @param seconds Время в секундах, необходимое для завершения процесса.
     */
    public final void run(float seconds) {
        if (isRun) return;

        task = new CraftBlockerTask(this).runTaskTimer(Uikit.getInstanse(), 0, Math.round(seconds / 20f * 10f));    // 20 тикс * 10 сек
        isRun = true;
    }

    /**
     * Если игрок чета поменял или тронул
     * Метод для отмены процесса крафтинга пользователем.
     */
    public final void onUserCanselCraftingProcces() {
        if (!isRun) return;
        progressBar.setVal(0f);
        if (task == null) return;
        task.cancel();
        task = null;
    }


    /**
     * Фильтр для вхоядщих в инвентарь даннных, в основной слот
     *
     * @param itemStack
     * @return
     */
    public Boolean filterInputSlot(ItemStack itemStack) {
        onUserCanselCraftingProcces();
        return true;
    }

    /**
     * Вызывается когда крафтинг закончен, по сути мтеод выдачи предмета игроку
     */
    public void onResult() {


    }

    public final void PlaySound(String sound) {
        if (sound.isEmpty()) return;
        player.getLocation().getWorld().playSound(player, sound, 1, 1f);
    }

    /**
     * Метод для обновления состояния процесса крафтинга. Работает автоматически.
     */
    public final void Tick() {
        // System.out.println("TICK");
        progressBar.increment(1f);
        if (progressBar.getVal() >= progressBar.valMax) {
            task.cancel();
            task = null;
            progressBar.setVal(0f);
            isRun = false;
            onResult();

            PlaySound(soundResult);
        }
    }

    /**
     * Метод для проверки возможности запуска процесса крафтинга. Все ли слоты правильно заполнены, влидация
     *
     * @return Возвращает true, если крафтинг можно запустить, иначе false.
     */
    public boolean validateCanStartCrafting() {
        return true;
    }


    /**
     * Метод для обработки нажатия на кнопку крафтинга.
     *
     * @param btnMenuCoreContract Кнопка, на которую произошло нажатие.
     */
    public final void CraftClick(BtnMenuCoreContract btnMenuCoreContract) {

        if (slotInput.isEmpty()) return;
        if (!validateCanStartCrafting()) return;

        onUserCanselCraftingProcces();
        if (isRun) return;

        run(timeReceptWait);
        isRun = true;
        PlaySound(soundStart);
        // System.out.println("CRAFT START");
    }

    @Override
    public void onCloseEvent() {

        onUserCanselCraftingProcces();
    }
}
