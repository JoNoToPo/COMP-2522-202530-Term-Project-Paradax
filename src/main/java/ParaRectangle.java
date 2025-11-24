import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class ParaRectangle extends ParaShape {

    private static final double TEN_PIXELS = 10;
    private final Rectangle rectangle;

    public ParaRectangle(final int zIndex, final double initX, final double initY,
                         final double width, final double height,
                         final double initAngle, final Color color) {
        super(zIndex, initX, initY, width, height, initAngle);
        rectangle = new Rectangle(width, height, color);
        rectangle.setArcWidth(TEN_PIXELS);
        rectangle.setArcHeight(TEN_PIXELS);
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
}
