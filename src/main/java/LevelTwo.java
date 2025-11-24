import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelTwo {
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

    private LevelTwo() {
    }

    static Level levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        Transform sideWidth = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, 150);

        ParaShape textBG = new ParaRectangle(2, INIT_X + WIDTH_MINUS_HEADER / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(sideWidth);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, INIT_X + WIDTH_MINUS_HEADER / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                INIT_Y + HEIGHT_MINUS_HEADER / 2 - TEXT_HEIGHT / 2 + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "Some objects move with the WIDTH of your screen.");
        welcome.addTransform(sideWidth);
        level.addShape(welcome);

        return level;
    }
}
