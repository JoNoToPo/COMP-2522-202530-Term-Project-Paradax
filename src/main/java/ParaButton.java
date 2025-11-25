import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

/**
 * Represents a button in Paralax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class ParaButton extends ParaShape {

    private final Button button;
    private final ParaButtonType buttonType;

    public ParaButton(final double initX, final double initY,
                      final double width, final double height,
                      final double initAngle,
                      final ParaButtonType buttonType, final Pane root) {
        super(0, initX, initY, width, height, initAngle);
        button = new Button("Next\nLevel");
        button.setTextAlignment(TextAlignment.CENTER);
        this.buttonType = buttonType;
        applyButtonType(root);
    }

    @Override
    void transformHorizontal(final double windowX, final double delta) {
        button.setTranslateX(super.getInitX() - windowX + delta);
    }

    @Override
    void transformVertical(final double windowY, final double delta) {
        button.setTranslateY(super.getInitY() - windowY + delta);
    }

    @Override
    void rotate(final double delta) {
        button.setRotate(super.getInitAngle() + delta);
    }

    @Override
    void widen(final double delta) {
        button.setMaxWidth(super.getInitWidth() + delta);
        button.setMinWidth(super.getInitWidth() + delta);
    }

    @Override
    void lengthen(final double delta) {
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
