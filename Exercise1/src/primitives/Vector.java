package primitives;

import java.util.List;

public class Vector extends Point {
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (x == 0 && y == 0 && z == 0) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }

    /**
     * @param v
     * @return add {@link Vector} to other vector
     */
    public Vector add(Vector v) {
        if (xyz.equals(v.xyz)) {
            throw new IllegalArgumentException("they the same vector");
        }
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
        return new Vector(xyz.reduce(length()));
    }

    // get point or vector and because vector is the san of the point 
    // it doesnt matter if it covert to point rather than vector because the action is the same.
    /**
     * @return dot product
     */
    public double dotProduct(Object obj) {
        if (obj instanceof Vector || obj instanceof Point){
            Point v = (Point)obj;
            return xyz.product(v.xyz).d1 + xyz.product(v.xyz).d2 + xyz.product(v.xyz).d3;
        }
        else{
            throw new IllegalArgumentException("Invalid object");
        }
    }

    public Vector crossProduct(Vector v) {
        Vector temp = new Vector(
                (xyz.d2 * v.xyz.d3) - (xyz.d3 * v.xyz.d2),
                (xyz.d3 * v.xyz.d1) - (xyz.d1 * v.xyz.d3),
                (xyz.d1 * v.xyz.d2) - (xyz.d2 * v.xyz.d1));
        if (temp.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vectors are parallel");
        }
        return temp;
    }

    public static boolean isOnSamePlane(Point... vertices) {
        return List.of(vertices).stream().allMatch(
                v -> new Point(
                        (vertices[0].xyz.d2 * v.xyz.d3) - (vertices[0].xyz.d3 * v.xyz.d2),
                        (vertices[0].xyz.d3 * v.xyz.d1) - (vertices[0].xyz.d1 * v.xyz.d3),
                        (vertices[0].xyz.d1 * v.xyz.d2) - (vertices[0].xyz.d2 * v.xyz.d1))
                        .equals(new Point(Double3.ZERO)));
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
