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

}