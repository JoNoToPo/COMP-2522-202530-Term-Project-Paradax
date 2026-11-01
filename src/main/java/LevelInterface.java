import java.util.List;

/**
 * Interface of a level in Paradax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public interface LevelInterface {

    /**
     * Updates shapes in level.
     *
     * @param initHeight the current height of the screen as a double
     * @param initWidth the current width of the screen as a double
     * @param initX the current X position of the top left of the window as a double
     * @param initY  the current Y position of the top left of the window as a double
     */
    void update(double initHeight, double initWidth, double initX, double initY);

    /**
     * Sorts shapes in level by Z index.
     */
    void sortByZ();

    /**
     * Gets all shapes in the level.
     *
     * @return all shapes as an ArrayList<ParaShape>
     */
    List<ParaShape> getShapes();

    /**
     * Gets the initial width of the window.
     *
     * @return the initial width of the window as a double
     */
    double getInitWidth();

    /**
     * Gets the initial height of the window.
     *
     * @return the initial height of the window as a double
     */
    double getInitHeight();

    /**
     * Gets the initial X position of the top left of the window.
     *
     * @return the initial X position of the top left of the window as a double
     */
    double getInitX();

    /**
     * Gets the initial Y position of the top left of the window.
     *
     * @return the initial Y position of the top left of the window as a double
     */
    double getInitY();

    /**
     * Gets the Fixed level types of the level as a list.
     *
     * @return the fixed level types as a List<FixedLevelType>
     */
    List<FixedLevelType> getFixedLevelTypes();
}
