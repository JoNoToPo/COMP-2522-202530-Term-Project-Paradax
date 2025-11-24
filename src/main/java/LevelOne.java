import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelOne {
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double HEADER_HEIGHT = 60;
    private static final double WIDTH_DIFFERENCE = 17;
    private static final double HEIGHT_MINUS_HEADER = INIT_HEIGHT - HEADER_HEIGHT;
    private static final double WIDTH_MINUS_HEADER = INIT_WIDTH - WIDTH_DIFFERENCE;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
    private static final double TEXT_HEIGHT = 55;
    private static final double TEXT_WIDTH = 250;
    private static final double TEXT_OFFSET = 15;
    private static final double BUTTON_DIMENSION = 50;
    private static final int ONE_HUNDRED_EIGHTY_DEGREES = 180;
    private static final double OFFSET_ANGLE = ONE_HUNDRED_EIGHTY_DEGREES
            * (INIT_X + INIT_Y) * 150;

    LevelOne() {
    }

    public static Level levelOne(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
//        ParaRectangle point = new ParaRectangle(0, INIT_X, INIT_Y, 3, 3, 0, Color.BLACK);
//        level.addShape(point);
//
//        ParaRectangle point2 = new ParaRectangle(0, INIT_X, INIT_Y + INIT_HEIGHT, 3, 3, 0, Color.BLACK);
//        level.addShape(point2);
//
//        ParaRectangle point3 = new ParaRectangle(0, INIT_X + INIT_WIDTH, INIT_Y + INIT_HEIGHT, 3, 3, 0, Color.BLACK);
//        level.addShape(point3);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        Transform spin = new Transform(TransformType.ROTATE,
                TransformSource.Y_POS, TransformPath.LINEAR, 1);
        Transform spin2 = new Transform(TransformType.ROTATE,
                TransformSource.X_POS, TransformPath.LINEAR, 1);

        ParaShape shape4 = new ParaRectangle(2, INIT_X + WIDTH_MINUS_HEADER / 2 - TEXT_WIDTH / 2,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2,
                TEXT_WIDTH, TEXT_HEIGHT, -OFFSET_ANGLE - 44, Color.BLACK);
        shape4.addTransform(spin);
        shape4.addTransform(spin2);
        level.addShape(shape4);

        ParaText welcome = new ParaText(0, INIT_X + WIDTH_MINUS_HEADER / 2 - TEXT_WIDTH / 2,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2 + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, -OFFSET_ANGLE - 44,
                "Welcome to Paralax! To play, MOVE your window around to find the \"Next Level\" button.");
        welcome.addTransform(spin);
        welcome.addTransform(spin2);
        level.addShape(welcome);

        return level;
    }
}
