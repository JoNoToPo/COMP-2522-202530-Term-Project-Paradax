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

    public Transform(final TransformType transformType, final TransformSource transformSource,
                     final TransformPath path, final double multiplier) {
        this.type = transformType;
        this.source = transformSource;
        this.path = path;
        this.multiplier = multiplier;
    }

    public TransformType getType() {
        return type;
    }

    public TransformSource getSource() {
        return source;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public TransformPath getPath() {
        return path;
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) return false;
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
