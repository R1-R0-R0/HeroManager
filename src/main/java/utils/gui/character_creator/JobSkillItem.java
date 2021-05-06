package utils.gui.character_creator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.job.JobSkill;

/**
 * Utility class used to encapsulate object and give them selection property for spell list view in character creation
 *
 * @see controller.CharacterCreatorController list views declarations
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
     * @return BooleanProperty selection property, used for checkbox in list view
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
