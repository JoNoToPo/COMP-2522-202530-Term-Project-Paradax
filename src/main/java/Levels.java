import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Levels {
    static final double Y_CENTERING_HEIGHT = 60;
    static final double HEADER_HEIGHT = 40;
    static final double WIDTH_DIFFERENCE = 17;
    private static final double TEXT_HEIGHT = 60;
    private static final double TWO_LINE = 10;
    private static final double TEXT_WIDTH = 250;
    private static final double TEXT_OFFSET = 15;
    private static final double BUTTON_DIMENSION = 50;

    private static final Transform spinY = new Transform(TransformType.ROTATE,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform spinX = new Transform(TransformType.ROTATE,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
    static final Transform withWiden = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);
    static final Transform withShorten = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    static final Transform withX = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
    static final Transform withY = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform horizontalHeight = new Transform(TransformType.HORIZONTAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    private static final Transform againstMoveY = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, -1);
    private static final Transform againstMoveX = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, -1);
    private static final Transform sinT = new Transform(TransformType.VERTICAL,
            TransformSource.WIDTH, TransformPath.SINUSOIDAL, 1);
    private static final Transform cosT = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -1);

    Levels() {
    }

    static LinkedList<Level> levels(final Pane root) {
        LinkedList<Level> levels = new LinkedList<>();
        levels.add(levelOne(root));
        levels.add(levelTwo(root));
        levels.add(levelThree(root));
        levels.add(levelFour(root));
        return levels;
    }

    private static Level levelOne(final Pane root) {
        final double INIT_WIDTH = 300;
        final double INIT_HEIGHT = 300;
        final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
        final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;
        final double OFFSET_ANGLE = Math.floor(INIT_X) + Math.floor(INIT_Y);

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, -OFFSET_ANGLE, Color.BLACK);
        textBG.addTransform(spinY);
        textBG.addTransform(spinX);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, -OFFSET_ANGLE,
                "\nFind the \"Next Level\" button.");
        welcome.addTransform(spinY);
        welcome.addTransform(spinX);
        level.addShape(welcome);

        return level;
    }

    static Level levelTwo(final Pane root) {
        final double INIT_WIDTH = 300;
        final double INIT_HEIGHT = 300;
        final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
        final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double textBoxX = 2 * INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = 2 * INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(againstMoveY);
        textBG.addTransform(againstMoveX);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "These Red lines indicate that you cannot WIDEN your window");
        welcome.addTransform(againstMoveY);
        welcome.addTransform(againstMoveX);
        level.addShape(welcome);

        return level;
    }

    static Level levelThree(final Pane root) {
        final double INIT_WIDTH = 300;
        final double INIT_HEIGHT = 300;
        final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
        final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        ParaShape textBG = new ParaRectangle(2, INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(horizontalHeight);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 - INIT_WIDTH,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "\nWhat does this black line do?");
        welcome.addTransform(horizontalHeight);
        level.addShape(welcome);

        return level;
    }

    static Level levelFour(final Pane root) {
        final double INIT_WIDTH = 300;
        final double INIT_HEIGHT = 300;
        final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
        final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double offsetX = Math.cos(INIT_WIDTH / Math.TAU / 10) * 100;
        double offsetY = Math.sin(INIT_WIDTH / Math.TAU / 10) * 100;
        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 + offsetX;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 - offsetY;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(sinT);
        textBG.addTransform(cosT);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "\nSpinny!");
        welcome.addTransform(sinT);
        welcome.addTransform(cosT);
        level.addShape(welcome);

        return level;
    }

    static Level levelFive(final Pane root) {
        final double INIT_WIDTH = 500;
        final double INIT_HEIGHT = 500;
        final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
        final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double offsetX = Math.cos(INIT_WIDTH / Math.TAU / 10) * 100;
        double offsetY = Math.sin(INIT_WIDTH / Math.TAU / 10) * 100;
        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 + offsetX;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 - offsetY;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "\n");
        level.addShape(welcome);

        return level;
    }
}
