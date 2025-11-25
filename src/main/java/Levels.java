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
    private static final double TWO_LINE = 10;
    private static final double TEXT_OFFSET = 15;
    private static final double THIRD = 3;
    private static final double HALF = 0.5;
    private static final double BUTTON_DIMENSION = 50;
    private static final double TEXT_BUTTON_DIFFERENCE = 2.5;

    private static final double INIT_WIDTH = 300;
    private static final double INIT_HEIGHT = 300;
    private static final double INIT_Y = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
    private static final double INIT_X = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double TEXT_HEIGHT = 60;
    private static final double TEXT_WIDTH = 250;
    private static final double TEXT_BOX_X = INIT_X
            + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
    private static final double TEXT_BOX_Y = INIT_Y
            + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;

    private static final double INIT_WIDTH_MAIN = 500;
    private static final double INIT_HEIGHT_MAIN = 600;
    private static final double INIT_Y_MAIN = Driver.SCREEN_HEIGHT / 2 - INIT_HEIGHT_MAIN / 2;
    private static final double INIT_X_MAIN = Driver.SCREEN_WIDTH / 2 - INIT_WIDTH_MAIN / 2;
    private static final double TEXT_BOX_X_MAIN = INIT_X_MAIN
            + (INIT_WIDTH_MAIN - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
    private static final double TEXT_BOX_Y_MAIN = INIT_Y_MAIN
            + (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THIRD - TEXT_HEIGHT / 2;
    private static final double BUTTON_X_MAIN = INIT_X_MAIN
            + (INIT_WIDTH_MAIN - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
    private static final double BUTTON_Y_MAIN = INIT_Y_MAIN
            + 2 * (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THIRD - BUTTON_DIMENSION / 2;

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
        levels.add(finish());
        return levels;
    }

    private static ParaButton tutorialButton(final Pane root) {
        return new ParaButton(INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + TEXT_BUTTON_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_BUTTON_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText tutorialText(final String text) {
        return new ParaText(0, TEXT_BOX_X, TEXT_BOX_Y + TEXT_OFFSET,
                TEXT_WIDTH, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle tutorialTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X, TEXT_BOX_Y,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static Level levelOne(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SPIN_Y);
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SPIN_X);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nFind the \"Next Level\" button.");
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SPIN_Y);
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SPIN_X);
        level.addShape(welcome);

        return level;
    }

    private static Level levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, AGAINST_MOVE_Y);
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, AGAINST_MOVE_X);
        level.addShape(textBG);

        ParaText welcome = tutorialText("These Red lines indicate that you cannot WIDEN your window");
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, AGAINST_MOVE_Y);
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, AGAINST_MOVE_X);
        level.addShape(welcome);

        return level;
    }

    private static Level levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, HORIZONTAL_HEIGHT);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nWhat does this black line do?");
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, HORIZONTAL_HEIGHT);
        level.addShape(welcome);

        return level;
    }

    private static Level levelFour(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SIN_T);
        textBG.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, COS_T);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nSpinny!");
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, SIN_T);
        welcome.addTransform(INIT_HEIGHT, INIT_WIDTH, INIT_X, INIT_Y, COS_T);
        level.addShape(welcome);

        return level;
    }

    private static ParaButton mainButton(final Pane root) {
        return new ParaButton(BUTTON_X_MAIN, BUTTON_Y_MAIN,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText mainText(final String text) {
        return new ParaText(0, TEXT_BOX_X_MAIN, TEXT_BOX_Y_MAIN + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle mainTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X_MAIN, TEXT_BOX_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static Level levelFive(final Pane root) {

        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("It's below those two boxes."));

        ParaRectangle red = new ParaRectangle(0, BUTTON_X_MAIN - TEXT_WIDTH / 2, BUTTON_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(INIT_HEIGHT_MAIN, INIT_WIDTH_MAIN, INIT_X_MAIN, INIT_Y_MAIN, WITH_WIDEN);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, BUTTON_X_MAIN
                - BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_HEIGHT,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(INIT_HEIGHT_MAIN, INIT_WIDTH_MAIN, INIT_X_MAIN, INIT_Y_MAIN, AGAINST_WIDEN);
        level.addShape(blue);

        return level;
    }

    private static Level levelSix(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("I can smell it, it's 100% still below those two boxes."));

        ParaRectangle red = new ParaRectangle(0, BUTTON_DIMENSION, BUTTON_Y_MAIN,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -HALF);
        red.addTransform(INIT_HEIGHT_MAIN, INIT_WIDTH_MAIN, INIT_X_MAIN, INIT_Y_MAIN, slowDescent);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_HEIGHT,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        Transform slowHeight = new Transform(TransformType.HORIZONTAL,
                TransformSource.HEIGHT, TransformPath.LINEAR, HALF);
        blue.addTransform(INIT_HEIGHT_MAIN, INIT_WIDTH_MAIN, INIT_X_MAIN, INIT_Y_MAIN, slowHeight);
        level.addShape(blue);

        return level;
    }

    private static Level finish() {

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        ParaShape textBG = new ParaRectangle(2, TEXT_BOX_X, TEXT_BOX_Y,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        level.addShape(textBG);

        ParaText welcome = new ParaText(0, TEXT_BOX_X, TEXT_BOX_Y + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH, TEXT_HEIGHT, 0, "You Win!\nThank you for playing!");
        level.addShape(welcome);

        return level;
    }
}
