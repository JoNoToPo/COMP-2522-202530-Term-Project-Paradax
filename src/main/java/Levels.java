import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

/**
 * The levels of the game.
 *
 * @author jenge
 * @version 2025
 */
public class Levels {
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    static final double Y_CENTERING_HEIGHT = 60;
    static final double HEADER_HEIGHT = 40;
    static final double WIDTH_DIFFERENCE = 17;
    private static final double TEXT_HEIGHT = 60;
    private static final double TWO_LINE = 10;
    private static final double TEXT_WIDTH = 250;
    private static final double TEXT_OFFSET = 15;
    private static final double BUTTON_DIMENSION = 50;

    private static final Transform SPIN_Y = new Transform(TransformType.ROTATE,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform SPIN_X = new Transform(TransformType.ROTATE,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
    static final Transform WITH_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);
    static final Transform AGAINST_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, -1);
    static final Transform WITH_SHORTEN = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    static final Transform WITH_X = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
    static final Transform WITH_Y = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform HORIZONTAL_HEIGHT = new Transform(TransformType.HORIZONTAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    private static final Transform AGAINST_MOVE_Y = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, -1);
    private static final Transform AGAINST_MOVE_X = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, -1);
    private static final Transform SIN_T = new Transform(TransformType.VERTICAL,
            TransformSource.WIDTH, TransformPath.SINUSOIDAL, 1);
    private static final Transform COS_T = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -1);

    Levels() {
    }

    static LinkedList<Level> levels(final Pane root) {
        LinkedList<Level> levels = new LinkedList<>();
        levels.add(levelOne(root));
        levels.add(levelTwo(root));
        levels.add(levelThree(root));
        levels.add(levelFour(root));
        levels.add(levelFive(root));
        levels.add(levelSix(root));
        return levels;
    }

    private static Level levelOne(final Pane root) {

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
        textBG.addTransform(SPIN_Y);
        textBG.addTransform(SPIN_X);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, -OFFSET_ANGLE,
                "\nFind the \"Next Level\" button.");
        welcome.addTransform(SPIN_Y);
        welcome.addTransform(SPIN_X);
        level.addShape(welcome);

        return level;
    }

    static Level levelTwo(final Pane root) {
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
        textBG.addTransform(AGAINST_MOVE_Y);
        textBG.addTransform(AGAINST_MOVE_X);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "These Red lines indicate that you cannot WIDEN your window");
        welcome.addTransform(AGAINST_MOVE_Y);
        welcome.addTransform(AGAINST_MOVE_X);
        level.addShape(welcome);

        return level;
    }

    static Level levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        ParaButton button = new ParaButton(1, INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + 2.5,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2 + 2.5,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 - INIT_WIDTH;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(HORIZONTAL_HEIGHT);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0,
                "\nWhat does this black line do?");
        welcome.addTransform(HORIZONTAL_HEIGHT);
        level.addShape(welcome);

        return level;
    }

    static Level levelFour(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

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
        textBG.addTransform(SIN_T);
        textBG.addTransform(COS_T);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "\nSpinny!");
        welcome.addTransform(SIN_T);
        welcome.addTransform(COS_T);
        level.addShape(welcome);

        return level;
    }

    static Level levelFive(final Pane root) {
        final double initWidth = 500;
        final double initHeight = 600;
        final double initY = Driver.SCREEN_HEIGHT / 2 - initHeight / 2;
        final double initX = Driver.SCREEN_WIDTH / 2 - initWidth / 2;

        Level level = new Level(initX, initY, initWidth, initHeight);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        double textBoxX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = initY + (initHeight - Y_CENTERING_HEIGHT) / 3 - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "It's definitely behind those boxes down there");
        level.addShape(welcome);

        double buttonX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
        double buttonY = initY + 2 * (initHeight - Y_CENTERING_HEIGHT) / 3 - BUTTON_DIMENSION / 2;

        ParaButton button = new ParaButton(1, buttonX, buttonY,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        ParaRectangle red = new ParaRectangle(0, buttonX - TEXT_WIDTH / 2 + initX, buttonY,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(AGAINST_WIDEN);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, buttonX - BUTTON_DIMENSION - initX, buttonY - TEXT_HEIGHT,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(WITH_WIDEN);
        level.addShape(blue);

        return level;
    }

    static Level levelSix(final Pane root) {
        final double initWidth = 500;
        final double initHeight = 600;
        final double initY = Driver.SCREEN_HEIGHT / 2 - initHeight / 2;
        final double initX = Driver.SCREEN_WIDTH / 2 - initWidth / 2;
        Level level = new Level(initX, initY, initWidth, initHeight);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        double textBoxX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = initY + (initHeight - Y_CENTERING_HEIGHT) / 3 - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "I can smell it, it's 100% still below two boxes.");
        level.addShape(welcome);

        double buttonX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
        double buttonY = initY + 2 * (initHeight - Y_CENTERING_HEIGHT) / 3 - BUTTON_DIMENSION / 2;

        ParaButton button = new ParaButton(1, buttonX, buttonY,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0, "Next\nLevel", ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        ParaRectangle red = new ParaRectangle(0, initY / 2 + BUTTON_DIMENSION, buttonY,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -.5);
        red.addTransform(slowDescent);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, buttonX - BUTTON_DIMENSION - initHeight / 2, buttonY - TEXT_HEIGHT,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        Transform slowHeight = new Transform(TransformType.HORIZONTAL,
                TransformSource.HEIGHT, TransformPath.LINEAR, .5);
        blue.addTransform(slowHeight);
        level.addShape(blue);

        return level;
    }
}
