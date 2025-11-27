import java.util.Objects;

/**
 * Represents a transform in Paradax.
 *
 * @author JoNoToPo
 * @version 2025
 */
public final class Transform {
    private final TransformType type;
    private final TransformSource source;
    private final double multiplier;
    private final TransformPath path;

    /**
     * Constructs a transform.
     *
     * @param transformType the type of transform as a TransformType
     * @param transformSource the source of the transform as a TransformSource
     * @param path the path the transform takes as a TransformPath
     * @param multiplier the multiplier as a double
     */
    public Transform(final TransformType transformType, final TransformSource transformSource,
                     final TransformPath path, final double multiplier) {
        this.type = transformType;
        this.source = transformSource;
        this.path = path;
        this.multiplier = multiplier;
    }

    /**
     * Gets the type of transform.
     *
     * @return the type of transform as a TransformType
     */
    public TransformType getType() {
        return type;
    }

    /**
     * Gets the source of transform.
     *
     * @return the source of transform as a TransformSource
     */
    public TransformSource getSource() {
        return source;
    }

    /**
     * Gets the path of transform.
     *
     * @return the path of transform as a TransformPath
     */
    public TransformPath getPath() {
        return path;
    }

    /**
     * Gets the multiplier.
     *
     * @return the multiplier as a double
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Returns a String representation of this Transform.
     *
     * @return a String
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("Transform{");
        sb.append("type=").append(type);
        sb.append(", source=").append(source);
        sb.append(", multiplier=").append(multiplier);
        sb.append(", path=").append(path);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this Transform to another Object for equality.
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
        Transform transform = (Transform) object;
        return Double.compare(multiplier, transform.multiplier) == 0
                && type == transform.type
                && source == transform.source
                && path == transform.path;
    }

    /**
     * Returns a hash code for this Transform.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                type,
                source,
                multiplier,
                path
        );
    }
}
