package Shapes;

import java.awt.*;

public class Shapes extends Component
{
    protected Point pos;
    protected int width, height;

    public Shapes(Point pos)
    {
        this.pos = pos;
        this.strokeWidth = 4;
    }

    public Double getX()
    {
        return pos.getX();
    }
    public Double getY()
    {
        return pos.getY();
    }

    public Point getPoint()
    {
        return pos;
    }

    public Point center()
    {
        int posX = (int) (pos.getX() + this.width/2);
        int posY = (int) (pos.getY() + this.height/2);
        return new Point(posX, posY);
    }
}