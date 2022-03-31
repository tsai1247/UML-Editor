package Line;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Vector;
import Shapes.Shapes;

public class composition extends Line{
    public composition(Shapes startShapes, Point startPos, Shapes endShapes, Point endPos) {
        super(startShapes, startPos, endShapes, endPos);
    }
    
    @Override
    public Vector<Line2D> getLines() {
        var ret = super.getLines();
        var originLine = ret.get(0);
        var arrowPoints = getArrowPoints(ret.get(0), arrowLength);
        var vector2 = extend(getVector(originLine), arrowLength*2*Math.cos(angle));
        var arrowMidPoint = new Point((int)(originLine.getX2() - vector2.getX()), (int)(originLine.getY2() - vector2.getY()));
        for(var point : arrowPoints)
        {
            ret.add(new Line2D.Double(originLine.getP2(), point));
            ret.add(new Line2D.Double(arrowMidPoint, point));
        }
        return ret;
    }
}
