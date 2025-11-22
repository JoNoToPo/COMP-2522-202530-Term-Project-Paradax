import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Driver extends Application {

    private static final Rectangle2D BOUNDS = Screen.getPrimary().getVisualBounds();
    private static final double SCREEN_WIDTH = BOUNDS.getWidth();
    private static final double SCREEN_HEIGHT = BOUNDS.getHeight();
    private static final int FONT_SIZE = 14;


    private Label label;
    private Canvas canvas;
    private Level currLevel = new Level();
    private final ArrayList<Level> levels = new ArrayList<>();

    /**
     * Starts the animation.
     * @param stage the stage used by javafx to represent what is seen on screen
     */
    @Override
    public void start(Stage stage) throws Exception {

        label = new Label("");
        Canvas canvas = new Canvas();
        Pane root = new Pane(canvas, label);

        Scene scene = new Scene(root, SCREEN_WIDTH - 300, SCREEN_HEIGHT - 300, Color.BLACK);
        stage.initStyle(StageStyle.UNIFIED);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        Level level1 = levelOne();
        levels.add(level1);
        currLevel = levels.getFirst();
        for (ParaShape shape : currLevel.getShapes()) {
            root.getChildren().add(shape.getNode());
        }

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                double windowHeight = canvas.getHeight();
                double windowWidth = canvas.getWidth();
                double windowX = stage.getX();
                double windowY = stage.getY();

                for (ParaShape paraShape : currLevel.getShapes()) {
                    paraShape.update(windowHeight, windowWidth, windowX, windowY);
                }
            }
        }.start();

        stage.setTitle("Paradax");
        stage.setScene(scene);
        stage.show();
    }

    private Level levelOne() {
        Level level1 = new Level();

        ParaShape move_against = new ParaRectangle(0, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0, 30, 30, Color.BLUE);
        Transform vert = new Transform(TransformType.VERTICAL, TransformSource.Y_POS, TransformPath.LINEAR, -1);
        Transform horz = new Transform(TransformType.HORIZONTAL, TransformSource.X_POS, TransformPath.LINEAR, -1);
        move_against.addTransform(vert);
        move_against.addTransform(horz);
        level1.addShape(move_against);

        ParaShape spinWithWidth = new ParaRectangle(0, 300, 300, 0, 30, 30, Color.FIREBRICK);
        Transform sinT = new Transform(TransformType.VERTICAL, TransformSource.WIDTH, TransformPath.SINUSOIDAL, .5);
        Transform cosT = new Transform(TransformType.HORIZONTAL, TransformSource.WIDTH, TransformPath.COSINUSOIDAL, .5);
        spinWithWidth.addTransform(sinT);
        spinWithWidth.addTransform(cosT);
        level1.addShape(spinWithWidth);

        return level1;
    }
}
