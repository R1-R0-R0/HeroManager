package model.gui;

import model.job.Job;
import view.CharacterView;
import view.HpPopUpView;

public class CharacterModel {

    private static CharacterModel instance;

    public CharacterModel(Job character) {
        instance = this;

        new CharacterView();
    }

    /**
     * Gets HP of current Job and send it to a new pop-up to update it
     */
    public void hpBarOnClickEvent() {
        new HpPopUpView(100);
        CharacterView.getInstance().blockWindow();
    }

    /**
     * Result of new hp pop-up updater
     * @param newHP New HP to set
     */
    public void hpBarOnClickEventDone(int newHP) {
        CharacterView.getInstance().unblockWindow();
    }

    /**
     * When new hp pop-up updater canceled
     */
    public void hpBarOnClickEventCancel() {
        CharacterView.getInstance().unblockWindow();
    }

    public void openDiceWindow() {
        new DiceModel();
    }

    public static CharacterModel getInstance() {
        return instance;
    }
}
