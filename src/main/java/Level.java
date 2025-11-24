import java.util.ArrayList;
import java.util.Comparator;

public final class Level {
    private final ArrayList<ParaShape> paraShapes;
    private final double initX;
    private final double initY;
    private final double initWidth;
    private final double initHeight;

    public Level(final double initX, final double initY, final double initWidth, final double initHeight) {
        this.paraShapes = new ArrayList<>();
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
