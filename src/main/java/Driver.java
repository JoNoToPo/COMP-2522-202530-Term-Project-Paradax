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

public class Driver extends Application {

    private static final Rectangle2D BOUNDS = Screen.getPrimary().getVisualBounds();
    private static final double SCREEN_WIDTH = BOUNDS.getWidth();
    private static final double SCREEN_HEIGHT = BOUNDS.getHeight();

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

        Scene scene = new Scene(root, SCREEN_WIDTH - 300, SCREEN_HEIGHT - 300, Color.BLACK);
        stage.initStyle(StageStyle.UNIFIED);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        levels.add(levelOne());
        levels.add(levelTwo());
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
                        Button nextLevel = new Button("Next Level");
                        nextLevel.setOnAction(_ -> {
                            System.out.println("clicked");
                            root.getChildren().clear();
                        });
                        root.getChildren().add(nextLevel);
                    } else {
                        Button nextLevel = new Button("The end of all things");
                        nextLevel.setOnAction(_ -> {
                            System.out.println("clicked");
                            root.getChildren().clear();
                        });
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

    private Level levelOne() {
        Level level = new Level();

        ParaShape move_against = new ParaRectangle(3, SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 2, 0, 50, 50, Color.BLUE);
        Transform vert = new Transform(TransformType.VERTICAL,
                TransformSource.Y_POS, TransformPath.LINEAR, -1);
        Transform horizontalTransform = new Transform(TransformType.HORIZONTAL,
                TransformSource.X_POS, TransformPath.LINEAR, -1);
        move_against.addTransform(vert);
        move_against.addTransform(horizontalTransform);
        level.addShape(move_against);

        ParaShape spinWithWidth = new ParaRectangle(2, 300, 300, 45, 50, 50, Color.FIREBRICK);
        Transform sinT = new Transform(TransformType.VERTICAL,
                TransformSource.WIDTH, TransformPath.SINUSOIDAL, .5);
        Transform cosT = new Transform(TransformType.HORIZONTAL,
                TransformSource.WIDTH, TransformPath.COSINUSOIDAL, -.5);
        spinWithWidth.addTransform(sinT);
        spinWithWidth.addTransform(cosT);
        level.addShape(spinWithWidth);

        ParaShape button = new ParaButton(5, 25, 35, 20, 50, 50, "  This\nIs cool");
        level.addShape(button);

        return level;
    }

    private Level levelTwo() {
        Level level = new Level();

        ParaShape button = new ParaButton(5, 300, 300, 0, 200, 50, "You Win!");
        level.addShape(button);

        return level;
    }
}
