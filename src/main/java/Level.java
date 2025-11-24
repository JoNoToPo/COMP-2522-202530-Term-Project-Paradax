import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;

public final class Level {
    private static final int FIVE_PIXELS = 5;
    private final ArrayList<ParaShape> paraShapes;
    private final ArrayList<FixedLevelType> fixedLevelTypes;
    private final double initX;
    private final double initY;
    private final double initWidth;
    private final double initHeight;

    public Level(final double initX, final double initY, final double initWidth, final double initHeight) {
        this.paraShapes = new ArrayList<>();
        this.fixedLevelTypes = new ArrayList<>();
        this.initX = initX;
        this.initY = initY;
        this.initWidth = initWidth;
        this.initHeight = initHeight;
    }

    public double getInitX() {
        return initX;
    }

    public double getInitY() {
        return initY;
    }

    public double getInitWidth() {
        return initWidth;
    }

    public double getInitHeight() {
        return initHeight;
    }

    ArrayList<ParaShape> getShapes() {
        return paraShapes;
    }

    public void addShape(ParaShape paraShape) {
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
        ParaRectangle leftRedLine = new ParaRectangle(0, 0, -FIVE_PIXELS, 3, Driver.SCREEN_HEIGHT, 0, Color.RED);
        leftRedLine.addTransform(Levels.withX);
        leftRedLine.addTransform(Levels.withY);
        paraShapes.add(leftRedLine);

        ParaRectangle rightRedLine = new ParaRectangle(0, 0 + initWidth
                - Levels.WIDTH_DIFFERENCE, -FIVE_PIXELS, 3, Driver.SCREEN_HEIGHT, 0, Color.RED);
        rightRedLine.addTransform(Levels.withX);
        rightRedLine.addTransform(Levels.withY);
        paraShapes.add(rightRedLine);
    }

    private void fixHeight() {
        ParaRectangle topRedLine = new ParaRectangle(0, -FIVE_PIXELS, 0, Driver.SCREEN_WIDTH, 3, 0, Color.RED);
        topRedLine.addTransform(Levels.withX);
        topRedLine.addTransform(Levels.withY);
        paraShapes.add(topRedLine);

        ParaRectangle bottomRedLine = new ParaRectangle(0, -FIVE_PIXELS, initHeight - Levels.HEADER_HEIGHT, Driver.SCREEN_WIDTH, 3, 0, Color.RED);
        bottomRedLine.addTransform(Levels.withX);
        bottomRedLine.addTransform(Levels.withY);
        paraShapes.add(bottomRedLine);
    }

    private void fixX() {
        ParaRectangle leftBlackLine = new ParaRectangle(0, FIVE_PIXELS, -FIVE_PIXELS, FIVE_PIXELS, Driver.SCREEN_HEIGHT, 0, Color.BLACK);
        leftBlackLine.addTransform(Levels.withX);
        leftBlackLine.addTransform(Levels.withY);
        paraShapes.add(leftBlackLine);
    }

    private void fixY() {
        ParaRectangle topBlackLine = new ParaRectangle(0, -FIVE_PIXELS, FIVE_PIXELS,
                Driver.SCREEN_WIDTH, FIVE_PIXELS, 0, Color.BLACK);
        topBlackLine.addTransform(Levels.withX);
        topBlackLine.addTransform(Levels.withY);
        paraShapes.add(topBlackLine);
    }

    ArrayList<FixedLevelType> getFixedLevelTypes() {
        return fixedLevelTypes;
    }

    public void sortByZ() {
        paraShapes.sort(Comparator.comparing(ParaShape::getZ));
    }

    public void update(final double windowHeight, final double windowWidth,
                       final double windowX, final double windowY) {
        for (ParaShape paraShape : paraShapes) {
            paraShape.update(windowHeight, windowWidth, windowX, windowY);
        }
    }
}
