import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * Represents a rectangle in Paradax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class ParaRectangle extends ParaShape {

    private static final double TEN_PIXELS = 10;
    private final Rectangle rectangle;

    public ParaRectangle(final int zIndex, final double initX, final double initY,
                         final double width, final double height,
                         final double initAngle, final Color color) {
        super(zIndex, initX, initY, width, height, initAngle);
        rectangle = new Rectangle(width, height, color);
        rectangle.setArcHeight(TEN_PIXELS);
        rectangle.setArcWidth(TEN_PIXELS);
        rectangle.setStrokeWidth(2);
        rectangle.setStroke(Paint.valueOf("WHITE"));
    }

    void setOpacity(double opacity) {
        rectangle.setOpacity(opacity);
    }

    @Override
    void transformHorizontal(final double windowX, final double delta) {
        rectangle.setX(super.getInitX() - windowX + delta);
    }

    @Override
    void transformVertical(final double windowY, final double delta) {
        rectangle.setY(super.getInitY() - windowY + delta);
    }

    @Override
    void rotate(final double delta) {
        rectangle.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(final double delta) {
        rectangle.setWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(final double delta) {
        rectangle.setHeight(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return rectangle;
    }

    /**
     * Returns a String representation of this ParaRectangle.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("ParaRectangle{");
        sb.append("rectangle=").append(rectangle);
        sb.append(", ");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this ParaRectangle to another Object for equality.
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
        ParaRectangle that = (ParaRectangle) object;
        return Objects.equals(rectangle, that.rectangle);
    }

    /**
     * Returns a hash code for this ParaRectangle.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(rectangle);
    }
}
