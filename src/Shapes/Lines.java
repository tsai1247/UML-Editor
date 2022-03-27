package Shapes;

import java.util.Vector;
import java.awt.geom.*;

public class Lines extends Component
{
    public Shapes startShape, endShape;
    public Pair<Double, Double> startPos, endPos;
    protected Vector<Line2D> lines = new Vector<Line2D>();

    public Lines(Shapes startShape, Pair<Integer, Integer> startPos, Shapes endShape, Pair<Integer, Integer> endPos)
    {
        this.startShape = startShape;
        this.endShape = endShape;
        this.strokeWidth = 2;
        this.startPos = getNearestPos(startShape, startPos);
        this.endPos = getNearestPos(endShape, endPos);
        lines.add(
             new Line2D.Double(this.startPos.first(), this.startPos.second(), this.endPos.first(), this.endPos.second())
        );
    }

    
    private Pair<Double, Double> getNearestPos(Shapes curShape, Pair<Integer, Integer> curPos) {
        Vector<Pair<Double, Double>> candidate = new Vector<Pair<Double, Double>>();
        
        candidate.add(new Pair<>(curShape.center().first() + curShape.width/2, curShape.center().second()));
        candidate.add(new Pair<>(curShape.center().first() - curShape.width/2, curShape.center().second()));
        candidate.add(new Pair<>(curShape.center().first(), curShape.center().second() + curShape.height/2));
        candidate.add(new Pair<>(curShape.center().first(), curShape.center().second() - curShape.height/2));

        Pair<Double, Double> ret = candidate.get(0);
        double retDist = getDistance(candidate.get(0), curPos);
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


    private double getDistance(Pair<Double, Double> pos1, Pair<Integer, Integer> pos2) {
        double x = (pos1.first() - pos2.first());
        double y = (pos1.second() - pos2.second());
        return x*x+y*y;
    }


    @Override
    public int getX() {
        return (int) Math.floor(startPos.first());
    }
    @Override
    public int getY() {
        return (int) Math.floor(startPos.second());
    }
}