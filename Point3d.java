import static java.lang.Math.*;

public class Point3d {
    private double x;
    private double y;
    private double z;

    public Point3d() {
        x = y = z = 0.0;
    }

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Проверка координат точек на идентичность
    public boolean isEqual(Point3d p) {
        if (this.x == p.getX()) {
            if (this.y == p.getY()) {
                if (this.z == p.getZ()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Возвращает расстояние между двумя точками
    public double distanceTo(Point3d p) {
        return sqrt(pow(p.getX() - this.x, 2) + pow(p.getY() - this.y, 2) + pow(p.getZ() - this.z, 2));
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
