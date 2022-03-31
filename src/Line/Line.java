package Line;

import java.awt.*;
import java.util.*;

import java.awt.geom.*;
import Shapes.Shapes;

public abstract class Line
{
    enum Locate
    {
        WESTNORTH,
        NORTH,
        EASTNORTH,
        WEST,
        CENTER,
        EAST,
        WESTSOUTH,
        SOUTH,
        EASTSOUTH
    }
    // Constructor
    public Line(Shapes startShapes, Point startPos, Shapes endShapes, Point endPos)
    {
        this.startShapes = startShapes;
        this.startLocate = getLocate(startShapes, startPos);
        this.endShapes = endShapes;
        this.endLocate = getLocate(endShapes, endPos);
        this.strokeWidth = 4;

    }

    private Locate getLocate(Shapes curShapes, Point curPos) {
        Locate ret = null;
        double squareDistance = Double.MAX_VALUE;
        for(int i=1; i<8; i+=2)
        {
            var curDistance = this.squareDistance(curShapes.getPoint(i), curPos);
            if(squareDistance > curDistance)
            {
                ret = Locate.values()[i];
                squareDistance = curDistance;
            }
        }
        return ret;
    }

    private double squareDistance(Point pos1, Point pos2) {
        var x = pos1.getX() - pos2.getX();
        var y = pos1.getY() - pos2.getY();
        return x*x + y*y;
    }

    private Shapes startShapes, endShapes;
    private Locate startLocate, endLocate;

    public Vector<Line2D> getLines()
    {
        var ret = new Vector<Line2D>();
        var startPos = startShapes.getPoint(startLocate.ordinal());
        var endPos = endShapes.getPoint(endLocate.ordinal());

        var basicline = new Line2D.Double(
            startPos.getX(), startPos.getY(), endPos.getX(), endPos.getY()
        );
        ret.add(basicline);

        return ret;
    }

    private float strokeWidth = 0;
    public float getStrokeWidth()
    {
        return this.strokeWidth;
    }
    protected static double angle = Math.toRadians(45);
    protected static int arrowLength = 15;
    public static void setAngle(double degree)
    {
        angle = Math.toRadians(degree);
    }
    public static void setArrowLength(int newarrowLength)
    {
        arrowLength = newarrowLength;
    }
    public static int getAngle()
    {
        return (int)Math.toDegrees(angle);
    }
    public static int getArrowLength()
    {
        return arrowLength;
    }
    
    protected Vector<Point> getArrowPoints(Line2D originLine, int arrowLength)
    {
        Vector<Point> ret = new Vector<Point>();
        var vector = new Point((int)(originLine.getX2() - originLine.getX1()), (int)(originLine.getY2() - originLine.getY1()));
        var newvector = new Point((int)(vector.getX() * Math.cos(angle) - vector.getY() * Math.sin(angle)), (int)(vector.getX()*Math.sin(angle) + vector.getY()*Math.cos(angle)));
        newvector = extend(newvector, arrowLength);
        var newpoint = new Point((int)(originLine.getX2() - newvector.getX()), (int)(originLine.getY2() - newvector.getY()));
        ret.add(newpoint);

        newvector = new Point((int)(vector.getX() * Math.cos(angle) + vector.getY()*Math.sin(angle)), (int)(-vector.getX()*Math.sin(angle) + vector.getY()*Math.cos(angle)));
        newvector = extend(newvector, arrowLength);
        newpoint = new Point((int)(originLine.getX2() - newvector.getX()), (int)(originLine.getY2() - newvector.getY()));
        ret.add(newpoint);
        return ret;
    }

    protected Point getVector(Point startPoint, Point endPoint)
    {
        var x = endPoint.getX() - startPoint.getX();
        var y = endPoint.getY() - startPoint.getY();
        return new Point((int)x, (int)y);
    }
    protected Point getVector(Line2D line)
    {
        var p1 = new Point((int)(line.getX1()), (int)(line.getY1()));
        var p2 = new Point((int)(line.getX2()), (int)(line.getY2()));
        return this.getVector(p1, p2);
    }
    protected Point extend(Point vector, double leng) {
        var x = vector.getX()*leng / getLength(vector);
        var y = vector.getY()*leng / getLength(vector);
        return new Point((int)x, (int)y);
    }
    public double getLength(Point vector)
    {
        
        return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

}