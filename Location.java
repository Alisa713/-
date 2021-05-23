import java.util.Objects;

/**
 * Этот класс представляет определенное местоположение на 2D-карте.
 * Координаты-это целочисленные значения.
 **/
public class Location
{
    /** Координата X этого местоположения. **/
    public int xCoord;

    /** Y координата этого местоположения. **/
    public int yCoord;


    /** Создает новое местоположение с заданными целочисленными координатами. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Создает новое местоположение с координатами (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        Location location = (Location) o;
        return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
    }
    public int hashCode(){

        return Objects.hash(xCoord, yCoord);
    }

}