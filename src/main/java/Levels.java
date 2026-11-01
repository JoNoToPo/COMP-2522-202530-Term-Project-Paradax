import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Deque;

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
     * Defines the height to apply to ParaShapes to appear at the bottom of the screen.
     */
    static final double HEADER_HEIGHT = WindowFrame.getHeight() + Scale.height(0.26);

    /**
     * Defines the width to apply to ParaShapes to appear on the right side of the screen.
     */
    static final double WIDTH_DIFFERENCE = WindowFrame.getWidth() + Scale.width(0.17);

    /**
     * Defines the centering height to apply to ParaShapes to be centered to the screen.
     */
    static final double Y_CENTERING_HEIGHT = HEADER_HEIGHT + Scale.height(2.19);

    private static final double THREE = 3;
    private static final double FOUR = 4;
    private static final double HALF = 0.5;
    private static final double TWO_AND_A_HALF = 2.5;
    private static final double FORTY_FIVE_DEGREES = 45;
    private static final double ELEMENT_OPACITY = 0.7;

    // The levels were designed on a 1536 x 912 screen. These keep transforms that turn one
    // direction of the window into another direction of movement feeling the same as they did.
    private static final double HEIGHT_TO_WIDTH = 0.594;
    private static final double WIDTH_TO_HEIGHT = 1.684;
    private static final double DEGREES_PER_WIDTH_PERCENT = 15.36;
    private static final double DEGREES_PER_HEIGHT_PERCENT = 9.12;
    private static final double WAVE_WIDTH_PERCENT = 6.51;
    private static final double WAVE_HEIGHT_PERCENT = 10.96;

    private static final double TWO_LINE = Scale.height(1.10);
    private static final double TEXT_OFFSET = Scale.height(1.64);
    private static final double BUTTON_WIDTH = Scale.width(3.26);
    private static final double BUTTON_HEIGHT = Scale.height(5.48);
    private static final double TEXT_PADDING_X = Scale.width(0.16);
    private static final double TEXT_PADDING_Y = Scale.height(0.27);

    private static final double INIT_WIDTH = Scale.width(19.53);
    private static final double INIT_HEIGHT = Scale.height(32.89);
    private static final double INIT_Y = Scale.SCREEN_HEIGHT / 2 - INIT_HEIGHT / 2;
    private static final double INIT_X = Scale.SCREEN_WIDTH / 2 - INIT_WIDTH / 2;
    private static final double TEXT_HEIGHT = Scale.height(6.58);
    private static final double TEXT_WIDTH = Scale.width(16.28);
    private static final double TEXT_BOX_X = INIT_X
            + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
    private static final double TEXT_BOX_Y = INIT_Y
            + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2 - TEXT_HEIGHT / 2;

    private static final double INIT_WIDTH_MAIN = Scale.width(32.55);
    private static final double INIT_HEIGHT_MAIN = Scale.height(65.79);
    private static final double INIT_Y_MAIN = Scale.SCREEN_HEIGHT / 2 - INIT_HEIGHT_MAIN / 2;
    private static final double INIT_X_MAIN = Scale.SCREEN_WIDTH / 2 - INIT_WIDTH_MAIN / 2;
    private static final double TEXT_BOX_X_MAIN = INIT_X_MAIN
            + (INIT_WIDTH_MAIN - WIDTH_DIFFERENCE) / 2 - TEXT_WIDTH / 2;
    private static final double TEXT_BOX_Y_MAIN = INIT_Y_MAIN
            + (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THREE - TEXT_HEIGHT / 2;
    private static final double BUTTON_X_MAIN = INIT_X_MAIN
            + (INIT_WIDTH_MAIN - WIDTH_DIFFERENCE) / 2 - BUTTON_WIDTH / 2;
    private static final double BUTTON_Y_MAIN = INIT_Y_MAIN
            + 2 * (INIT_HEIGHT_MAIN - Y_CENTERING_HEIGHT) / THREE - BUTTON_HEIGHT / 2;

    private static final Transform WITH_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);
    private static final Transform AGAINST_WIDEN = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.LINEAR, -1);
    private static final Transform WITH_LENGTHEN = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);
    private static final Transform AGAINST_LENGTHEN = new Transform(TransformType.VERTICAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, -1);
    private static final Transform SPIN_Y = new Transform(TransformType.ROTATE,
            TransformSource.Y_POS, TransformPath.LINEAR, DEGREES_PER_HEIGHT_PERCENT);
    private static final Transform SPIN_X = new Transform(TransformType.ROTATE,
            TransformSource.X_POS, TransformPath.LINEAR, DEGREES_PER_WIDTH_PERCENT);
    private static final Transform SPIN_WIDTH = new Transform(TransformType.ROTATE,
            TransformSource.WIDTH, TransformPath.LINEAR, HALF * DEGREES_PER_WIDTH_PERCENT);
    private static final Transform SPIN_HEIGHT = new Transform(TransformType.ROTATE,
            TransformSource.HEIGHT, TransformPath.LINEAR, HALF * DEGREES_PER_HEIGHT_PERCENT);
    private static final Transform HORIZONTAL_HEIGHT = new Transform(TransformType.HORIZONTAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, HEIGHT_TO_WIDTH);
    private static final Transform AGAINST_MOVE_Y = new Transform(TransformType.VERTICAL,
            TransformSource.Y_POS, TransformPath.LINEAR, -1);
    private static final Transform AGAINST_MOVE_X = new Transform(TransformType.HORIZONTAL,
            TransformSource.X_POS, TransformPath.LINEAR, -1);
    private static final Transform SIN_T = new Transform(TransformType.VERTICAL,
            TransformSource.WIDTH, TransformPath.SINUSOIDAL, WAVE_HEIGHT_PERCENT);
    private static final Transform COS_T = new Transform(TransformType.HORIZONTAL,
            TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -WAVE_WIDTH_PERCENT);
    private static final Transform SLOW_HEIGHT = new Transform(TransformType.HORIZONTAL,
            TransformSource.HEIGHT, TransformPath.LINEAR, HALF * HEIGHT_TO_WIDTH);
    private static final Transform VERT_WITH_WIDEN = new Transform(TransformType.VERTICAL,
            TransformSource.WIDTH, TransformPath.LINEAR, WIDTH_TO_HEIGHT);

    private Levels() {
    }

    /**
     * Initializes the levels as a linked list.
     * @param pane the pane as a Pane Object
     * @return the levels as a Linked List containing Level
     */
    static Deque<LevelInterface> levels(final Pane pane) {
        Deque<LevelInterface> levels = new LinkedList<>();
        levels.add(levelOne(pane));
        levels.add(tutorialWiden(pane));
        levels.add(tutorialLengthen(pane));
        levels.add(levelTwo(pane));
        levels.add(tutorialYFixed(pane));
        levels.add(tutorialXFixed(pane));
        levels.add(levelThree(pane));
        levels.add(levelFour(pane));
        levels.add(levelFive(pane));
        levels.add(levelSix(pane));
        levels.add(levelSeven(pane));
        levels.add(levelEight(pane));
        levels.add(levelNine(pane));
        levels.add(levelTen(pane));
        levels.add(levelEleven(pane));
        levels.add(finalLevel(pane));
        levels.add(finish(pane));
        return levels;
    }

    static ParaButton continueButton(final Pane root) {
        return new ParaButton(INIT_X + INIT_WIDTH - WIDTH_DIFFERENCE - BUTTON_WIDTH * 2,
                INIT_Y + INIT_HEIGHT - HEADER_HEIGHT - BUTTON_HEIGHT,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT, 0, ParaButtonType.BOOL_SWITCH, root);
    }

    private static ParaButton tutorialButton(final Pane root) {
        return new ParaButton(INIT_X + INIT_WIDTH / 2 - TEXT_WIDTH / 2 + TEXT_PADDING_X,
                INIT_Y + (INIT_HEIGHT - Y_CENTERING_HEIGHT) / 2
                        - TEXT_HEIGHT / 2 + TEXT_PADDING_Y,
                BUTTON_WIDTH, BUTTON_HEIGHT, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText tutorialText(final String text) {
        return new ParaText(TEXT_BOX_X + TEXT_PADDING_X, TEXT_BOX_Y + TEXT_OFFSET,
                TEXT_WIDTH - TEXT_PADDING_X * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaText tutorialTextTwoLine(final String text) {
        return new ParaText(TEXT_BOX_X + TEXT_PADDING_X, TEXT_BOX_Y + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH - TEXT_PADDING_X * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle tutorialTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X, TEXT_BOX_Y,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static ParaButton mainButton(final Pane root) {
        return new ParaButton(BUTTON_X_MAIN, BUTTON_Y_MAIN,
                BUTTON_WIDTH, BUTTON_HEIGHT, 0,
                ParaButtonType.NEXT_LEVEL, root);
    }

    private static ParaText mainText(final String text) {
        return new ParaText(TEXT_BOX_X_MAIN + TEXT_PADDING_X, TEXT_BOX_Y_MAIN + TEXT_OFFSET,
                TEXT_WIDTH - TEXT_PADDING_X * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaText mainTextTwoLine(final String text) {
        return new ParaText(TEXT_BOX_X_MAIN + TEXT_PADDING_X,
                TEXT_BOX_Y_MAIN + TEXT_OFFSET + TWO_LINE,
                TEXT_WIDTH - TEXT_PADDING_X * 2, TEXT_HEIGHT, 0, text);
    }

    private static ParaRectangle mainTextBox() {
        return new ParaRectangle(2, TEXT_BOX_X_MAIN, TEXT_BOX_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT, 0, Color.BLACK);
    }

    private static ParaRectangle newRedMain() {
        ParaRectangle red = new ParaRectangle(0, BUTTON_WIDTH, BUTTON_Y_MAIN,
                Scale.SCREEN_WIDTH / 2, BUTTON_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        return red;
    }

    private static ParaRectangle newBlueMain() {
        ParaRectangle blue = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_WIDTH, BUTTON_Y_MAIN - BUTTON_HEIGHT,
                Scale.SCREEN_WIDTH / 2, BUTTON_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.setOpacity(ELEMENT_OPACITY);
        return blue;
    }

    private static ParaRectangle finalBlueBox(final Transform transform) {
        ParaRectangle blue = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_WIDTH * 2, BUTTON_Y_MAIN - BUTTON_HEIGHT,
                Scale.SCREEN_WIDTH, BUTTON_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.addTransform(transform);
        blue.setOpacity(ELEMENT_OPACITY);
        return blue;
    }

    static Level intro(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        ParaButton newGame = new ParaButton(INIT_X,
                INIT_Y + INIT_HEIGHT - HEADER_HEIGHT - BUTTON_HEIGHT,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT, 0, ParaButtonType.NEXT_LEVEL, root);
        newGame.changeText("Start Game");
        level.addShape(newGame);

        ParaShape textBG = tutorialTextBox();
        level.addShape(textBG);

        ParaText welcome = tutorialText("\nWelcome to Paradax!");
        level.addShape(welcome);

        level.initTransforms();
        return level;
    }

    private static LevelInterface levelOne(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(SPIN_Y);
        textBG.addTransform(SPIN_X);
        textBG.addTransform(SPIN_WIDTH);
        textBG.addTransform(SPIN_HEIGHT);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("Think at the boundaries of the box to"
                + " find the \"Next Level\" button.");
        welcome.addTransform(SPIN_Y);
        welcome.addTransform(SPIN_X);
        welcome.addTransform(SPIN_WIDTH);
        welcome.addTransform(SPIN_HEIGHT);
        level.addShape(welcome);

        level.initTransforms();
        return level;
    }

    private static LevelInterface tutorialWiden(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(WITH_LENGTHEN);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("These Red lines indicate"
                + " that you cannot WIDEN your window");
        welcome.addTransform(WITH_LENGTHEN);
        level.addShape(welcome);

        level.initTransforms();
        return level;
    }

    private static LevelInterface tutorialLengthen(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(AGAINST_MOVE_Y);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("These Red lines indicate that you"
                + " cannot LENGTHEN the HEIGHT your window");
        welcome.addTransform(AGAINST_MOVE_Y);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static LevelInterface levelTwo(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        ParaButton button = tutorialButton(root);
        button.changeInitPosition(INIT_X_MAIN + INIT_WIDTH_MAIN, INIT_Y_MAIN + INIT_Y_MAIN);
        Transform buttonExpoSpin = new Transform(TransformType.ROTATE, TransformSource.X_POS,
                TransformPath.EXPONENTIAL, DEGREES_PER_WIDTH_PERCENT * DEGREES_PER_WIDTH_PERCENT);
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

    private static LevelInterface tutorialYFixed(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(tutorialButton(root));

        ParaShape textBG = tutorialTextBox();
        textBG.addTransform(WITH_X);
        level.addShape(textBG);

        ParaText welcome = tutorialTextTwoLine("This black line fixes"
                + " your window to the Y position of your screen");
        welcome.addTransform(WITH_X);
        level.addShape(welcome);
        level.initTransforms();
        return level;
    }

    private static LevelInterface tutorialXFixed(final Pane root) {
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

    private static LevelInterface levelThree(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        ParaButton button = mainButton(root);
        button.changeInitPosition(TEXT_BOX_X + TEXT_WIDTH / 2 - BUTTON_WIDTH / 2
                + TEXT_PADDING_X, TEXT_BOX_Y + TEXT_PADDING_Y);
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

    private static LevelInterface levelFour(final Pane root) {
        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.X_POSITION);

        ParaButton button = tutorialButton(root);
        button.changeInitPosition(INIT_X + INIT_WIDTH / 2,
                Scale.SCREEN_HEIGHT - BUTTON_HEIGHT / 2);
        button.addTransform(VERT_WITH_WIDEN);
        level.addShape(button);

        addColdBox(level);
        addHotBox(level);

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

    private static void addHotBox(final Level level) {
        ParaRectangle hotBG = new ParaRectangle(0, INIT_X + INIT_WIDTH,
                Scale.SCREEN_HEIGHT - BUTTON_HEIGHT * FOUR - BUTTON_HEIGHT / 2,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                0, Color.WHITE);
        ParaText hot = new ParaText(INIT_X + INIT_WIDTH,
                Scale.SCREEN_HEIGHT - BUTTON_HEIGHT * FOUR, BUTTON_WIDTH, BUTTON_HEIGHT,
                0, "Hot", Color.DARKRED);
        hot.addTransform(VERT_WITH_WIDEN);
        hotBG.addTransform(VERT_WITH_WIDEN);
        level.addShape(hotBG);
        level.addShape(hot);
    }

    private static void addColdBox(final Level level) {
        ParaText cold = new ParaText(Scale.SCREEN_WIDTH - TEXT_WIDTH, INIT_Y + BUTTON_HEIGHT,
                BUTTON_WIDTH, BUTTON_HEIGHT, FORTY_FIVE_DEGREES * 2, "Cold", Color.DARKBLUE);
        ParaRectangle coldBG = new ParaRectangle(0, Scale.SCREEN_WIDTH - TEXT_WIDTH,
                INIT_Y + BUTTON_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT,
                0, Color.WHITE);
        Transform slowWithWidth = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, 1 / FOUR);
        cold.addTransform(slowWithWidth);
        coldBG.addTransform(slowWithWidth);
        level.addShape(coldBG);
        level.addShape(cold);
    }

    private static LevelInterface levelFive(final Pane root) {

        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("I wonder where the next level button is."
                + " On an unrelated note, look at those slightly transparent boxes!"));

        ParaRectangle red = new ParaRectangle(0, BUTTON_X_MAIN - TEXT_WIDTH / 2, BUTTON_Y_MAIN,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        red.addTransform(WITH_WIDEN);
        level.addShape(red);

        ParaRectangle blue = new ParaRectangle(0, BUTTON_X_MAIN
                - BUTTON_WIDTH, BUTTON_Y_MAIN - TEXT_HEIGHT,
                TEXT_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKBLUE);
        blue.setOpacity(ELEMENT_OPACITY);
        blue.addTransform(AGAINST_MOVE_X);
        level.addShape(blue);
        level.initTransforms();
        return level;
    }

    private static LevelInterface levelSix(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("Whaaaat it was behind the two"
                + " slightly transparent boxes? no waaaaay"));

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

    private static LevelInterface levelSeven(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);
        level.addFixedLevelType(FixedLevelType.HEIGHT);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("Can you believe it?\nAnother two boxes."));

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

    private static LevelInterface levelEight(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("I hope you're invested so far,"
                + "\nbecause next level will use some unexpected emergent gameplay"));

        ParaRectangle red = newRedMain();
        Transform slowDescent = new Transform(TransformType.HORIZONTAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -HALF * HEIGHT_TO_WIDTH);
        red.addTransform(slowDescent);
        level.addShape(red);

        ParaRectangle blue = newBlueMain();
        blue.addTransform(SLOW_HEIGHT);
        level.addShape(blue);

        level.initTransforms();
        return level;
    }

    private static LevelInterface levelNine(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nI can promise you this level IS possible"));

        ParaRectangle red = new ParaRectangle(0, THREE * BUTTON_WIDTH + TEXT_WIDTH / 2,
                BUTTON_Y_MAIN - BUTTON_HEIGHT / 2,
                Scale.SCREEN_WIDTH / 2, BUTTON_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        Transform width = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.LINEAR, HALF);
        red.addTransform(width);
        level.addShape(red);

        level.initTransforms();
        return level;
    }

    private static LevelInterface levelTen(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("How did that happen?\nMaybe you can do it again?"));

        ParaRectangle red = new ParaRectangle(0,
                TWO_AND_A_HALF * BUTTON_WIDTH + TEXT_WIDTH,
                BUTTON_Y_MAIN - BUTTON_HEIGHT / 2,
                Scale.SCREEN_WIDTH / 2, BUTTON_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        Transform width = new Transform(TransformType.HORIZONTAL,
                TransformSource.HEIGHT, TransformPath.LINEAR, HEIGHT_TO_WIDTH);
        red.addTransform(width);
        level.addShape(red);

        level.initTransforms();
        return level;
    }

    private static LevelInterface levelEleven(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainText("\nHere's some breathing room."));

        ParaRectangle red = new ParaRectangle(0, -Scale.SCREEN_WIDTH / 2 + BUTTON_WIDTH,
                BUTTON_Y_MAIN - TEXT_PADDING_Y,
                Scale.SCREEN_WIDTH, BUTTON_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        red.addTransform(WITH_X);
        level.addShape(red);
        level.addShape(finalBlueBox(AGAINST_WIDEN));

        level.initTransforms();
        return level;
    }

    private static LevelInterface finalLevel(final Pane root) {
        Level level = new Level(INIT_X_MAIN, INIT_Y_MAIN, INIT_WIDTH_MAIN, INIT_HEIGHT_MAIN);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        level.addShape(mainButton(root));
        level.addShape(mainTextBox());
        level.addShape(mainTextTwoLine("It's time for your final test... THREE BOXES."));

        ParaRectangle red = new ParaRectangle(0, -Scale.SCREEN_WIDTH / 2 + BUTTON_WIDTH / 2,
                BUTTON_Y_MAIN - TEXT_PADDING_Y,
                Scale.SCREEN_WIDTH, TEXT_HEIGHT * 2, 0, Color.DARKRED);
        red.setOpacity(ELEMENT_OPACITY);
        red.addTransform(WITH_WIDEN);
        level.addShape(red);

        level.addShape(finalBlueBox(SLOW_HEIGHT));

        ParaRectangle green = new ParaRectangle(0,
                BUTTON_X_MAIN - BUTTON_WIDTH,
                BUTTON_Y_MAIN - TEXT_HEIGHT + BUTTON_HEIGHT / 2,
                BUTTON_WIDTH * 2, Scale.SCREEN_HEIGHT, 0, Color.GREEN);
        Transform slow = new Transform(TransformType.VERTICAL,
                TransformSource.X_POS, TransformPath.LINEAR, -HALF * WIDTH_TO_HEIGHT);
        green.setOpacity(ELEMENT_OPACITY);
        green.addTransform(slow);
        level.addShape(green);
        level.initTransforms();
        return level;
    }

    private static LevelInterface finish(final Pane root) {

        Level level = new Level(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT);
        level.addFixedLevelType(FixedLevelType.HEIGHT);
        level.addFixedLevelType(FixedLevelType.X_POSITION);
        level.addFixedLevelType(FixedLevelType.WIDTH);
        level.addFixedLevelType(FixedLevelType.Y_POSITION);

        ParaButton button = new ParaButton(
                INIT_X + (INIT_WIDTH - WIDTH_DIFFERENCE) / 2 - BUTTON_WIDTH,
                INIT_Y + INIT_HEIGHT / 2 + BUTTON_HEIGHT / 2,
                BUTTON_WIDTH * 2, BUTTON_HEIGHT,
                0, ParaButtonType.NEXT_LEVEL, root);
        button.changeText("Play again?");

        level.addShape(button);
        level.addShape(tutorialTextBox());
        level.addShape(tutorialTextTwoLine("You Win!\nThank you for playing!"));
        level.initTransforms();
        return level;
    }
}
