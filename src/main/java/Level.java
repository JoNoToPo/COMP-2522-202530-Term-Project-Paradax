import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a level in Paralax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class Level {
    private static final int FIVE_PIXELS = 5;
    private static final int THREE_PIXELS = 5;
    private final List<ParaShape> paraShapes;
    private final List<FixedLevelType> fixedLevelTypes;
    private final double initX;
    private final double initY;
    private final double initWidth;
    private final double initHeight;

    /**
     * Constructs a level.
     *
     * @param initX the initial X position of the top left of the window as a double
     * @param initY  the initial Y position of the top left of the window as a double
     * @param initWidth the initial width of the screen as a double
     * @param initHeight the initial height of the screen as a double
     */
    public Level(final double initX, final double initY,
                 final double initWidth, final double initHeight) {
        this.paraShapes = new ArrayList<>();
        this.fixedLevelTypes = new ArrayList<>();
        this.initX = initX;
        this.initY = initY;
        this.initWidth = initWidth;
        this.initHeight = initHeight;
    }

    /**
     * Gets the initial X position of the top left of the window.
     *
     * @return the initial X position of the top left of the window as a double
     */
    public double getInitX() {
        return initX;
    }

    /**
     * Gets the initial Y position of the top left of the window.
     *
     * @return the initial Y position of the top left of the window as a double
     */
    public double getInitY() {
        return initY;
    }

    /**
     * Gets the initial width of the window.
     *
     * @return the initial width of the window as a double
     */
    public double getInitWidth() {
        return initWidth;
    }

    /**
     * Gets the initial height of the window.
     *
     * @return the initial height of the window as a double
     */
    public double getInitHeight() {
        return initHeight;
    }

    /**
     * Gets all shapes in the level.
     *
     * @return all shapes as an ArrayList<ParaShape>
     */
    List<ParaShape> getShapes() {
        return paraShapes;
    }

    public void addShape(final ParaShape paraShape) {
        this.paraShapes.add(paraShape);
    }

    public void addFixedLevelType(final FixedLevelType fixedLevelType) {
        this.fixedLevelTypes.add(fixedLevelType);
        if (fixedLevelType == FixedLevelType.WIDTH) {
            fixWidth();
        } else if (fixedLevelType == FixedLevelType.HEIGHT) {
            fixHeight();
        } else if (fixedLevelType == FixedLevelType.X_POSITION) {
            fixX();
        } else if (fixedLevelType == FixedLevelType.Y_POSITION) {
            fixY();
        }
    }

    private void fixWidth() {
        ParaRectangle leftRedLine = new ParaRectangle(0, initX,
                -FIVE_PIXELS, THREE_PIXELS, Driver.SCREEN_HEIGHT, 0, Color.RED);
        leftRedLine.addTransform(Levels.WITH_X);
        leftRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(leftRedLine);

        ParaRectangle rightRedLine = new ParaRectangle(0, initX + initWidth
                - Levels.WIDTH_DIFFERENCE, -FIVE_PIXELS, THREE_PIXELS,
                Driver.SCREEN_HEIGHT, 0, Color.RED);
        rightRedLine.addTransform(Levels.WITH_X);
        rightRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(rightRedLine);
    }

    private void fixHeight() {
        ParaRectangle topRedLine = new ParaRectangle(0, initX - FIVE_PIXELS,
                initY, Driver.SCREEN_WIDTH, THREE_PIXELS, 0, Color.RED);
        topRedLine.addTransform(Levels.WITH_X);
        topRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(topRedLine);

        ParaRectangle bottomRedLine = new ParaRectangle(0, initX - FIVE_PIXELS,
                initY + initHeight - Levels.HEADER_HEIGHT,
                Driver.SCREEN_WIDTH, THREE_PIXELS, 0, Color.RED);
        bottomRedLine.addTransform(Levels.WITH_X);
        bottomRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(bottomRedLine);
    }

    private void fixX() {
        ParaRectangle leftBlackLine = new ParaRectangle(0, initX + FIVE_PIXELS,
                initY - FIVE_PIXELS, FIVE_PIXELS, Driver.SCREEN_HEIGHT, 0, Color.BLACK);
        leftBlackLine.addTransform(Levels.WITH_X);
        leftBlackLine.addTransform(Levels.WITH_Y);
        paraShapes.add(leftBlackLine);
    }

    private void fixY() {
        ParaRectangle topBlackLine = new ParaRectangle(0, initX - FIVE_PIXELS, initY + FIVE_PIXELS,
                Driver.SCREEN_WIDTH, FIVE_PIXELS, 0, Color.BLACK);
        topBlackLine.addTransform(Levels.WITH_X);
        topBlackLine.addTransform(Levels.WITH_Y);
        paraShapes.add(topBlackLine);
    }

    void initTransforms() {
        for (ParaShape shape : paraShapes) {
            shape.implementTransforms(initHeight, initWidth, initX, initY);
        }
    }

    List<FixedLevelType> getFixedLevelTypes() {
        return fixedLevelTypes;
    }

    public void sortByZ() {
        paraShapes.sort(Comparator.comparing(ParaShape::getzIndex));
    }

    public void update(final double windowHeight, final double windowWidth,
                       final double windowX, final double windowY) {
        for (ParaShape paraShape : paraShapes) {
            paraShape.update(windowHeight, windowWidth, windowX, windowY);
        }
    }
}
