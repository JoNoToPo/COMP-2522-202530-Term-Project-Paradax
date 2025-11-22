import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ParaRectangle extends ParaShape {

    private final Rectangle rectangle;

    public ParaRectangle(final int zIndex, final double initX, final double initY,
                         final double initAngle, final double width, final double height, final Color color) {
        super(zIndex, initX, initY, initAngle);
        rectangle = new Rectangle(width, height, color);
        rectangle.setX(initX);
        rectangle.setY(initX);
        rectangle.setRotate(initAngle);
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

    Node getNode() {
        return rectangle;
    }
}
