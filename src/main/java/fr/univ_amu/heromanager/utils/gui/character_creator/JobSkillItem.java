package fr.univ_amu.heromanager.utils.gui.character_creator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import fr.univ_amu.heromanager.model.job.JobSkill;

/**
 * Utility class used to encapsulate object and give them selection property for spell list fr.univ_amu.heromanager.view in character creation
 *
 * @see fr.univ_amu.heromanager.controller.CharacterCreatorController list views declarations
 */
public class JobSkillItem {
    private JobSkill skill;
    private BooleanProperty selected;

    /**
     * @param skill spell object to attach
     */
    public JobSkillItem(JobSkill skill) {
        this.skill = skill;
        selected = new SimpleBooleanProperty(false);
    }

    /**
     * @return attached job skill
     */
    public JobSkill getSkill() {
        return skill;
    }

    /**
     * @return BooleanProperty selection property, used for checkbox in list fr.univ_amu.heromanager.view
     */
    public BooleanProperty selectedProperty() {
        return selected;
    }

    /**
     * @return TRUE if item is selected, FALSE otherwise
     */
    public boolean isSelected() {
        return selected.get();
    }

    /**
     * @return toString() result of attached job skill
     */
    @Override
    public String toString() {
        return skill.toString();
    }
}
