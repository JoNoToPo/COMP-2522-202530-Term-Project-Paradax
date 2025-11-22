import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class ParaButton extends ParaShape {

    private final Button button;

    public ParaButton(final int zIndex, final double initX, final double initY,
                      final double initAngle, final double width, final double height, final Color color) {
        super(zIndex, initX, initY, initAngle);
        button = new Button();
        button.setMaxHeight(height);
        button.setMinHeight(height);
        button.setMaxWidth(width);
        button.setMinWidth(width);
        button.setTranslateX(initX);
        button.setTranslateY(initY);
        button.setRotate(initAngle);
    }

    @Override
    void transformHorizontal(double windowX, double delta) {
        button.setTranslateX(super.getInitX() - windowX + delta);
    }

    @Override
    void transformVertical(double windowY, double delta) {
        button.setTranslateY(super.getInitY() - windowY + delta);
    }

    @Override
    void rotate(double delta) {
        button.setRotate(super.getInitAngle() + delta);
    }

    Node getNode() {
        return button;
    }
}
