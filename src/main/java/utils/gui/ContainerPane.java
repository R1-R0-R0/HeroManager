package utils.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * A JavaFX StackPane extension that can contains and give an object
 *
 * @param <T> type of object
 */
public class ContainerPane<T> extends StackPane {

    /**
     * The contained T object
     */
    private T containedObject;

    /**
     * Empty contructor to create an instance without default contained object
     */
    public ContainerPane() {

    }

    /**
     * Contructor taking a T object to set it up at start
     *
     * @param object object to contain
     */
    public ContainerPane(T object) {
        containedObject = object;
        super.getChildren().add(new Text(object.toString()));
    }

    /**
     * To retrieve contained object already assigned. May return null object.
     *
     * @return contained object or null if doesn't exist
     */
    public T getContainedObject() {
        return containedObject;
    }

    /**
     * To set contained object and set his toString String in a text passed to super StackPane extended
     *
     * @param containedObject object to contain
     */
    public void setContainedObject(T containedObject) {
        this.containedObject = containedObject;
        super.getChildren().clear();

        Text text = new Text(containedObject.toString());
        text.setWrappingWidth(super.getWidth());
        super.getChildren().add(text);
    }

    /**
     * To set contained object to null and clear text previously passed to super StackPane extended
     */
    public void clearContainedObject() {
        containedObject = null;
        super.getChildren().clear();
    }
}
