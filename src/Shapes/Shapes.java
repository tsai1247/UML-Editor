package Shapes;

import java.awt.*;
import java.util.*;

public abstract class Shapes
{
    // Constructor
    public Shapes(Point pos, int width, int height)
    {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.strokeWidth = 4;
    }

    protected Point pos;
    // 0 1 2
    // 3 4 5
    // 6 7 8
    public Point getPoint(int index)
    {
        var x = this.getX() + (index % 3) * this.getWidth()/2.0;
        var y = this.getY() + (index / 3) * this.getHeight()/2.0;
        return new Point((int)x, (int)y);
    }

    public double getX()
    {
        return pos.getX();
    }
    public double getY()
    {
        return pos.getY();
    }
    public Point center()
    {
        int posX = (int) (pos.getX() + this.width/2);
        int posY = (int) (pos.getY() + this.height/2);
        return new Point(posX, posY);
    }

    private int width, height;
    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }
    


    private boolean _isSelected = false;
    public boolean isSelected()
    {
        return _isSelected;
    }
    
    
    public void setSelected(boolean isSelected)
    {
        this._isSelected = isSelected;
    }


    private float strokeWidth = 0;
    public float getStrokeWidth()
    {
        return this.strokeWidth;
    }

    private Vector<Shape> shapes = new Vector<Shape>();
    public Vector<Shape> getShapes()
    {
        return this.shapes;
    }
    
    private Vector<Color> colors = new Vector<Color>();
    public Vector<Color> getColors()
    {
        return this.colors;
    }

    public void Add(Shape shape, Color color)
    {
        this.shapes.add(shape);
        this.colors.add(color);
    }

    public void move(Point fromPos, Point toPos)
    {
        this.pos = this.plus(this.pos, this.distance(fromPos, toPos));
    }

    protected Point distance(Point pos1, Point pos2)   // return pos2 - pos1
    {
        var x = pos2.getX() - pos1.getX();
        var y = pos2.getY() - pos1.getY();
        return new Point((int)x, (int)y);
    }
    protected Point plus(Point pos1, Point pos2)   // return pos2 - pos1
    {
        var x = pos2.getX() + pos1.getX();
        var y = pos2.getY() + pos1.getY();
        return new Point((int)x, (int)y);
    }

    private String name = new String();
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
}