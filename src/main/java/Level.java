import java.util.ArrayList;

public class Level {
    private ArrayList<ParaShape> paraShapes;

    public Level() {
        this.paraShapes = new ArrayList<>();
    }

//    NodeList getNodes();

    ArrayList<ParaShape> getShapes() {
        return paraShapes;
    }

    public void addShape(ParaShape paraShape) {
        this.paraShapes.add(paraShape);
    }
}
