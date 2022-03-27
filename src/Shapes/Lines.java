package Shapes;

import java.util.Vector;
import java.awt.geom.*;
import java.awt.*;

public class Lines extends Component
{
    public Shapes startShape, endShape;
    public Point startPos, endPos;
    protected Vector<Line2D> lines = new Vector<Line2D>();

    public Lines(Shapes startShape, Point startPos, Shapes endShape, Point endPos)
    {
        this.startShape = startShape;
        this.endShape = endShape;
        this.strokeWidth = 2;
        this.startPos = getNearestPos(startShape, startPos);
        this.endPos = getNearestPos(endShape, endPos);
        lines.add(
             new Line2D.Double(this.startPos.getX(), this.startPos.getY(), this.endPos.getX(), this.endPos.getY())
        );
    }

    
    private Point getNearestPos(Shapes curShape, Point curPos) {
        Vector<Point> candidate = new Vector<Point>();

        candidate.add(new Point((int) (curShape.center().getX()) + curShape.width/2,  (int) (curShape.center().getY())));
        candidate.add(new Point((int) (curShape.center().getX()) - curShape.width/2,  (int) (curShape.center().getY())));
        candidate.add(new Point((int) (curShape.center().getX()), (int) curShape.center().getY() + curShape.height/2));
        candidate.add(new Point((int) (curShape.center().getX()), (int) curShape.center().getY() - curShape.height/2));

        Point ret = candidate.get(0);
        Double retDist = getDistance(candidate.get(0), curPos);
        for(int i=1; i<candidate.size(); i++)
        {
            var curDist = getDistance(candidate.get(i), curPos);
            if(curDist < retDist)
            {
                ret = candidate.get(i);
                retDist = curDist;
            }
        }

        return ret;
    }


    private Double getDistance(Point pos1, Point pos2) {
        Double x = (pos1.getX() - pos2.getX());
        Double y = (pos1.getY() - pos2.getY());
        return x*x+y*y;
    }


    @Override
    public Double getX() {
        return startPos.getX();
    }
    @Override
    public Double getY() {
        return startPos.getY();
    }


    @Override
    public int getWidth() {
        return (int) (endPos.getX() - startPos.getX());
    }


    @Override
    public int getHeight() {
        return (int) (endPos.getY() - startPos.getY());
    }


    @Override
    public Point getPoint() {
        return startPos;
    }
}