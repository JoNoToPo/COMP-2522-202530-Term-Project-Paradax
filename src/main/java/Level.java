import java.util.ArrayList;
import java.util.Comparator;

public final class Level {
    private final ArrayList<ParaShape> paraShapes;

    public Level() {
        this.paraShapes = new ArrayList<>();
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
