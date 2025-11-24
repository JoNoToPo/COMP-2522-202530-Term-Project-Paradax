import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelThree {
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double INIT_Y = 300;

    private LevelThree() {
    }

    static Level levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaShape button = new ParaButton(5, 300, 300, 0,
                200, 50, "Next Level!", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

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
