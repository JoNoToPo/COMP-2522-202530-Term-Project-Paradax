import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelOne {
    static final double TEXT_HEIGHT = 200;
    static final double TEXT_WIDTH = 200;

    LevelOne() {
    }

    public static Level levelOne(final Pane root) {
        Level level = new Level();

        ParaShape move_against = new ParaRectangle(3, Driver.SCREEN_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2, 0, 50, 50, Color.BLUE);
        Transform vert = new Transform(TransformType.VERTICAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -1);
        Transform horizontalTransform = new Transform(TransformType.HORIZONTAL,
                TransformSource.X_POS, TransformPath.LINEAR, -1);
        move_against.addTransform(vert);
        move_against.addTransform(horizontalTransform);
        level.addShape(move_against);

        ParaShape spinWithWidth = new ParaRectangle(2, 300, 300, 45, 50, 50, Color.FIREBRICK);
        Transform sinT = new Transform(TransformType.VERTICAL,
                TransformSource.WIDTH, TransformPath.SINUSOIDAL, .5);
        Transform cosT = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -.5);
        spinWithWidth.addTransform(sinT);
        spinWithWidth.addTransform(cosT);
        level.addShape(spinWithWidth);

        ParaButton button = new ParaButton(1, 400, 600, 0,
                50, 50, "  Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        button.addTransform(cosT);
        level.addShape(button);

        ParaShape shape3 = new ParaRectangle(2, 400, 600, 0, 150, 50, Color.FIREBRICK);
        Transform s3t1 = new Transform(TransformType.ROTATE,
                TransformSource.HEIGHT, TransformPath.LINEAR, 1);
        shape3.addTransform(s3t1);
        shape3.addTransform(cosT);
        level.addShape(shape3);

        ParaText welcome = new ParaText(0, Driver.SCREEN_WIDTH / 2 - TEXT_WIDTH / 2,
                Driver.SCREEN_HEIGHT / 2 - Driver.INIT_HEIGHT / 2,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "Welcome to Paralax!\nTo play move your window around\n and widen and shorten it to find\n the next level buttons.");
        level.addShape(welcome);

        return level;
    }
}
