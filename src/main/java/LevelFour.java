import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelFour {
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double INIT_Y = 300;
    static final double TEXT_HEIGHT = 55;
    static final double TEXT_WIDTH = 200;
    static final double BUTTON_DIMENSION = 50;

    private LevelFour() {
    }

    static Level levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaButton button = new ParaButton(1, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4 - 15,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        Transform sideWidth = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, 150);

        ParaShape textBG = new ParaRectangle(2, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4 - 15,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(sideWidth);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 4,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "Good luck! Have fun! (The next button is at the bottom :D)");
        welcome.addTransform(sideWidth);
        level.addShape(welcome);

        ParaShape move_against = new ParaRectangle(3, Driver.SCREEN_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2, 50, 50, 0, Color.BLUE);
        Transform vert = new Transform(TransformType.VERTICAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -100);
        Transform horizontalTransform = new Transform(TransformType.HORIZONTAL,
                TransformSource.X_POS, TransformPath.LINEAR, -100);
        move_against.addTransform(vert);
        move_against.addTransform(horizontalTransform);
        level.addShape(move_against);

        ParaShape spinWithWidth = new ParaRectangle(2, 300, 300, 50, 50, 45, Color.FIREBRICK);
        Transform sinT = new Transform(TransformType.VERTICAL,
                TransformSource.WIDTH, TransformPath.SINUSOIDAL, .5);
        Transform cosT = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -.5);
        spinWithWidth.addTransform(sinT);
        spinWithWidth.addTransform(cosT);
        level.addShape(spinWithWidth);

        ParaShape shape3 = new ParaRectangle(2, 400, 600, 150, 50, 0, Color.FIREBRICK);
        shape3.addTransform(cosT);
        level.addShape(shape3);

        return level;
    }
}
