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
    private static final double THREE = 3;
    private static final double FOUR = 4;
    private static final double HALF = 0.5;
    private static final double BUTTON_DIMENSION = 50;
    private static final double TEXT_X_DIFFERENCE = 2.5;
    private static final double FORTY_FIVE_DEGREES = 45;

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
            + (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THREE - TEXT_HEIGHT / 2;
    private static final double BUTTON_X_MAIN = INIT_X_MAIN
            + (INIT_WIDTH_MAIN - WIDTH_DIFFERENCE) / 2 - BUTTON_DIMENSION / 2;
    private static final double BUTTON_Y_MAIN = INIT_Y_MAIN
            + 2 * (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THREE - BUTTON_DIMENSION / 2;

    private static final Transform WITH_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);
    private static final Transform AGAINST_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, -1);
    private static final Transform WITH_LENGTHEN = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    private static final Transform AGAINST_LENGTHEN = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, -1);
    private static final Transform SPIN_Y = new Transform(TransformType.ROTATE,
            TransformSource.Y_POS, TransformPath.LINEAR, 1);
    private static final Transform SPIN_X = new Transform(TransformType.ROTATE,
            TransformSource.X_POS, TransformPath.LINEAR, 1);
    private static final Transform SPIN_WIDTH = new Transform(TransformType.ROTATE,
            TransformSource.WIDTH, TransformPath.LINEAR, HALF);
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
    private static final Transform SLOW_HEIGHT = new Transform(TransformType.HORIZONTAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, HALF);

    private Levels() {
    }

    /**
     * Initializes the levels as a linked list.
     * @param root the root as a Pane Object
     * @return the levels as a Linked List containing Level
     */
    static LinkedList<Level> levels(final Pane root) {
        LinkedList<Level> levels = new LinkedList<>();
//        levels.add(levelOne(root));
//        levels.add(tutorialWiden(root));
//        levels.add(tutorialLengthen(root));
//        levels.add(levelTwo(root));
//        levels.add(tutorialYFixed(root));
//        levels.add(tutorialXFixed(root));
//        levels.add(levelThree(root));
//        levels.add(levelFour(root));
//        levels.add(levelFive(root));
//        levels.add(levelSix(root));
//        levels.add(levelSeven(root));
//        levels.add(levelEight(root));
//        levels.add(levelNine(root));
//        levels.add(levelTen(root));
//        levels.add(levelEleven(root));
        levels.add(finalLevel(root));
        levels.add(finish());
        return levels;
    }

    private static ParaButton tutorialButton(final Pane root) {
        return new ParaButton(INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + TEXT_X_DIFFERENCE,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_X_DIFFERENCE,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText tutorialText(final String text) {
        return new ParaText(0, TEXT_BOX_X + TEXT_X_DIFFERENCE, TEXT_BOX_Y + TEXT_OFFSET,
                TEXT_WIDTH - TEXT_X_DIFFERENCE * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaText tutorialTextTwoLine(final String text) {
        return new ParaText(0, TEXT_BOX_X + TEXT_X_DIFFERENCE, TEXT_BOX_Y + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH - TEXT_X_DIFFERENCE * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle tutorialTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X, TEXT_BOX_Y,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static ParaButton mainButton(final Pane root) {
        return new ParaButton(BUTTON_X_MAIN, BUTTON_Y_MAIN,
                BUTTON_DIMENSION, BUTTON_DIMENSION, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText mainText(final String text) {
        return new ParaText(0, TEXT_BOX_X_MAIN + TEXT_X_DIFFERENCE, TEXT_BOX_Y_MAIN + TEXT_OFFSET,
                TEXT_WIDTH - TEXT_X_DIFFERENCE * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaText mainTextTwoLine(final String text) {
        return new ParaText(0, TEXT_BOX_X_MAIN + TEXT_X_DIFFERENCE,
                TEXT_BOX_Y_MAIN + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH - TEXT_X_DIFFERENCE * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle mainTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X_MAIN, TEXT_BOX_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static ParaRectangle newRedMain() {
        return new ParaRectangle(0, BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_X_DIFFERENCE,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
    }

    private static ParaRectangle newBlueMain() {
        return new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_HEIGHT,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
    }

    private static ParaRectangle finalBlueBox(Transform transform) {
        ParaRectangle blue = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_DIMENSION * 2, BUTTON_Y_MAIN - TEXT_HEIGHT,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(transform);
        return blue;
    }

    private static Level levelOne(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(SPIN_Y);
        textBG.addTransform(SPIN_X);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("Think at the boundaries of the box to find the \"Next Level\" button.");
        welcome.addTransform(SPIN_Y);
        welcome.addTransform(SPIN_X);
        level.addShape(welcome);

        level.initTransforms();
        return level;
    }

    private static Level tutorialWiden(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(WITH_LENGTHEN);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("These Red lines indicate that you cannot WIDEN your window");
        welcome.addTransform(WITH_LENGTHEN);
        level.addShape(welcome);

        level.initTransforms();
        return level;
    }

    private static Level tutorialLengthen(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(AGAINST_MOVE_Y);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("These Red lines indicate that you cannot LENGTHEN the HEIGHT your window");
        welcome.addTransform(AGAINST_MOVE_Y);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        ParaButton button = tutorialButton(root);
        button.changePosition(INIT_X_MAIN + INIT_WIDTH_MAIN, INIT_Y_MAIN + INIT_Y_MAIN);
        Transform buttonExpoSpin = new Transform(TransformType.ROTATE, TransformSource.X_POS,
                TransformPath.EXPONENTIAL, 1);
        button.addTransform(buttonExpoSpin);
        level.addShape(button);

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(AGAINST_MOVE_Y);
        textBG.addTransform(AGAINST_MOVE_X);
        textBG.addTransform(SIN_T);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nWhere is it?");
        welcome.addTransform(AGAINST_MOVE_Y);
        welcome.addTransform(AGAINST_MOVE_X);
        welcome.addTransform(COS_T);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level tutorialYFixed(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(WITH_X);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("This black line fixes your window to the Y position of your screen");
        welcome.addTransform(WITH_X);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level tutorialXFixed(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(HORIZONTAL_HEIGHT);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nWhat does this black line do?");
        welcome.addTransform(HORIZONTAL_HEIGHT);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        ParaButton button = mainButton(root);
        button.changePosition(TEXT_BOX_X + TEXT_WIDTH / 2 - BUTTON_DIMENSION / 2 + TEXT_X_DIFFERENCE,
                TEXT_BOX_Y + TEXT_X_DIFFERENCE);
        level.addShape(button);

        ParaShape textBG = new ParaRectangle(2, TEXT_BOX_X, TEXT_BOX_Y,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
        textBG.addTransform(SIN_T);
        textBG.addTransform(COS_T);
        textBG.addTransform(SPIN_WIDTH);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nSpinny!");
        welcome.addTransform(SIN_T);
        welcome.addTransform(COS_T);
        welcome.addTransform(SPIN_WIDTH);

        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level levelFour(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        ParaButton button = tutorialButton(root);
        button.changePosition(INIT_X + INIT_WIDTH / 2, Driver.SCREEN_HEIGHT - BUTTON_DIMENSION / 2);
        Transform vertWithWiden = new Transform(TransformType.VERTICAL, TransformSource.WIDTH,
                TransformPath.LINEAR, 1);
        button.addTransform(vertWithWiden);
        level.addShape(button);

        ParaText cold = new ParaText(Driver.SCREEN_WIDTH - TEXT_WIDTH, INIT_Y + BUTTON_DIMENSION,
                BUTTON_DIMENSION, BUTTON_DIMENSION, FORTY_FIVE_DEGREES * 2, "Cold", Color.DARKBLUE);
        ParaRectangle coldBG = new ParaRectangle(0, Driver.SCREEN_WIDTH - TEXT_WIDTH,
                INIT_Y + BUTTON_DIMENSION / 2, BUTTON_DIMENSION, BUTTON_DIMENSION,
                0, Color.WHITE);
        Transform slowWithWidth = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, 1 / FOUR);
        cold.addTransform(slowWithWidth);
        coldBG.addTransform(slowWithWidth);
        level.addShape(coldBG);
        level.addShape(cold);

        ParaRectangle hotBG = new ParaRectangle(0, INIT_X + INIT_WIDTH,
                Driver.SCREEN_HEIGHT - BUTTON_DIMENSION * FOUR - BUTTON_DIMENSION / 2,
                BUTTON_DIMENSION, BUTTON_DIMENSION,
                0, Color.WHITE);

        ParaText hot = new ParaText(INIT_X + INIT_WIDTH,
                Driver.SCREEN_HEIGHT - BUTTON_DIMENSION * FOUR, BUTTON_DIMENSION, BUTTON_DIMENSION,
                0, "Hot", Color.DARKRED);
        hot.addTransform(vertWithWiden);
        hotBG.addTransform(vertWithWiden);
        level.addShape(hotBG);
        level.addShape(hot);

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(AGAINST_WIDEN);
        textBG.addTransform(AGAINST_LENGTHEN);
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nWhere is it?");
        welcome.addTransform(AGAINST_WIDEN);
        welcome.addTransform(AGAINST_LENGTHEN);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static Level levelFive(final Pane root) {

        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nIt's behind those two boxes."));

        ParaRectangle red = new ParaRectangle(0, BUTTON_X_MAIN - TEXT_WIDTH / 2, BUTTON_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(WITH_WIDEN);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, BUTTON_X_MAIN
                - BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_HEIGHT,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(AGAINST_MOVE_X);
        level.addShape(blue);
        level.initTransforms();
        return level;
    }

    private static Level levelSix(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nI wonder where it is."));

        ParaRectangle red = newRedMain();
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, -HALF);
        red.addTransform(slowDescent);
        level.addShape(red);

        ParaRectangle blue = newBlueMain();
        blue.addTransform(SLOW_HEIGHT);
        level.addShape(blue);

        level.initTransforms();
        return level;
    }

    private static Level levelSeven(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("I can smell it now, it's 100% still behind those two boxes."));

        ParaRectangle red = newRedMain();
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.X_POS, TransformPath.LINEAR, -HALF);
        red.addTransform(slowDescent);
        level.addShape(red);

        ParaRectangle blue = newBlueMain();
        Transform slowHeight = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, HALF / FOUR);
        blue.addTransform(slowHeight);
        level.addShape(blue);

        level.initTransforms();
        return level;
    }

    private static Level levelEight(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("Can you believe it?\nAnother two boxes."));

        ParaRectangle red = newRedMain();
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -HALF);
        red.addTransform(slowDescent);
        level.addShape(red);

        ParaRectangle blue = newBlueMain();
        blue.addTransform(SLOW_HEIGHT);
        level.addShape(blue);

        level.initTransforms();
        return level;
    }

    private static Level levelNine(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nI can promise you this level IS possible"));

        ParaRectangle red = new ParaRectangle(0, THREE * BUTTON_DIMENSION + TEXT_WIDTH / 2,
                BUTTON_Y_MAIN - BUTTON_DIMENSION / 2,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        Transform width = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, HALF);
        red.addTransform(width);
        level.addShape(red);

        level.initTransforms();
        return level;
    }

    private static Level levelTen(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("How did that happen? \nMaybe you can do it again"));

        ParaRectangle red = new ParaRectangle(0,
                TEXT_X_DIFFERENCE * BUTTON_DIMENSION + TEXT_WIDTH,
                BUTTON_Y_MAIN - BUTTON_DIMENSION / 2,
                Driver.SCREEN_WIDTH / 2, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        Transform width = new Transform(TransformType.HORIZONTAL,
                TransformSource.HEIGHT, TransformPath.LINEAR, 1);
        red.addTransform(width);
        level.addShape(red);

        level.initTransforms();
        return level;
    }

    private static Level levelEleven(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nHere's some breathing room"));

        ParaRectangle red = new ParaRectangle(0, -Driver.SCREEN_WIDTH / 2 + BUTTON_DIMENSION,
                BUTTON_Y_MAIN - TEXT_X_DIFFERENCE,
                Driver.SCREEN_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(WITH_X);
        level.addShape(red);
        level.addShape(finalBlueBox(AGAINST_WIDEN));

        level.initTransforms();
        return level;
    }

    private static Level finalLevel(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("It's time for your final test... THREE BOXES."));

        ParaRectangle red = new ParaRectangle(0, -Driver.SCREEN_WIDTH / 2 + BUTTON_DIMENSION / 2,
                BUTTON_Y_MAIN - TEXT_X_DIFFERENCE,
                Driver.SCREEN_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.addTransform(WITH_WIDEN);
        level.addShape(red);

        level.addShape(finalBlueBox(SLOW_HEIGHT));

        ParaRectangle green = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_DIMENSION, BUTTON_Y_MAIN - TEXT_HEIGHT + BUTTON_DIMENSION / 2,
                TEXT_HEIGHT * 2, Driver.SCREEN_HEIGHT, 0, Color.GREEN);
        Transform slow = new Transform(TransformType.VERTICAL,
                TransformSource.X_POS, TransformPath.LINEAR, -HALF);
        green.addTransform(slow);
        level.addShape(green);
        level.initTransforms();
        return level;
    }

    private static Level finish() {

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(tutorialTextBox());
        level.addShape(tutorialTextTwoLine("You Win!\nThank you for playing!"));
        level.initTransforms();
        return level;
    }
}
