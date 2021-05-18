package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.LevelUpController;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.Characteristics;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.job.JobSkill;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.utils.gui.character_creator.JobSkillItem;
import fr.univ_amu.heromanager.utils.gui.character_creator.SpellItem;
import fr.univ_amu.heromanager.view.CharacterView;
import fr.univ_amu.heromanager.view.LevelUpView;

import java.util.Arrays;
import java.util.List;

/**
 * Model of Level up fr.univ_amu.heromanager.view.
 * Called to level up character and set up new statistics.
 *
 * @see LevelUpView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see LevelUpController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class LevelUpModel implements Model {

    private static LevelUpModel instance;

    /**
     * Contructor of this class, when called, init fr.univ_amu.heromanager.view
     *
     * @param owner fr.univ_amu.heromanager.view caller
     */
    public LevelUpModel(Stage owner) {
        instance = this;
        new LevelUpView(owner);

        LevelUpView.getInstance().setJobSkillsListView(Arrays.asList(JobSkill.values()));
        List<Spell> spells = HeroManagerDB.getSpells();
        spells.removeIf(spell -> spell.getJobType() != CharacterModel.getInstance().getCharacter().getJobType());
        LevelUpView.getInstance().setSpellsListView(spells);
    }

    /**
     * @return instance of this class
     */
    public static LevelUpModel getInstance() {
        return instance;
    }

    /**
     * Allows to confirm level up of character and set up its new statistics, and close level up fr.univ_amu.heromanager.view
     */
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

    /**
     * Cancelling level up.
     * Character will not level up and none of this statistics will be affected
     */
    public void cancelLevelUp() {
        LevelUpView.getInstance().getStage().close();
    }

    /**
     * Called when one of statistics spinner is updated, to update character's statistics
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
}
