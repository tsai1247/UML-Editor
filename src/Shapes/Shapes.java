package Shapes;

import java.awt.*;
import java.awt.geom.*;

public class Shapes extends Component
{
    protected Point pos;
    protected int width, height;
    protected boolean _isSelected = false;
    protected int selectedMarkSize = 10;

    public Shapes(Point pos, int width, int height)
    {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.strokeWidth = 4;

    }

    public boolean isSelected()
    {
        return _isSelected;
    }
    public void setSelected(boolean isSelected)
    {
        if(this._isSelected == isSelected)   return;
        this._isSelected = isSelected;

        if(this._isSelected)
        {
            this.Add(getRectangle(this.getX() + this.width/2, this.getY(), selectedMarkSize), Color.PINK);
            this.Add(getRectangle(this.getX() + this.width/2, this.getY() + this.height, selectedMarkSize), Color.PINK);
            this.Add(getRectangle(this.getX(), this.getY() + this.height/2, selectedMarkSize), Color.PINK);
            this.Add(getRectangle(this.getX() + this.width, this.getY() + this.height/2, selectedMarkSize), Color.PINK);
        }
        else
        {
            while(this.shapes.size() > 0)
            {
                if(this.shapes.lastElement() instanceof Rectangle2D)
                {
                    var curWidth = ((Rectangle2D)this.shapes.lastElement()).getWidth();
                    var curHeight = ((Rectangle2D)this.shapes.lastElement()).getHeight();
                    if(curWidth == curHeight)
                        this.shapes.remove(this.shapes.size()-1);
                    else
                        break;
                }
                else
                    break;
            }
        }
    }

    protected Shape getRectangle(Point center, int size)
    {
        return getRectangle(center.getX(), center.getY(), size);
    }
    protected Shape getRectangle(double posX, double posY, int size)
    {
        var x = posX - size/2;
        var y = posY - size/2;
        return new Rectangle2D.Double(x, y, selectedMarkSize, selectedMarkSize);
    }
    public Double getX()
    {
        return pos.getX();
    }
    public Double getY()
    {
        return pos.getY();
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
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

    public void setPoint(Point pos) {
        this.pos = pos;
    }

}