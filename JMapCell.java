import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Этот класс является пользовательским компонентом 
 * Swing для представления одной ячейки карты на 2D-карте.
 * Клетка имеет несколько различных типов состояний,
 * но самое основное состояние-это то, является ли клетка проходимой или нет.
 */
public class JMapCell extends JComponent
{
    private static final Dimension CELL_SIZE = new Dimension(12, 12);
    
    /** Значение True указывает, что ячейка является конечной точкой, либо начальной, либо конечной. **/
    boolean endpoint = false;
    
    
    /** True указывает, что ячейка проходима; false означает, что это не так. **/
    boolean passable = true;
    
    /**
     * True указывает, что эта ячейка является частью пути между началом и концом.
     **/
    boolean path = false;
    
    /**
     * Постройте новую ячейку карты с заданной "проходимостью"." 
     * Ввод true означает, что ячейка проходима.
     **/
    public JMapCell(boolean pass)
    {
        // Установите предпочтительный размер ячейки, чтобы управлять начальным размером окна.
        setPreferredSize(CELL_SIZE);
        
        setPassable(pass);
    }
    
    /** Создайте новую ячейку карты, которая по умолчанию является проходимой. **/
    public JMapCell()
    {
        // Вызовите другой конструктор, указав true для "passable".
        this(true);
    }
    
    /** Помечает эту ячейку как начальную или конечную. **/
    public void setEndpoint(boolean end)
    {
        endpoint = end;
        updateAppearance();
    }
    
    /**
     * Устанавливает эту ячейку как проходимую или не проходимую.
     * Вход true помечает ячейку как проходимую; вход false помечает ее как не проходимую.
     **/
    public void setPassable(boolean pass)
    {
        passable = pass;
        updateAppearance();
    }
    
    /** Возвращает true, если эта ячейка проходима, или false в противном случае. **/
    public boolean isPassable()
    {
        return passable;
    }
    
    /** Переключает текущее "проходимое" состояние ячейки карты. **/
    public void togglePassable()
    {
        setPassable(!isPassable());
    }
    
    /** Помечает эту ячейку как часть пути, обнаруженного алгоритмом A*. **/
    public void setPath(boolean path)
    {
        this.path = path;
        updateAppearance();
    }
    
    /**
     * Этот вспомогательный метод обновляет цвет фона в соответствии
     * с текущим внутренним состоянием ячейки.
     **/
    private void updateAppearance()
    {
        if (passable)
        {
            // Сносная клетка. Укажите его состояние с границей.
            setBackground(Color.WHITE);

            if (endpoint)
                setBackground(Color.CYAN);
            else if (path)
                setBackground(Color.GREEN);
        }
        else
        {
            // Непроходимая клетка... Сделай все красным.
            setBackground(Color.RED);
        }
    }

    /**
     * Реализация метода paint для рисования цвета фона в ячейке карты.
     **/
    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}