import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Converts percentages of the screen into pixels so Paradax looks the same on every monitor.
 * Horizontal lengths are percentages of the screen width and vertical lengths are
 * percentages of the screen height.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class Scale {

    /**
     * The width of the screen in pixels as a double.
     */
    static final double SCREEN_WIDTH;

    /**
     * The height of the screen in pixels as a double.
     */
    static final double SCREEN_HEIGHT;

    private static final double ONE_HUNDRED_PERCENT = 100;

    static {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        SCREEN_WIDTH = bounds.getWidth();
        SCREEN_HEIGHT = bounds.getHeight();
    }

    private Scale() {
    }

    /**
     * Converts a percentage of the screen width into pixels.
     *
     * @param percent the percentage of the screen width as a double
     * @return the length in pixels as a double
     */
    static double width(final double percent) {
        return SCREEN_WIDTH * percent / ONE_HUNDRED_PERCENT;
    }

    /**
     * Converts a percentage of the screen height into pixels.
     *
     * @param percent the percentage of the screen height as a double
     * @return the length in pixels as a double
     */
    static double height(final double percent) {
        return SCREEN_HEIGHT * percent / ONE_HUNDRED_PERCENT;
    }

    /**
     * Converts a horizontal length in pixels into a percentage of the screen width.
     *
     * @param pixels the length in pixels as a double
     * @return the percentage of the screen width as a double
     */
    static double toWidthPercent(final double pixels) {
        return pixels / SCREEN_WIDTH * ONE_HUNDRED_PERCENT;
    }

    /**
     * Converts a vertical length in pixels into a percentage of the screen height.
     *
     * @param pixels the length in pixels as a double
     * @return the percentage of the screen height as a double
     */
    static double toHeightPercent(final double pixels) {
        return pixels / SCREEN_HEIGHT * ONE_HUNDRED_PERCENT;
    }
}
