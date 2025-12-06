import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

/**
 * Represents text in Paradax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class ParaText extends ParaShape {
    private static final int Z_INDEX = 10;
    private static final int FONT_SIZE = 14;

    private final Text text;

    /**
     * Constructs a text node.
     *
     * @param initX the x index of the top left of the text as a double
     * @param initY the y index of the top left of the text as a double
     * @param width the width of the text as a double
     * @param height the height of the text as a double
     * @param initAngle the angle of the text in degrees as a double
     * @param message the message as a string
     * @param color the color of the message as a Color
     */
    public ParaText(final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message, final Color color) {
        this(initX, initY, width, height, initAngle, message);
        text.setFill(color);
    }

    /**
     * Constructs a text node.
     *
     * @param initX the x index of the top left of the text as a double
     * @param initY the y index of the top left of the text as a double
     * @param width the width of the text as a double
     * @param height the height of the text as a double
     * @param initAngle the angle of the text in degrees as a double
     * @param message the message as a string
     */
    public ParaText(final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message) {
        super(Z_INDEX, initX, initY, width, height, initAngle);
        if (message == null || message.isBlank()) {
            text = new Text(initX, initY, "Default message.");
        } else {
            text = new Text(initX, initY, message);
        }
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FONT_SIZE));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(width);
    }

    /**
     * Transforms the horizontal position of the text.
     *
     * @param newX the X position as a double
     */
    @Override
    void transformHorizontal(final double newX) {
        text.setX(newX);
    }

    /**
     * Transforms the vertical position of the text.
     *
     * @param newY the Y position as a double
     */
    @Override
    void transformVertical(final double newY) {
        text.setY(newY);
    }

    /**
     * Transforms the angle of the text.
     *
     * @param newAngle the angle as a double
     */
    @Override
    void rotate(final double newAngle) {
        text.setRotate(newAngle);
    }

    /**
     * Widens the text.
     *
     * @param newWidth the width as a double
     */
    @Override
    void widen(final double newWidth) {
        text.maxWidth(newWidth);
        text.minWidth(newWidth);
        text.setWrappingWidth(newWidth);
    }

    /**
     * Lengthens the text.
     *
     * @param newLength the length as a double
     */
    @Override
    void lengthen(final double newLength) {
        text.maxHeight(newLength);
        text.minHeight(newLength);
    }

    /**
     * Returns the node of the text.
     *
     * @return the text as a Node
     */
    @Override
    Node getNode() {
        return text;
    }

    /**
     * Returns a String representation of this ParaText.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("ParaText{");
        sb.append("text=").append(text);
        sb.append(", ");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this ParaText to another Object for equality.
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
        if (!super.equals(object)) {
            return false;
        }
        ParaText paraText = (ParaText) object;
        return Objects.equals(text, paraText.text);
    }

    /**
     * Returns a hash code for this ParaText.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                text
        );
    }
}
