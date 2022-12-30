package geometries;

import primitives.*;

public class Plane implements Geometry {
    private Point q0;
    private Vector normal;
    private double d; // d - the d parm in plane equation

    public Plane(Point point1, Point point2, Point point3) {
        if (point1.equals(point3) || point2.equals(point1) || point2.equals(point3)) {
            throw new IllegalArgumentException("points are coalesce with each other" + point1.toString() + ",  "
                    + point2.toString() + ", " + point3.toString());
        }
        if ((point2.subtract(point1).crossProduct(point3.subtract(point2)).length() == 0)/*
                                                                                          * point1.determinant(point1,
                                                                                          * point2, point3)
                                                                                          */) {
            throw new IllegalArgumentException("points are on the same line" + point1.toString() + ",  "
                    + point2.toString() + ", " + point3.toString());
        }
        Vector ab = (point2).subtract(point1);
        Vector ac = (point3).subtract(point1);
        normal = ab.crossProduct(ac);
        normal = normal.normalize();
        q0 = point1;
    }

    public Plane(Vector v, Point p) {
        q0 = p;
        normal = v.normalize();
        if (p.equals(new Point(0, 0, 0))) {
            d = 0;
        } else {
            d =  (v.dotProduct(p.subtract(new Point(0, 0, 0))));
        }
    }

    /**
     * @return normal {@link Vector}
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * @param p
     * @return normal {@link Vector}
     */
    public Vector getNormal(Point p) {
        return getNormal();
    }

    /**
     * @return q0 {@link Point}
     */
    public Point getQ0() {
        return q0;
    }

    public boolean isOnPlane(Point p) {
        if (normal.dotProduct(p.subtract(new Point(0, 0, 0))) - d == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Plane [q0=" + q0 + ", normal=" + normal + "]";
    }

}
