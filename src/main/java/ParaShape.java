import javafx.scene.Node;

import java.util.ArrayList;

public abstract class ParaShape {
    private static final int SCALE_CONSTANT = 150;
    private static final int ONE_HUNDRED_EIGHTY_DEGREES = 180;

    private int zIndex;
    private final ArrayList<Transform> transforms;
    private final double initX;
    private final double initY;
    private final double initWidth;
    private final double initHeight;
    private final double initAngle;

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
            double difference = calculateDifference(windowHeight, windowWidth, windowX, windowY, transform);
            if (type == TransformType.HORIZONTAL) {
                xDifference += difference;
            } else if (type == TransformType.VERTICAL) {
                yDifference += difference;
            } else if (type == TransformType.ROTATE) {
                angleDifference += difference * ONE_HUNDRED_EIGHTY_DEGREES;
            } else if (type == TransformType.WIDEN) {
                widthDifference += difference;
            } else if (type == TransformType.LENGTHEN) {
                heightDifference += difference;
            }
        }
        transform(windowX, windowY, xDifference, yDifference, widthDifference, heightDifference, angleDifference);
    }

    private double parseSource(final double windowHeight, final double windowWidth,
                               final double windowX, final double windowY, final TransformSource source) {
        if (source == TransformSource.X_POS) {
            return windowX / SCALE_CONSTANT;
        } else if (source == TransformSource.Y_POS) {
            return windowY / SCALE_CONSTANT;
        } else if (source == TransformSource.HEIGHT) {
            return windowHeight / SCALE_CONSTANT;
        } else if (source == TransformSource.WIDTH) {
            return windowWidth / SCALE_CONSTANT;
        }
        return 0;
    }

    private double parsePath(final double source, final double multiplier, final TransformPath path) {
        if (path == TransformPath.SINUSOIDAL) {
            return Math.sin(source) * SCALE_CONSTANT * multiplier;
        } else if (path == TransformPath.COSINUSOIDAL) {
            return Math.cos(source) * SCALE_CONSTANT * multiplier;
        }
        return source * multiplier;
    }

    private double calculateDifference(final double windowHeight, final double windowWidth,
                                       final double windowX, final double windowY, final Transform transform) {
        double source = parseSource(windowHeight, windowWidth, windowX, windowY, transform.getSource());
        return parsePath(source, transform.getMultiplier(), transform.getPath());
    }

    private void transform(final double windowX, final double windowY, final double xDifference,
                           final double yDifference, final double widthDifference, final double heightDifference, final double angleDifference) {
        transformHorizontal(windowX, xDifference);
        transformVertical(windowY, yDifference);
        rotate(angleDifference);
        widen(widthDifference);
        lengthen(heightDifference);
    }

    abstract void transformHorizontal(final double windowX, final double delta);

    abstract void transformVertical(final double windowY, final double delta);

    abstract void rotate(final double delta);

    abstract void widen(final double delta);

    abstract void lengthen(final double delta);

    abstract Node getNode();

    public int getZ() {
        return zIndex;
    }

    public final void setZ(int zIndex) {
        this.zIndex = zIndex;
    }

    public final void addTransform(Transform transform) {
        transforms.add(transform);
    }

    public final void removeTransform(Transform transform) {
        this.transforms.remove(transform);
    }

    public final double getInitX() {
        return initX;
    }

    public final double getInitY() {
        return initY;
    }

    public double getInitHeight() {
        return initHeight;
    }

    public double getInitWidth() {
        return initWidth;
    }

    public final double getInitAngle() {
        return initAngle;
    }
}
