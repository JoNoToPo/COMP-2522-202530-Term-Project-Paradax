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

    public ParaButton(final double initX, final double initY,
                      final double width, final double height,
                      final double initAngle,
                      final ParaButtonType buttonType, final Pane root) {
        super(0, initX, initY, width, height, initAngle);
        button = new Button("Next\nLevel");
        button.setFocusTraversable(false);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setStyle("-fx-border-color: lightblue; -fx-border-width: 3px; -fx-border-radius: 3px;");
        this.buttonType = buttonType;
        applyButtonType(root);
    }

    @Override
    void transformHorizontal(final double delta) {
        button.setTranslateX(super.getInitX() + delta);
    }

    @Override
    void transformVertical(final double delta) {
        button.setTranslateY(super.getInitY() + delta);
    }

    @Override
    void rotate(final double delta) {
        button.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(final double delta) {
        button.setMaxWidth(super.getInitWidth() + delta);
        button.setMinWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(final double delta) {
        button.setMaxHeight(super.getInitHeight() + delta);
        button.setMinHeight(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return button;
    }

    public void applyButtonType(final Pane root) {
        if (buttonType == ParaButtonType.NEXT_LEVEL) {
            button.setOnAction(_ -> {
                System.out.println("clicked");
                root.getChildren().clear();
            });
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
