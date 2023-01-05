package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

public class Sphere implements Geometry {
    private Point center;
    private double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * @return center of the sphere {@link Point}
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param p
     * @return normal {@link Vector}
     */
    public Vector getNormal(Point p) {
        return (p.subtract(center)).normalize();
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Sphere [center=" + center + ", radius=" + radius + "]";
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> list = new ArrayList<Point>();
        if (ray.getP0().equals(center)) {
            list.add(ray.getPoint(radius));
            return list;
        }
        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt((u.length() * u.length()) - (tm * tm));
        if (d >= radius) {
            return null;
        }
        double th = Math.sqrt((radius * radius) - (d * d));
        if (!isZero(th + tm)) {
            if (tm + th > 0) {
                list.add(ray.getPoint(tm + th));
            }
            if (tm - th > 0) {
                list.add(ray.getPoint(tm - th));
            }
        }
        return list.size() == 0 ? null : list;
    }
}
