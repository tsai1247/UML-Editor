package Shapes;

import java.awt.Point;
import java.util.Vector;

public class composite extends Shapes {

    protected Vector<Shapes> subShapes;
    public composite(Vector<Shapes> subShapes)
    {
        super(getLeftTopPos(subShapes), getTotalWidth(subShapes), getTotalHeight(subShapes));
        this.subShapes = subShapes;
        for(var shape : subShapes)
        {
            for(int i=0; i<shape.getShapes().size(); i++)
            {
                this.Add(shape.getShapes().get(i), shape.getColors().get(i));
            }
        }
    }

    public Vector<Shapes> getsubShapes()
    {
        return this.subShapes;
    }

    private static Point getLeftTopPos(Vector<Shapes> subShapes) {
        double x = subShapes.get(0).getX();
        double y = subShapes.get(0).getY();
        
        for(var shape : subShapes)
        {
            if(shape.getX() < x)
                x = shape.getX();

            if(shape.getY() < y)
                y = shape.getY();
        }
        return new Point((int)x, (int)y);
    }
    private static Point getRightBottomPos(Vector<Shapes> subShapes) {
        double x = subShapes.get(0).getX() + subShapes.get(0).getWidth();
        double y = subShapes.get(0).getY() + subShapes.get(0).getHeight();
        
        for(var shape : subShapes)
        {
            if(shape.getX() + shape.getWidth() > x)
                x = shape.getX() + shape.getWidth();

            if(shape.getY() + shape.getHeight() > y)
                y = shape.getY() + shape.getHeight();
        }
        return new Point((int)x, (int)y);
    }

    private static int getTotalWidth(Vector<Shapes> subShapes) {
        return (int) (getRightBottomPos(subShapes).getX() - getLeftTopPos(subShapes).getX());
    }

    private static int getTotalHeight(Vector<Shapes> subShapes) {
        return (int) (getRightBottomPos(subShapes).getY() - getLeftTopPos(subShapes).getY());
    }
}
