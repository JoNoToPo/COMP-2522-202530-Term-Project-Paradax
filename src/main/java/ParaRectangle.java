import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class ParaRectangle extends ParaShape {

    private final Rectangle rectangle;

    public ParaRectangle(final int zIndex, final double initX, final double initY,
                         final double width, final double height,
                         final double initAngle, final Color color) {
        super(zIndex, initX, initY, width, height, initAngle);
        rectangle = new Rectangle(width, height, color);
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
    }

    @Override
    void transformHorizontal(double windowX, double delta) {
        rectangle.setX(super.getInitX() - windowX + delta);
    }

    @Override
    void transformVertical(double windowY, double delta) {
        rectangle.setY(super.getInitY() - windowY + delta);
    }

    @Override
    void rotate(double delta) {
        rectangle.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(double delta) {
        rectangle.setWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(double delta) {
        rectangle.setHeight(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return rectangle;
    }
}
