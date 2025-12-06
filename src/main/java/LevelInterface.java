import java.util.List;

public interface LevelInterface {

    void update(double initHeight, double initWidth, double initX, double initY);

    void sortByZ();

    List<ParaShape> getShapes();

    double getInitWidth();

    double getInitHeight();

    double getInitX();

    double getInitY();

    List<FixedLevelType> getFixedLevelTypes();
}
