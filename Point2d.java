// 2d точка
public class Point2d {
    private double xCoord;
    private double yCoord;

    public Point2d() {
        // Вызов конструктора с 2 параметрами
        this(0,0);
    }

    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }
}
