package Shapes;

import java.awt.*;
import java.util.*;

public abstract class Component {
    protected Vector<Shape> shapes = new Vector<Shape>();
    public Vector<Shape> getShapes()
    {
        return this.shapes;
    }

    protected Vector<Color> colors = new Vector<Color>();
    public Vector<Color> getColors()
    {
        return this.colors;
    }
    
    
    protected float strokeWidth = 0;
    public float getStrokeWidth()
    {
        return this.strokeWidth;
    }

    public abstract Double getX();
    public abstract Double getY();
    public void Add(Shape shape, Color color)
    {
        this.shapes.add(shape);
        this.colors.add(color);
    }

}
