package primitives;

public class Vector extends Point {
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }

    public Vector(Double x, Double y, Double z) {
        super(x, y, z);
        if (new Double3(x, y, z).equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }

    /**
     * @param v
     * @return add {@link Vector} to other vector
     */
    public Vector add(Vector v) {
        return new Vector(super.add(v).xyz);
    }

    /**
     * @param multiplier
     * @return thw {@link Vector} time multiplier
     */
    public Vector scale(double multiplier) {
        return new Vector(xyz.scale(multiplier));
    }

    /**
     * @return squared length of the Vector
     */
    public double lengthSquared() {
        return distanceSquared(new Point(Double3.ZERO), new Point(xyz));
    }

    /**
     * @return length of the Vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return normalize the vector
     */
    public Vector normalize() {
        return new Vector(xyz.scale(1 / length()));
    }

    /**
     * @return dot product
     */
    public double dotProduct(Vector v) {
        return xyz.product(v.xyz).d1 + xyz.product(v.xyz).d2 + xyz.product(v.xyz).d3;
    }

    public Vector crossProduct(Vector v)
    {
        return new Vector(
            (xyz.d2 * v.xyz.d3) - (xyz.d3 * v.xyz.d2),
            (xyz.d3 * v.xyz.d1) - (xyz.d1 * v.xyz.d3),
            (xyz.d1 * v.xyz.d2) - (xyz.d2 * v.xyz.d1)
        );
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Vector))
            return false;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }
}
