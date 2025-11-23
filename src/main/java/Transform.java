public final class Transform {
    private TransformType type;
    private TransformSource source;
    private double multiplier;
    private TransformPath path;

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

    public void setType(TransformType type) {
        this.type = type;
    }

    public TransformSource getSource() {
        return source;
    }

    public void setSource(TransformSource source) {
        this.source = source;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public TransformPath getPath() {
        return path;
    }

    public void setPath(TransformPath path) {
        this.path = path;
    }
}
