package org.slavawins.uikit.helpers;

public class SimulateOverDose {

    public static void Hard(){
        try {
            Thread.sleep(800); // задержка в 0.5 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
