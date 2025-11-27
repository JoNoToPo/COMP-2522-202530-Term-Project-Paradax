import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

/**
 * Represents a button in Paralax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class ParaButton extends ParaShape {

    private final Button button;
    private final ParaButtonType buttonType;

    /**
     * Constructs a button.
     *
     * @param initX the x index of the top left of the button as a double
     * @param initY the y index of the top left of the button as a double
     * @param width the width of the button as a double
     * @param height the height of the button as a double
     * @param initAngle the angle of the button in degrees as a double
     * @param buttonType the type of button as a ButtonType
     * @param root the root of the scene
     */
    public ParaButton(final double initX, final double initY,
                      final double width, final double height,
                      final double initAngle,
                      final ParaButtonType buttonType, final Pane root) {
        super(0, initX, initY, width, height, initAngle);
        button = new Button("");
        button.setFocusTraversable(false);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setStyle("-fx-border-color: lightblue; -fx-border-width: 3px; -fx-border-radius: 3px;");
        this.buttonType = buttonType;
        applyButtonType(root);
    }

    /**
     * Transforms the horizontal position of the button.
     *
     * @param delta the change as a double
     */
    @Override
    void transformHorizontal(final double delta) {
        button.setTranslateX(super.getInitX() + delta);
    }

    /**
     * Transforms the vertical position of the button.
     *
     * @param delta the change as a double
     */
    @Override
    void transformVertical(final double delta) {
        button.setTranslateY(super.getInitY() + delta);
    }

    /**
     * Transforms the angle of the button.
     *
     * @param delta the change as a double
     */
    @Override
    void rotate(final double delta) {
        button.setRotate(super.getInitAngle() + delta);
    }

    /**
     * Widens the button.
     *
     * @param delta the change as a double
     */
    @Override
    void widen(final double delta) {
        button.setMaxWidth(super.getInitWidth() + delta);
        button.setMinWidth(super.getInitWidth() + delta);
    }

    /**
     * Lengthens the button.
     *
     * @param delta the change as a double
     */
    @Override
    void lengthen(final double delta) {
        button.setMaxHeight(super.getInitHeight() + delta);
        button.setMinHeight(super.getInitHeight() + delta);
    }

    /**
     * Returns the node of the button.
     *
     * @return the button as a Node
     */
    @Override
    Node getNode() {
        return button;
    }

    private void applyButtonType(final Pane root) {
        if (buttonType == ParaButtonType.NEXT_LEVEL) {
            button.setOnAction(_ -> {
                System.out.println("clicked");
                root.getChildren().clear();
            });
            button.setText("Next\nLevel");
        }
    }

    /**
     * Returns a String representation of this ParaButton.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("ParaButton{");
        sb.append("button=").append(button);
        sb.append(", buttonType=").append(buttonType);
        sb.append(", ");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this ParaButton to another Object for equality.
     *
     * @param object an Object
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ParaButton that = (ParaButton) object;
        return Objects.equals(button, that.button)
                && buttonType == that.buttonType;
    }

    /**
     * Returns a hash code for this ParaButton.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                button,
                buttonType
        );
    }
}
