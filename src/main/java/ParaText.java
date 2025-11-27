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
    private static final int FONT_SIZE = 14;

    private final Text text;

    public ParaText(final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message, final Color color) {
        this(0, initX, initY, width, height, initAngle, message);
        text.setFill(color);
    }

    public ParaText(final int zIndex, final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message) {
        super(zIndex, initX, initY, width, height, initAngle);
        text = new Text(initX, initY, message);
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FONT_SIZE));
        text.maxWidth(initX);
        text.minWidth(initX);
        text.minHeight(initY);
        text.setFill(Color.WHITE);
        text.setRotate(initAngle);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(width);
    }

    @Override
    void transformHorizontal(final double delta) {
        text.setX(super.getInitX() + delta);
    }

    @Override
    void transformVertical(final double delta) {
        text.setY(super.getInitY() + delta);
    }

    @Override
    void rotate(final double delta) {
        text.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(final double delta) {
        text.maxWidth(super.getInitWidth() + delta);
        text.minWidth(super.getInitWidth() + delta);
        text.setWrappingWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(final double delta) {
        text.maxHeight(super.getInitHeight() + delta);
        text.minHeight(super.getInitHeight() + delta);
    }

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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
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
