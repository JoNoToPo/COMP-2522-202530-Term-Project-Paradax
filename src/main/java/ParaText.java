import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public final class ParaText extends ParaShape {

    private final Text text;

    public ParaText(final int zIndex, final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message) {
        super(zIndex, initX, initY, width, height, initAngle);
        text = new Text(width, height, message);
        text.setX(initX);
        text.setY(initY);
        text.setRotate(initAngle);
        text.setTextAlignment(TextAlignment.CENTER);
    }

    @Override
    void transformHorizontal(double windowX, double delta) {
        text.setX(super.getInitX() - windowX + delta);
    }

    @Override
    void transformVertical(double windowY, double delta) {
        text.setY(super.getInitY() - windowY + delta);
    }

    @Override
    void rotate(double delta) {
        text.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(double delta) {
        text.setX(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(double delta) {
        text.setX(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return text;
    }
}
