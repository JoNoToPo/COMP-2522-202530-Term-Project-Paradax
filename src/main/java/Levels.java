import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

/**
 * The levels of the game.
 *
 * @author jenge
 * @version 2025
 */
public final class Levels {

    /**
     * Horizontally transforms linearly with window width.
     */
    static final Transform WITH_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);

    /**
     * Horizontally transforms linearly against window width.
     */
    static final Transform AGAINST_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, -1);

    /**
     * Horizontally transforms linearly with window X position.
     */
    static final Transform WITH_X = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, 1);

    /**
     * Vertically transforms linearly with window Y position.
     */
    static final Transform WITH_Y = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);

    /**
     * Defines the centering height to apply to ParaShapes to be centered to the screen.
     */
    static final double Y_CENTERING_HEIGHT = 60;

    /**
     * Defines the height to apply to ParaShapes to appear at the bottom of the screen.
     */
    static final double HEADER_HEIGHT = 40;

    /**
     * Defines the width to apply to ParaShapes to appear on the right side of the screen.
     */
    static final double WIDTH_DIFFERENCE = 17;

    private static final int ONE_HUNDRED_PIXELS = 100;
    private static final int TEN_PIXELS = 10;
    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double TEXT_HEIGHT = 60;
    private static final double TWO_LINE = 10;
    private static final double TEXT_WIDTH = 250;
    private static final double TEXT_OFFSET = 15;
    private static final double BUTTON_DIMENSION = 50;
    private static final double TEXT_BUTTON_DIFFERENCE = 2.5;
    private static final double THIRD = 3;
    private static final double HALF = 0.5;

    private static final Transform SPIN_Y = new Transform(TransformType.ROTATE,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform SPIN_X = new Transform(TransformType.ROTATE,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
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

    private Levels() {
    }

    /**
     * Initializes the levels as a linked list.
     * @param root the root as a Pane Object
     * @return the levels as a Linked List containing Level
     */
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

        ParaButton button = new ParaButton(
                INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + TEXT_BUTTON_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_BUTTON_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;
        final double offsetAngle = Math.floor(INIT_X) + Math.floor(INIT_Y);

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, -offsetAngle, Color.BLACK);
        textBG.addTransform(SPIN_Y);
        textBG.addTransform(SPIN_X);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, -offsetAngle,
                "\nFind the \"Next Level\" button.");
        welcome.addTransform(SPIN_Y);
        welcome.addTransform(SPIN_X);
        level.addShape(welcome);

        return level;
    }

    private static Level levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        ParaButton button = new ParaButton(INIT_X + INIT_WIDTH / 2
                - TEXT_WIDTH / 2 + TEXT_BUTTON_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_BUTTON_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
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

    private static Level levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        ParaButton button = new ParaButton(INIT_X + INIT_WIDTH / 2
                - TEXT_WIDTH / 2 + TEXT_BUTTON_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_BUTTON_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2
                - TEXT_WIDTH / 2 - INIT_WIDTH;
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

    private static Level levelFour(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        ParaButton button = new ParaButton(INIT_X + INIT_WIDTH / 2
                - TEXT_WIDTH / 2 + TEXT_BUTTON_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_BUTTON_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        double offsetX = Math.cos(INIT_WIDTH / Math.TAU / TEN_PIXELS) * ONE_HUNDRED_PIXELS;
        double offsetY = Math.sin(INIT_WIDTH / Math.TAU / TEN_PIXELS) * ONE_HUNDRED_PIXELS;
        double textBoxX = INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2 + offsetX;
        double textBoxY = INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                - TEXT_HEIGHT / 2 - offsetY;

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



    private static Level levelFive(final Pane root) {
        final double initWidth = 500;
        final double initHeight = 600;
        final double initY = Driver.SCREEN_HEIGHT / 2 - initHeight / 2;
        final double initX = Driver.SCREEN_WIDTH / 2 - initWidth / 2;

        Level level = new Level(initX, initY, initWidth, initHeight);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        double textBoxX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = initY + (initHeight - Y_CENTERING_HEIGHT) / THIRD - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "It's definitely behind those boxes down there");
        level.addShape(welcome);

        double buttonX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
        double buttonY = initY + 2 * (initHeight - Y_CENTERING_HEIGHT) / THIRD
                - BUTTON_DIMENSION / 2;

        ParaButton button = new ParaButton(buttonX, buttonY,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        ParaRectangle red = new ParaRectangle(0, buttonX - TEXT_WIDTH / 2 + initX, buttonY,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(AGAINST_WIDEN);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, buttonX
                - BUTTON_DIMENSION - initX, buttonY - TEXT_HEIGHT,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(WITH_WIDEN);
        level.addShape(blue);

        return level;
    }

    private static void addBlueLevelSix(double buttonX, double buttonY, double initHeight, Level level) {
        ParaRectangle blue = new ParaRectangle(0, buttonX - BUTTON_DIMENSION
                - initHeight / 2, buttonY - TEXT_HEIGHT,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        Transform slowHeight = new Transform(TransformType.HORIZONTAL,
                TransformSource.HEIGHT, TransformPath.LINEAR, HALF);
        blue.addTransform(slowHeight);
        level.addShape(blue);
    }

    private static Level levelSix(final Pane root) {
        final double initWidth = 500;
        final double initHeight = 600;
        final double initY = Driver.SCREEN_HEIGHT / 2 - initHeight / 2;
        final double initX = Driver.SCREEN_WIDTH / 2 - initWidth / 2;
        Level level = new Level(initX, initY, initWidth, initHeight);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        double textBoxX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
        double textBoxY = initY + (initHeight - Y_CENTERING_HEIGHT) / THIRD - TEXT_HEIGHT / 2;

        ParaShape textBG = new ParaRectangle(2, textBoxX, textBoxY,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, textBoxX, textBoxY + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "I can smell it, it's 100% still below two boxes.");
        level.addShape(welcome);

        double buttonX = initX + (initWidth - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
        double buttonY = initY
                + 2 * (initHeight - Y_CENTERING_HEIGHT) / THIRD - BUTTON_DIMENSION / 2;

        ParaButton button = new ParaButton(buttonX, buttonY,
                BUTTON_DIMENSION, BUTTON_DIMENSION,
                0, ParaButtonType.NEXT_LEVEL, root);
        level.addShape(button);

        ParaRectangle red = new ParaRectangle(0, initY / 2 + BUTTON_DIMENSION, buttonY,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -HALF);
        red.addTransform(slowDescent);
        level.addShape(red);

        addBlueLevelSix(buttonX, buttonY, initHeight, level);

        return level;
    }
}
