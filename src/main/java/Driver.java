import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
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

    private static final int RADIUS = 10;
    private static final BackgroundFill BACKGROUND_FILL = new BackgroundFill(
            Color.LIGHTGRAY,
            new CornerRadii(RADIUS),
            new Insets(RADIUS)
    );
    private static final Background BACKGROUND = new Background(BACKGROUND_FILL);
    private static final ParaText DEBUG = new ParaText(50, 50,
            50, 50, 0, "", Color.BLACK);
    private Level currLevel = new Level(0, 0, 0, 0);

    /**
     * Starts the animation.
     * @param stage the stage used by javafx to represent what is seen on screen
     */
    @Override
    public void start(final Stage stage) {

        Label label = new Label("");
        Canvas canvas = new Canvas();
        Pane root = new Pane(canvas, label);
        root.setBackground(BACKGROUND);

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.GRAY);
        stage.initStyle(StageStyle.UNIFIED);
        diallowMaximize(stage);
        scene.setCursor(new ImageCursor());

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        LinkedList<Level> levels = Levels.levels(root);
        root.getChildren().clear();

        new AnimationTimer() {
            @Override
            public void handle(final long longParam) {
                double windowHeight = stage.getHeight();
                double windowWidth = stage.getWidth();
                double windowX = stage.getX();
                double windowY = stage.getY();

                if (root.getChildren().isEmpty()) {
//                    root.getChildren().add(DEBUG.getNode());
                    if (!levels.isEmpty()) {
                        currLevel = levels.pop();
                        initializeStagePosition(stage);
                        for (ParaShape shape : currLevel.getShapes()) {
                            root.getChildren().add(shape.getNode());
                        }
                    } else {
                        Button contingency = new Button("You Win!");
                        root.getChildren().add(contingency);
                    }
                }
                fixStage(stage);
//                DEBUG.changeMessage(Double.toString(windowX));
                currLevel.update(windowHeight, windowWidth, windowX, windowY);
                currLevel.sortByZ();
            }
        }.start();

        stage.setTitle("Paradax");
        stage.setScene(scene);
        stage.show();
    }

    private static void diallowMaximize(Stage stage) {
        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
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
