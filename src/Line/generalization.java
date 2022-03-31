package Line;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Vector;
import Shapes.Shapes;



public class generalization extends Line{
    public generalization(Shapes startShapes, Point startPos, Shapes endShapes, Point endPos) {
        super(startShapes, startPos, endShapes, endPos);
    }

    @Override
    public Vector<Line2D> getLines() {
        var ret = super.getLines();
        var originLine = ret.get(0);
        var arrowPoints = getArrowPoints(ret.get(0), arrowLength);
        for(var point : arrowPoints)
        {
            ret.add(new Line2D.Double(originLine.getP2(), point));
        }
        ret.add(new Line2D.Double(arrowPoints.get(0), arrowPoints.get(1)));
        return ret;
    }
}
