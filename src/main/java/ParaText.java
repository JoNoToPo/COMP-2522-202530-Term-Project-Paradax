import com.sun.prism.paint.Paint;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public final class ParaText extends ParaShape {

    private final Text text;

    public ParaText(final int zIndex, final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message, Color color) {
        this(zIndex, initX, initY, width, height, initAngle, message);
        text.setFill(color);
    }

    public ParaText(final int zIndex, final double initX, final double initY,
                    final double width, final double height,
                    final double initAngle, final String message) {
        super(zIndex, initX, initY, width, height, initAngle);
        text = new Text(initX, initY, message);
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        text.maxWidth(initX);
        text.minWidth(initX);
        text.minHeight(initY);
        text.setFill(Color.WHITE);
        text.setRotate(initAngle);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(width);
    }

    void changeMessage(String message) {
        text.setText(message);
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
        text.maxWidth(super.getInitWidth() + delta);
        text.minWidth(super.getInitWidth() + delta);
        text.setWrappingWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(double delta) {
        text.maxWidth(super.getInitHeight() + delta);
        text.minWidth(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return text;
    }
}
