package model.gui;

import controller.LevelUpController;
import javafx.stage.Stage;
import model.Characteristics;
import model.job.Job;
import model.job.JobSkill;
import model.spell.Spell;
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;
import view.CharacterCreatorView;
import view.CharacterView;
import view.LevelUpView;

import javax.swing.text.html.ListView;
import java.util.Arrays;
import java.util.List;

public class LevelUpModel implements Model {

    private static LevelUpModel instance;

    public LevelUpModel(Stage owner) {
        instance = this;
        new LevelUpView(owner);

        LevelUpView.getInstance().setJobSkillsListView(Arrays.asList(JobSkill.values()));
        LevelUpView.getInstance().setSpellsListView(CharacterModel.getInstance().getCharacter().getSpellInventory()); // TODO Set up from db
    }

    public void updateCharacterAndContinue() {
        Job character = CharacterModel.getInstance().getCharacter();
        character.getSkills().clear();
        character.getSpellInventory().clear();
        character.levelUp();

        List<JobSkillItem> jobSkills = LevelUpController.getInstance().skillsListView.getItems();
        for (JobSkillItem jobSkill : jobSkills) {
            if (jobSkill.isSelected())
                character.getSkills().add(jobSkill.getSkill());
        }

        List<SpellItem> spells = LevelUpController.getInstance().spellsListView.getItems();
        for (SpellItem spell : spells) {
            if (spell.isSelected())
                character.getSpellInventory().add(spell.getSpell());
        }

        character.setHealthPoints(character.getMaxHp());

        LevelUpView.getInstance().getStage().close();
        CharacterView.getInstance().refreshView();
    }

    public void cancelLevelUp() {
        LevelUpView.getInstance().getStage().close();
    }

    /**
     * Called when one of statistics spinner is updated, to updates character in model
     *
     * @param characteristic affected statistic
     * @param oldValue       value before event
     * @param newValue       value after event
     */
    public void updateStatistics(Characteristics characteristic, int oldValue, int newValue) {
        Job character = CharacterModel.getInstance().getCharacter();

        int value = newValue - oldValue;

        if (value < 0) {
            switch (characteristic) {
                case STRENGTH -> character.decreaseStrength();
                case DEXTERITY -> character.decreaseDexterity();
                case INTELLIGENCE -> character.decreaseIntelligence();
                case WISDOM -> character.decreaseWisdom();
                case ROBUSTNESS -> character.decreaseRobustness();
                case CHARISMA -> character.decreaseCharisma();
            }
        } else if (value > 0) {
            switch (characteristic) {
                case STRENGTH -> character.increaseStrength();
                case DEXTERITY -> character.increaseDexterity();
                case INTELLIGENCE -> character.increaseIntelligence();
                case WISDOM -> character.increaseWisdom();
                case ROBUSTNESS -> character.increaseRobustness();
                case CHARISMA -> character.increaseCharisma();
            }
        }
    }

    public static LevelUpModel getInstance() {
        return instance;
    }
}
