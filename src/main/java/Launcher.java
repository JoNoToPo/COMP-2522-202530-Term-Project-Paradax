import javafx.application.Application;

/**
 * Starts Paradax from a downloaded copy of the game. JavaFX refuses to start when the main
 * class extends Application and JavaFX is not on the module path, so this class launches it.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * Launches the game.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        Application.launch(Driver.class, args);
    }
}
