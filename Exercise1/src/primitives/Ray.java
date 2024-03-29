package primitives;

import java.util.List;
import java.util.stream.Collectors;

import geometries.Intersectable.GeoPoint;

public class Ray {
    private final Point p0;
    private final Vector dir;

    public Ray(Vector v, Point p) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * @return P0 {@link Point}
     */
    public Point getP0() {
        return p0;
    }

    /**
     * @return Dir {@link Vector}
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * @param obj
     * @return true if equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Ray))
            return false;
        return p0.equals(((Ray) obj).p0) && dir.equals(((Ray) obj).dir);
    }

    /**
     * @param t
     * @return Pc {@link Point} - point on the view plane
     */
    public Point getPoint(double t) {
        return getP0().add(getDir().scale(t));
    }

    /**
     * @param list
     * @return the closest {@link Point}
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(
                        (points.stream().map(p -> new GeoPoint(null, p))).collect(Collectors.toList())).point;

    }

    @Override
    public String toString() {
        return p0.toString() + ", " + dir.toString();
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        if (geoPointList == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance; // the distance between the "this.p0" to each point in the list

        if (!geoPointList.isEmpty()) {
            for (var geoPoint : geoPointList) {
                geoPointDistance = this.p0.distance(geoPoint.point);
                if (geoPointDistance < minDistance) {
                    minDistance = geoPointDistance;
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }
}
