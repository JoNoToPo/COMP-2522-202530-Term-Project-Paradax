import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Measures the title bar and borders the operating system draws around the game window.
 * They are different on Windows, Mac and Linux, so they are measured once the window is shown
 * instead of being hard-coded.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class WindowFrame {

    private static double width = Double.NaN;
    private static double height = Double.NaN;

    private WindowFrame() {
    }

    /**
     * Measures the window frame. Must be called after the stage is shown and before any
     * levels are created.
     *
     * @param stage the shown stage as a Stage
     * @param scene the scene inside the stage as a Scene
     */
    static void measure(final Stage stage, final Scene scene) {
        width = stage.getWidth() - scene.getWidth();
        height = stage.getHeight() - scene.getHeight();
    }

    /**
     * Gets the combined width of the left and right borders of the window.
     *
     * @return the width in pixels as a double
     */
    static double getWidth() {
        checkMeasured();
        return width;
    }

    /**
     * Gets the combined height of the title bar and bottom border of the window.
     *
     * @return the height in pixels as a double
     */
    static double getHeight() {
        checkMeasured();
        return height;
    }

    private static void checkMeasured() {
        if (Double.isNaN(width)) {
            throw new IllegalStateException("The window frame has not been measured yet.");
        }
    }
}
