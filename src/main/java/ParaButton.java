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
    private static final double BORDER_SIZE = Scale.height(0.33);
    private static final double FONT_SIZE = Scale.height(1.32);

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
        button.setStyle("-fx-border-color: lightblue;"
                + "-fx-border-width: " + BORDER_SIZE + "px; -fx-border-radius: " + BORDER_SIZE
                + "px; -fx-font-size: " + FONT_SIZE + "px;");
        this.buttonType = buttonType;
        applyButtonType(root);
    }

    /**
     * Changes the text of the button.
     *
     * @param newText the new text as a String
     */
    public void changeText(final String newText) {
        button.setText(newText);
    }

    /**
     * Transforms the horizontal position of the button.
     *
     * @param newX the X position as a double
     */
    @Override
    void transformHorizontal(final double newX) {
        button.setTranslateX(newX);
    }

    /**
     * Transforms the vertical position of the button.
     *
     * @param newY the Y position as a double
     */
    @Override
    void transformVertical(final double newY) {
        button.setTranslateY(newY);
    }

    /**
     * Transforms the angle of the button.
     *
     * @param newAngle the angle as a double
     */
    @Override
    void rotate(final double newAngle) {
        button.setRotate(newAngle);
    }

    /**
     * Widens the button.
     *
     * @param newWidth the width as a double
     */
    @Override
    void widen(final double newWidth) {
        button.setMaxWidth(newWidth);
        button.setMinWidth(newWidth);
    }

    /**
     * Lengthens the button.
     *
     * @param newLength the length as a double
     */
    @Override
    void lengthen(final double newLength) {
        button.setMaxHeight(newLength);
        button.setMinHeight(newLength);
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
            button.setOnAction(_ -> root.getChildren().clear());
            button.setText("Next\nLevel");
        } else if (buttonType == ParaButtonType.BOOL_SWITCH) {
            button.setOnAction(_ -> root.getChildren().clear());
            button.setText("Continue");
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
    public boolean equals(final Object object) {
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
