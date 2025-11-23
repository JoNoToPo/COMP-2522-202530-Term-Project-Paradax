import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public final class ParaButton extends ParaShape {

    private final Button button;
    private final ParaButtonType buttonType;

    public ParaButton(final int zIndex, final double initX, final double initY,
                      final double width, final double height,
                      final double initAngle, final String label,
                      final ParaButtonType buttonType, final Pane root) {
        super(zIndex, initX, initY, width, height, initAngle);
        button = new Button(label);
        button.setMaxHeight(height);
        button.setMinHeight(height);
        button.setMaxWidth(width);
        button.setMinWidth(width);
        button.setTranslateX(initX);
        button.setTranslateY(initY);
        button.setRotate(initAngle);
        this.buttonType = buttonType;
        applyButtonType(root);
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

    @Override
    void widen(double delta) {
        button.setMaxWidth(super.getInitWidth() + delta);
        button.setMinWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(double delta) {
        button.setMaxHeight(super.getInitHeight() + delta);
        button.setMinHeight(super.getInitHeight() + delta);
    }

    @Override
    Node getNode() {
        return button;
    }

    public void applyButtonType(final Pane root) {
        if (buttonType == ParaButtonType.NEXT_LEVEL) {
            button.setOnAction(_ -> {
                System.out.println("clicked");
                root.getChildren().clear();
            });
        }
    }
}
