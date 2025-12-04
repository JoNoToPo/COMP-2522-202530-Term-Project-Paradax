import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a rectangle in Paradax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class ParaRectangle extends ParaShape implements Serializable {

    private static final double TEN_PIXELS = 10;
    private final Rectangle rectangle;

    /**
     * Constructs a rectangle.
     *
     * @param zIndex the z index of the rectangle as an integer
     * @param initX the x index of the top left of the rectangle as a double
     * @param initY the y index of the top left of the rectangle as a double
     * @param width the width of the rectangle as a double
     * @param height the height of the rectangle as a double
     * @param initAngle the angle of the rectangle in degrees as a double
     * @param color the root of the scene
     */
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

    /**
     * Sets the opacity of the Rectangle.
     *
     * @param opacity the percentage of the opacity as a double
     */
    void setOpacity(final double opacity) {
        rectangle.setOpacity(opacity);
    }

    /**
     * Transforms the horizontal position of the rectangle.
     *
     * @param delta the change as a double
     */
    @Override
    void transformHorizontal(final double delta) {
        rectangle.setX(super.getInitX() + delta);
    }

    /**
     * Transforms the vertical position of the rectangle.
     *
     * @param delta the change as a double
     */
    @Override
    void transformVertical(final double delta) {
        rectangle.setY(super.getInitY() + delta);
    }

    /**
     * Transforms the angle of the rectangle.
     *
     * @param delta the change as a double
     */
    @Override
    void rotate(final double delta) {
        rectangle.setRotate(super.getInitAngle() + delta);
    }

    /**
     * Widens the rectangle.
     *
     * @param delta the change as a double
     */
    @Override
    void widen(final double delta) {
        rectangle.setWidth(super.getInitWidth() + delta);
    }

    /**
     * Lengthens the rectangle.
     *
     * @param delta the change as a double
     */
    @Override
    void lengthen(final double delta) {
        rectangle.setHeight(super.getInitHeight() + delta);
    }

    /**
     * Returns the node of the rectangle.
     *
     * @return the rectangle as a Node
     */
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
    public boolean equals(final Object object) {
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
