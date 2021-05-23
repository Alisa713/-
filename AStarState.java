import java.util.HashMap;

/**
 * Этот класс хранит базовое состояние, необходимое для алгоритма A* для вычисления пути по карте.
 * Это состояние включает в себя коллекцию "открытых путевых точек" и другую коллекцию
 *  "закрытых путевых точек"."
 * Кроме того, этот класс предоставляет основные операции,
 * необходимые алгоритму поиска пути A* для выполнения его обработки.
 **/
public class AStarState
{
    /** Это ссылка на карту, по которой перемещается алгоритм A*. **/
    private Map2D map;
    private HashMap<Location,Waypoint> opened_waypoints = new HashMap<Location,Waypoint>();
    private HashMap<Location,Waypoint> closed_waypoints = new HashMap<Location,Waypoint>();




    /**
     * Инициализируйте новый объект состояния для использования алгоритма поиска пути A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки
     * и возвращает путевую точку с минимальной общей стоимостью.
     * Если открытых путевых точек нет, этот метод возвращает <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        float min = -1;
        Waypoint waypoint = null;
        for (Waypoint w :opened_waypoints.values()) {
            if( min > w.getTotalCost() || min == -1) {
                min = w.getTotalCost();
                waypoint = w;
            }
        }
        return waypoint;
    }

    /**
     * Этот метод добавляет путевую точку в коллекцию "открытые путевые точки"
     * (или потенциально обновляет уже имеющуюся путевую точку). 
     * Если в местоположении новой путевой точки еще нет открытой путевой точки,
     * то новая путевая точка просто добавляется в коллекцию.
     * Однако, если в местоположении новой путевой точки уже есть путевая точка,
     * новая путевая точка заменяет старую <em>только в том случае, 
     * если</em> значение "предыдущей стоимости" новой путевой точки 
     * меньше значения "предыдущей стоимости" текущей путевой точки.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if(opened_waypoints.containsKey(newWP.loc)){
            if(opened_waypoints.get(newWP.loc).getPreviousCost() > newWP.getPreviousCost()){
                opened_waypoints.put(newWP.loc,newWP);
                return true;
            }

        }
        else {
            opened_waypoints.put(newWP.loc,newWP);
            return true;
        }
        return false;
    }


    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints()
    {
        return opened_waypoints.values().size();
    }


    /**
     * Этот метод перемещает путевую точку в указанном месте из открытого списка в закрытый список.
     **/
    public void closeWaypoint(Location loc)
    {
        closed_waypoints.put(loc,opened_waypoints.get(loc));
        opened_waypoints.remove(loc);
    }

    /**
     * Возвращает значение true, 
     * если коллекция закрытых путевых точек содержит путевую точку для указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}
