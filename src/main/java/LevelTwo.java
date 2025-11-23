import javafx.scene.layout.Pane;

public class LevelTwo {

    private LevelTwo() {
    }

    static Level levelTwo(final Pane root) {
        Level level = new Level();

        ParaShape button = new ParaButton(5, 300, 300, 0,
                200, 50, "You Win!", ParaButtonType.CHANGE_TRANSFORM_TYPE, root);
        level.addShape(button);

        return level;
    }
}
