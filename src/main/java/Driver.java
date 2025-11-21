import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Driver extends Application {

    private static final Rectangle2D BOUNDS = Screen.getPrimary().getVisualBounds();
    private static final double SCREEN_WIDTH = BOUNDS.getWidth();
    private static final double SCREEN_HEIGHT = BOUNDS.getHeight();
    private static final int FONT_SIZE = 14;

    private Label label;
    private Canvas canvas;

    /**
     * Starts the animation.
     * @param stage the stage used by javafx to represent what is seen on screen
     */
    @Override
    public void start(Stage stage) throws Exception {

        label = new Label("Bounding Box Collision");
        label.setFont(Font.font("Arial", FONT_SIZE));
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane(canvas, label);

        Scene scene = new Scene(root, SCREEN_WIDTH - 300, SCREEN_HEIGHT - 300, Color.BLACK);
        stage.initStyle(StageStyle.UNIFIED);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        Rectangle cube = new Rectangle(30, 30, Color.FIREBRICK);

        root.getChildren().add(cube);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                cube.setX(100 - stage.getX() + canvas.getHeight());
                cube.setY(100 - stage.getY());
            }
        }.start();

        stage.setTitle("JavaFX Image Collision Detection");
        stage.setScene(scene);
        stage.show();
    }
}
