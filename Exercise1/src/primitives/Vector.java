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
        if (x == 0 && y == 0 && z == 0) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }


    public Vector(int i, int j, int k) {
        super(i, j, k);
        if (i == 0 && j == 0 && k == 0) {
            throw new IllegalArgumentException("the vector is the zero vector");
        }
    }

    /**
     * @param v
     * @return add {@link Vector} to other vector
     */
    public Vector add(Vector v) {
        if(xyz.equals(v.xyz))
        {
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
        Vector temp =  new Vector(
            (xyz.d2 * v.xyz.d3) - (xyz.d3 * v.xyz.d2),
            (xyz.d3 * v.xyz.d1) - (xyz.d1 * v.xyz.d3),
            (xyz.d1 * v.xyz.d2) - (xyz.d2 * v.xyz.d1)
        );
        if (temp.xyz.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("vectors are parallel");
        }
        return temp;
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
