import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a shape in Paradax.
 *
 * @author jenge
 * @version 2025
 */
public abstract class ParaShape {
    private static final int ONE_HUNDRED_PIXELS = 100;
    private static final int TEN_PIXELS = 10;

    private double initX;
    private double initY;
    private double initWidth;
    private double initHeight;
    private double initAngle;

    private final List<Transform> transforms;
    private final int zIndex;

    public ParaShape(final int zIndex, final double initX, final double initY,
                     final double width, final double height, final double initAngle) {
        this.zIndex = zIndex;
        this.transforms = new ArrayList<>();
        this.initX = initX;
        this.initY = initY;
        this.initWidth = width;
        this.initHeight = height;
        this.initAngle = initAngle;
    }

    public final void update(final double windowHeight, final double windowWidth,
                             final double windowX, final double windowY) {
        double xDifference = 0;
        double yDifference = 0;
        double angleDifference = 0;
        double widthDifference = 0;
        double heightDifference = 0;

        for (Transform transform : transforms) {
            TransformType type = transform.getType();
            double difference = calculateDifference(windowHeight,
                    windowWidth, windowX, windowY, transform);
            if (type == TransformType.HORIZONTAL) {
                xDifference += difference;
            } else if (type == TransformType.VERTICAL) {
                yDifference += difference;
            } else if (type == TransformType.ROTATE) {
                angleDifference += Math.floor(difference);
            } else if (type == TransformType.WIDEN) {
                widthDifference += difference;
            } else if (type == TransformType.LENGTHEN) {
                heightDifference += difference;
            }
        }
        transform(windowX, windowY, xDifference, yDifference,
                widthDifference, heightDifference, angleDifference);
    }

    private double parseSource(final double windowHeight, final double windowWidth,
                               final double windowX, final double windowY,
                               final TransformSource source) {
        if (source == TransformSource.X_POS) {
            return windowX;
        } else if (source == TransformSource.Y_POS) {
            return windowY;
        } else if (source == TransformSource.HEIGHT) {
            return windowHeight;
        } else if (source == TransformSource.WIDTH) {
            return windowWidth;
        }
        return 0;
    }

    private double parsePath(final double source, final double multiplier,
                             final TransformPath path) {
        if (path == TransformPath.SINUSOIDAL) {
            return Math.sin(source / Math.TAU / TEN_PIXELS) * multiplier * ONE_HUNDRED_PIXELS;
        } else if (path == TransformPath.COSINUSOIDAL) {
            return Math.cos(source / Math.TAU / TEN_PIXELS) * multiplier * ONE_HUNDRED_PIXELS;
        } else if (path == TransformPath.EXPONENTIAL) {
            return source * source * multiplier;
        }
        return source * multiplier;
    }

    private double calculateDifference(final double windowHeight, final double windowWidth,
                                       final double windowX, final double windowY,
                                       final Transform transform) {
        double source = parseSource(windowHeight, windowWidth,
                windowX, windowY, transform.getSource());
        return parsePath(source, transform.getMultiplier(), transform.getPath());
    }

    private void transform(final double windowX, final double windowY, final double xDifference,
                           final double yDifference, final double widthDifference,
                           final double heightDifference, final double angleDifference) {
        transformHorizontal(windowX, xDifference);
        transformVertical(windowY, yDifference);
        rotate(angleDifference);
        widen(widthDifference);
        lengthen(heightDifference);
    }

    abstract void transformHorizontal(double windowX, double delta);

    abstract void transformVertical(double windowY, double delta);

    abstract void rotate(double delta);

    abstract void widen(double delta);

    abstract void lengthen(double delta);

    abstract Node getNode();

    public final int getzIndex() {
        return zIndex;
    }

    public final void addTransform(final Transform transform) {
        transforms.add(transform);
    }

    public final void implementTransforms(final double shapeInitHeight, final double shapeInitWidth,
                                          final double shapeInitX, final double shapeInitY) {
        for (Transform transform : transforms) {
            TransformType type = transform.getType();
            double difference = calculateDifference(shapeInitHeight,
                    shapeInitWidth, shapeInitX, shapeInitY, transform);
            if (type == TransformType.HORIZONTAL) {
                this.initX -= difference;
            } else if (type == TransformType.VERTICAL) {
                this.initY -= difference;
            } else if (type == TransformType.ROTATE) {
                this.initAngle -= Math.floor(difference);
            } else if (type == TransformType.WIDEN) {
                this.initWidth -= difference;
            } else if (type == TransformType.LENGTHEN) {
                this.initHeight -= difference;
            }
        }
    }

    public final double getInitX() {
        return initX;
    }

    public final double getInitY() {
        return initY;
    }

    public void changePosition(final double initX, final double initY) {
        this.initY = initY;
        this.initX = initX;
    }

    public final double getInitHeight() {
        return initHeight;
    }

    public final double getInitWidth() {
        return initWidth;
    }

    public final double getInitAngle() {
        return initAngle;
    }
}
