import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelOne {
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double INIT_Y = 300;
    static final double TEXT_HEIGHT = 55;
    static final double TEXT_WIDTH = 200;
    static final double BUTTON_DIMENSION = 50;

    LevelOne() {
    }

    public static Level levelOne(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaButton button = new ParaButton(1, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4 - 15,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        Transform spin = new Transform(TransformType.ROTATE,
                TransformSource.Y_POS, TransformPath.LINEAR, 1);

        ParaShape shape4 = new ParaRectangle(2, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4 - 15,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        shape4.addTransform(spin);
        level.addShape(shape4);

        ParaText welcome = new ParaText(0, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "Welcome to Paralax! To play, MOVE your window around to find the \"Next Level\" button.");
        welcome.addTransform(spin);
        level.addShape(welcome);

        return level;
    }
}
