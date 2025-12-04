import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a level in Paralax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class Level {
    /**
     * Widens linearly with window width.
     */
    static final Transform GROW_WIDTH = new Transform(TransformType.WIDEN,
            TransformSource.WIDTH, TransformPath.LINEAR, 1);

    /**
     * Widens linearly with window width.
     */
    static final Transform GROW_HEIGHT = new Transform(TransformType.LENGTHEN,
            TransformSource.HEIGHT, TransformPath.LINEAR, 1);

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

    /**
     * Adds a shape into the level.
     *
     * @param paraShape the shape as a ParaShape Object
     */
    public void addShape(final ParaShape paraShape) {
        this.paraShapes.add(paraShape);
    }

    /**
     * Fixes the level in a dimension.
     *
     * @param fixedLevelType the dimension being restricted as a FixedLevelType Enumeration
     */
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

    /**
     * Adds line indicators when the width is fixed.
     */
    private void fixWidth() {
        ParaRectangle leftRedLine = new ParaRectangle(0, initX,
                initY + FIVE_PIXELS, THREE_PIXELS,
                initHeight - Levels.HEADER_HEIGHT - FIVE_PIXELS * 2, 0, Color.RED);
        leftRedLine.addTransform(GROW_HEIGHT);
        leftRedLine.addTransform(Levels.WITH_X);
        leftRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(leftRedLine);

        ParaRectangle rightRedLine = new ParaRectangle(0, initX + initWidth - 2
                - Levels.WIDTH_DIFFERENCE, initY + FIVE_PIXELS, THREE_PIXELS,
                initHeight - Levels.HEADER_HEIGHT - FIVE_PIXELS * 2, 0, Color.RED);
        rightRedLine.addTransform(GROW_HEIGHT);
        rightRedLine.addTransform(Levels.WITH_X);
        rightRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(rightRedLine);
    }

    /**
     * Adds line indicators when the height is fixed.
     */
    private void fixHeight() {
        ParaRectangle topRedLine = new ParaRectangle(0, initX + FIVE_PIXELS,
                initY, initWidth - Levels.WIDTH_DIFFERENCE - FIVE_PIXELS * 2,
                THREE_PIXELS, 0, Color.RED);
        topRedLine.addTransform(GROW_WIDTH);
        topRedLine.addTransform(Levels.WITH_X);
        topRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(topRedLine);

        ParaRectangle bottomRedLine = new ParaRectangle(0, initX + FIVE_PIXELS,
                initY + initHeight - Levels.HEADER_HEIGHT - 2,
                initWidth - Levels.WIDTH_DIFFERENCE - FIVE_PIXELS * 2,
                THREE_PIXELS, 0, Color.RED);
        bottomRedLine.addTransform(GROW_WIDTH);
        bottomRedLine.addTransform(Levels.WITH_X);
        bottomRedLine.addTransform(Levels.WITH_Y);
        paraShapes.add(bottomRedLine);
    }

    /**
     * Adds line indicator when the X is fixed.
     */
    private void fixX() {
        ParaRectangle leftBlackLine = new ParaRectangle(0, initX + FIVE_PIXELS,
                initY + FIVE_PIXELS, THREE_PIXELS,
                initHeight - Levels.HEADER_HEIGHT - FIVE_PIXELS * 2, 0, Color.BLACK);
        leftBlackLine.addTransform(GROW_HEIGHT);
        leftBlackLine.addTransform(Levels.WITH_X);
        leftBlackLine.addTransform(Levels.WITH_Y);
        paraShapes.add(leftBlackLine);
    }

    /**
     * Adds line indicator when the Y is fixed.
     */
    private void fixY() {
        ParaRectangle topBlackLine = new ParaRectangle(0, initX + FIVE_PIXELS,
                initY + FIVE_PIXELS, initWidth - Levels.WIDTH_DIFFERENCE - FIVE_PIXELS * 2,
                THREE_PIXELS, 0, Color.BLACK);
        topBlackLine.addTransform(GROW_WIDTH);
        topBlackLine.addTransform(Levels.WITH_X);
        topBlackLine.addTransform(Levels.WITH_Y);
        paraShapes.add(topBlackLine);
    }

    /**
     * Initializes the transforms for each shape in level.
     */
    void initTransforms() {
        for (ParaShape shape : paraShapes) {
            shape.initializeTransforms(initHeight, initWidth, initX, initY);
        }
    }

    /**
     * Adds line indicator when the X is fixed.
     *
     * @return the fixed level types as a List<FixedLevelType>
     */
    List<FixedLevelType> getFixedLevelTypes() {
        return fixedLevelTypes;
    }

    /**
     * Sorts shapes in level by Z index.
     */
    public void sortByZ() {
        paraShapes.sort(Comparator.comparing(ParaShape::getzIndex));
    }

    /**
     * Updates shapes in level.
     *
     * @param currWindowHeight the current height of the screen as a double
     * @param currWindowWidth the current width of the screen as a double
     * @param currWindowX the current X position of the top left of the window as a double
     * @param currWindowY  the current Y position of the top left of the window as a double
     */
    public void update(final double currWindowHeight, final double currWindowWidth,
                       final double currWindowX, final double currWindowY) {
        for (ParaShape paraShape : paraShapes) {
            paraShape.update(currWindowHeight, currWindowWidth, currWindowX, currWindowY);
        }
    }

    /**
     * Returns a String representation of this Level.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("Level{");
        sb.append("paraShapes=").append(paraShapes);
        sb.append(", fixedLevelTypes=").append(fixedLevelTypes);
        sb.append(", initX=").append(initX);
        sb.append(", initY=").append(initY);
        sb.append(", initWidth=").append(initWidth);
        sb.append(", initHeight=").append(initHeight);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this Level to another Object for equality.
     *
     * @param object an Object
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Level level = (Level) object;
        return Double.compare(initX, level.initX) == 0
                && Double.compare(initY, level.initY) == 0
                && Double.compare(initWidth, level.initWidth) == 0
                && Double.compare(initHeight, level.initHeight) == 0
                && Objects.equals(paraShapes, level.paraShapes)
                && Objects.equals(fixedLevelTypes, level.fixedLevelTypes);
    }

    /**
     * Returns a hash code for this Level.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                paraShapes,
                fixedLevelTypes,
                initX,
                initY,
                initWidth,
                initHeight
        );
    }
}
