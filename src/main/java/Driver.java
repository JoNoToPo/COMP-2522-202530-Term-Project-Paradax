import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.LinkedList;

public final class Driver extends Application {

    static final Rectangle2D BOUNDS = Screen.getPrimary().getVisualBounds();
    static final double SCREEN_WIDTH = BOUNDS.getWidth();
    static final double SCREEN_HEIGHT = BOUNDS.getHeight();

    private Level currLevel = new Level();
    private final LinkedList<Level> levels = new LinkedList<>();

    /**
     * Starts the animation.
     * @param stage the stage used by javafx to represent what is seen on screen
     */
    @Override
    public void start(Stage stage) {

        Label label = new Label("");
        Canvas canvas = new Canvas();
        Pane root = new Pane(canvas, label);

        Scene scene = new Scene(root, SCREEN_WIDTH - 500, SCREEN_HEIGHT - 400, Color.BLACK);
        stage.initStyle(StageStyle.UNIFIED);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        levels.add(LevelOne.levelOne(root));
        levels.add(LevelTwo.levelTwo(root));
        root.getChildren().clear();

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                double windowHeight = canvas.getHeight();
                double windowWidth = canvas.getWidth();
                double windowX = stage.getX();
                double windowY = stage.getY();

                if (root.getChildren().isEmpty()) {
                    if (!levels.isEmpty()) {
                        currLevel = levels.pop();
                        for (ParaShape shape : currLevel.getShapes()) {
                            root.getChildren().add(shape.getNode());
                        }
                    } else {
                        Button contingency = new Button("This shouldn't happen");
                        root.getChildren().add(contingency);
                    }
                }
                currLevel.update(windowHeight, windowWidth, windowX, windowY);
                currLevel.sortByZ();
            }
        }.start();

        stage.setTitle("Paradax");
        stage.setScene(scene);
        stage.show();
    }
}
