import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private final int zIndex;
    private final List<Transform> transforms;

    /**
     * Constructs a ParaShape.
     *
     * @param zIndex the z index of the shape as an int
     * @param initX the x index of the top left of the shape as a double
     * @param initY the y index of the top left of the shape as a double
     * @param width the width of the shape as a double
     * @param height the height of the shape as a double
     * @param initAngle the angle of the shape in degrees as a double
     */
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

    /**
     * Updates the shape using its transforms.
     *
     * @param currWindowHeight the current height of the screen as a double
     * @param currWindowWidth the current width of the screen as a double
     * @param currWindowX the current X position of the top left of the window as a double
     * @param currWindowY  the current Y position of the top left of the window as a double
     */
    public final void update(final double currWindowHeight, final double currWindowWidth,
                             final double currWindowX, final double currWindowY) {
        double totalX = initX - currWindowX;
        double totalY = initY - currWindowY;
        double totalAngle = initAngle;
        double totalWidth = initWidth;
        double totalHeight = initHeight;

        for (Transform transform : transforms) {
            TransformType type = transform.getType();
            double difference = calculateDelta(currWindowHeight,
                    currWindowWidth, currWindowX, currWindowY, transform);
            if (type == TransformType.HORIZONTAL) {
                totalX += difference;
            } else if (type == TransformType.VERTICAL) {
                totalY += difference;
            } else if (type == TransformType.ROTATE) {
                totalAngle += Math.floor(difference);
            } else if (type == TransformType.WIDEN) {
                totalWidth += difference;
            } else if (type == TransformType.LENGTHEN) {
                totalHeight += difference;
            }
        }
        transform(totalX, totalY, totalWidth, totalHeight, totalAngle);
    }

    /**
     * Parses the source of the transform.
     *
     * @param currWindowHeight the current height of the screen as a double
     * @param currWindowWidth the current width of the screen as a double
     * @param currWindowX the current X position of the top left of the window as a double
     * @param currWindowY  the current Y position of the top left of the window as a double
     * @param source the source of the transform as a TransformSource
     * @return the source of the transform as a double
     */
    private double parseSource(final double currWindowHeight, final double currWindowWidth,
                               final double currWindowX, final double currWindowY,
                               final TransformSource source) {
        if (source == TransformSource.X_POS) {
            return currWindowX;
        } else if (source == TransformSource.Y_POS) {
            return currWindowY;
        } else if (source == TransformSource.HEIGHT) {
            return currWindowHeight;
        } else if (source == TransformSource.WIDTH) {
            return currWindowWidth;
        }
        return 0;
    }

    /**
     * Parse the path taken.
     *
     * @param source the source of the transform as a double
     * @param path the path of the transform as a TransformPath
     * @return the path of the transform as a double
     */
    private double parsePath(final double source,
                             final TransformPath path) {
        if (path == TransformPath.SINUSOIDAL) {
            return Math.sin(source / Math.TAU / TEN_PIXELS) * ONE_HUNDRED_PIXELS;
        } else if (path == TransformPath.COSINUSOIDAL) {
            return Math.cos(source / Math.TAU / TEN_PIXELS) * ONE_HUNDRED_PIXELS;
        } else if (path == TransformPath.EXPONENTIAL) {
            return source * source;
        }
        return source;
    }

    /**
     * Calculates the delta from the transform.
     *
     * @param passWindowHeight the passed value for the height of the screen as a double
     * @param passWindowWidth the passed value for the width of the screen as a double
     * @param passWindowX the passed value for the X position
     *                    of the top left of the screen as a double
     * @param passWindowY the passed value for the Y position
     *                    of the top left of the screen as a double
     * @param transform the transform being applied as a Transform
     * @return the delta as a double
     */
    private double calculateDelta(final double passWindowHeight, final double passWindowWidth,
                                  final double passWindowX, final double passWindowY,
                                  final Transform transform) {
        double source = parseSource(passWindowHeight, passWindowWidth,
                passWindowX, passWindowY, transform.getSource());
        return parsePath(source, transform.getPath()) * transform.getMultiplier();
    }

    /**
     * Transforms the shape.
     *
     * @param xDifference the difference in X position as a double
     * @param yDifference the difference in Y position as a double
     * @param widthDifference the difference in width as a double
     * @param heightDifference the difference in height as a double
     * @param angleDifference the difference in angle as a double
     */
    private void transform(final double xDifference, final double yDifference,
                           final double widthDifference, final double heightDifference,
                           final double angleDifference) {
        transformHorizontal(xDifference);
        transformVertical(yDifference);
        rotate(angleDifference);
        widen(widthDifference);
        lengthen(heightDifference);
    }

    /**
     * Transforms the horizontal position of the shape.
     *
     * @param newX the X position as a double
     */
    abstract void transformHorizontal(double newX);

    /**
     * Transforms the vertical position of the shape.
     *
     * @param newY the Y position as a double
     */
    abstract void transformVertical(double newY);

    /**
     * Transforms the angle of the shape.
     *
     * @param newAngle the angle as a double
     */
    abstract void rotate(double newAngle);

    /**
     * Widens the shape.
     *
     * @param newWidth the width as a double
     */
    abstract void widen(double newWidth);

    /**
     * Lengthens the shape.
     *
     * @param newLength the length as a double
     */
    abstract void lengthen(double newLength);

    /**
     * Returns the node of the shape.
     *
     * @return the node of the shape as a Node
     */
    abstract Node getNode();

    /**
     * Initializes the shape given its transforms.
     *
     * @param windowInitHeight the initial X position of the top left of the window as a double
     * @param windowInitWidth the initial Y position of the top left of the window as a double
     * @param windowInitX the initial width of the screen as a double
     * @param windowInitY the initial height of the screen as a double
     */
    public final void initializeTransforms(final double windowInitHeight,
                                           final double windowInitWidth,
                                           final double windowInitX,
                                           final double windowInitY) {
        for (Transform transform : transforms) {
            TransformType type = transform.getType();
            double difference = calculateDelta(windowInitHeight,
                    windowInitWidth, windowInitX, windowInitY, transform);
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

    /**
     * Gets the z index of the shape.
     *
     * @return the z index as an integer
     */
    public final int getzIndex() {
        return zIndex;
    }

    /**
     * Changes the position of the top left of the shape.
     *
     * @param newX the X index of the top left of the shape as a double
     * @param newY the Y index of the top left of the shape as a double
     */
    public void changePosition(final double newX, final double newY) {
        this.initY = newY;
        this.initX = newX;
    }

    /**
     * Adds a transform to the shape.
     *
     * @param transform a transform as a Transform
     */
    public final void addTransform(final Transform transform) {
        transforms.add(transform);
    }

    /**
     * Returns a String representation of this ParaShape.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("ParaShape{");
        sb.append("initX=").append(initX);
        sb.append(", initY=").append(initY);
        sb.append(", initWidth=").append(initWidth);
        sb.append(", initHeight=").append(initHeight);
        sb.append(", initAngle=").append(initAngle);
        sb.append(", transforms=").append(transforms);
        sb.append(", zIndex=").append(zIndex);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this ParaShape to another Object for equality.
     *
     * @param object an Object
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ParaShape paraShape = (ParaShape) object;
        return Double.compare(initX, paraShape.initX) == 0
                && Double.compare(initY, paraShape.initY) == 0
                && Double.compare(initWidth, paraShape.initWidth) == 0
                && Double.compare(initHeight, paraShape.initHeight) == 0
                && Double.compare(initAngle, paraShape.initAngle) == 0
                && zIndex == paraShape.zIndex
                && Objects.equals(transforms, paraShape.transforms);
    }

    /**
     * Returns a hash code for this ParaShape.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                initX,
                initY,
                initWidth,
                initHeight,
                initAngle,
                transforms,
                zIndex
        );
    }
}
