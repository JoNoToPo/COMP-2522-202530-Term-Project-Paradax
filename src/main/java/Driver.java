import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Drives Paradax the game.
 *
 * @author jenge
 * @version 2025
 */
public final class Driver extends Application {

    private static final Rectangle2D BOUNDS = Screen.getPrimary().getVisualBounds();

    /**
     * The width of the screen as a double.
     */
    static final double SCREEN_WIDTH = BOUNDS.getWidth();

    /**
     * The height of the screen as a double.
     */
    static final double SCREEN_HEIGHT = BOUNDS.getHeight();

    private static final Path DATA_PATH = Path.of("src", "main", "java", "data.txt");
    private static final int RADIUS = 10;
    private static final int OVERSHOOT = 2;
    private static final BackgroundFill BACKGROUND_FILL = new BackgroundFill(
            Color.DARKGRAY,
            new CornerRadii(RADIUS),
            new Insets(RADIUS)
    );
    private static final Background BACKGROUND = new Background(BACKGROUND_FILL);

    private LevelInterface currLevel = new Level(0, 0, 0, 0);
    private Deque<LevelInterface> levels = new LinkedList<>();
    private int skip;
    private int index = 0;
    private boolean contd = false;

    /**
     * Starts the animation.
     * @param stage the stage used by javafx to represent what is seen on screen
     */
    @Override
    public void start(final Stage stage) {
        createSaveFile();

        Pane root = new Pane();
        root.setBackground(BACKGROUND);

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.GRAY);
        limitStage(stage);
        scene.setCursor(new ImageCursor());

        Level intro = Levels.intro(root);
        ParaButton contBtn = Levels.continueButton(root);
        intro.addShape(contBtn);
        contBtn.getNode().addEventHandler(ActionEvent.ACTION, _ -> contd = true);
        levels.addFirst(intro);
        populateStage(stage, root);

        new AnimationTimer() {
            @Override
            public void handle(final long longParam) {

                if (root.getChildren().isEmpty()) {
                    nextLevel(stage, root);
                }

                fixStage(stage);
                currLevel.update(stage.getHeight(), stage.getWidth(), stage.getX(), stage.getY());
                currLevel.sortByZ();
            }
        }.start();

        stage.setTitle("Paradax");
        stage.setScene(scene);
        stage.show();
    }

    private void populateStage(final Stage unpopulatedStage, final Pane childlessRoot) {
        currLevel = levels.pop();
        initializeStagePosition(unpopulatedStage);
        for (ParaShape shape : currLevel.getShapes()) {
            childlessRoot.getChildren().add(shape.getNode());
        }
    }

    private void nextLevel(final Stage emptyStage, final Pane pane) {
        index += 1;
        if (!levels.isEmpty()) {
            populateStage(emptyStage, pane);
            writeFile(index - OVERSHOOT);
        } else {
            levels = Levels.levels(pane);
            if (contd) {
                for (; skip > 0; skip--) {
                    currLevel = levels.pop();
                    index += 1;
                }
                initializeStagePosition(emptyStage);
                contd = false;
            }
        }
    }

    private void createSaveFile() {
        if (Files.exists(DATA_PATH)) {
            skip = readFile();
        } else {
            try {
                Files.createFile(DATA_PATH);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            skip = 0;
        }
    }

    private static void writeFile(final Integer input) {
        try {
            Files.writeString(DATA_PATH, input.toString());
        } catch (IOException somethingBroke) {
            throw new RuntimeException(somethingBroke);
        }
    }

    private static int readFile() {
        try {
            return Integer.parseInt(Files.readString(DATA_PATH));
        } catch (IOException somethingBroke) {
            throw new RuntimeException(somethingBroke);
        }
    }

    private static void limitStage(final Stage stage) {
        stage.initStyle(StageStyle.UNIFIED);
        stage.setMaxWidth(SCREEN_WIDTH);
        negateMaximize(stage);
    }

    private static void negateMaximize(final Stage stage) {
        stage.maximizedProperty().addListener((_, _, newValue) -> {
            if (newValue) {
                stage.setMaximized(false);
            }
        });
    }

    private void initializeStagePosition(final Stage stage) {
        stage.setHeight(currLevel.getInitHeight());
        stage.setWidth(currLevel.getInitWidth());
        stage.setX(currLevel.getInitX());
        stage.setY(currLevel.getInitY());
    }

    private void fixStage(final Stage stage) {
        for (FixedLevelType type : currLevel.getFixedLevelTypes()) {
            if (type == FixedLevelType.HEIGHT) {
                stage.setHeight(currLevel.getInitHeight());
            } else if (type == FixedLevelType.WIDTH) {
                stage.setWidth(currLevel.getInitWidth());
            } else if (type == FixedLevelType.X_POSITION) {
                stage.setX(currLevel.getInitX());
            } else if (type == FixedLevelType.Y_POSITION) {
                stage.setY(currLevel.getInitY());
            }
        }
    }
}
