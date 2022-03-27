package Shapes;

import java.util.Vector;

public class Lines extends Component
{
    public Lines(Shapes startShape, Shapes endShape)
    {
        this.startShape = startShape;
        this.endShape = endShape;
        this.strokeWidth = 2;
    }

    public Shapes startShape, endShape;
    public Pair<Double, Double> startCoord, endCoord;
    
    private boolean between(Double first, Double first2, Double first3) {
        return (first <= first2 && first2 <= first3) || (first >= first2 && first2 >= first3);
    }
    public Pair<Double, Double> getEndCoord()
    {
        Pair<Double, Double> curlinefunc = getLine(startShape, endShape);
        return getCounter(endShape, startShape, curlinefunc);
    }

    private Pair<Double, Double> getCounter(Shapes shape, Shapes anotherShape, Pair<Double, Double> curlinefunc) {
        Vector<Pair<Double, Double>> ret = new Vector<>();
        var a = curlinefunc.first();
        var b = curlinefunc.second();
        if(shape instanceof classification)
        {
            ret.add(new Pair<Double, Double>(shape.x, (a*shape.x + b)));
            ret.add(new Pair<Double, Double>(shape.x + shape.width, (a*(shape.x + shape.width) + b)));
            ret.add(new Pair<Double, Double>(((shape.y-b)/a), shape.y));
            ret.add(new Pair<Double, Double>((((shape.y + shape.height)-b)/a), shape.y + shape.height));
        }
        else if(shape instanceof use_case)
        {
            ret.add(shape.center());
        }
        
        for(var i : ret)
        {
            if(Math.abs(i.first() - shape.center().first()) <= shape.width/2 && Math.abs(i.second() - shape.center().second()) <= shape.height/2 )
            if(between(shape.center().first(), i.first(), anotherShape.center().first()) && between(shape.center().second(), i.second(), anotherShape.center().second()) )
            {
                return i;
            }
        }
        return null;
    }
    private Pair<Double, Double> getLine(Shapes startShape, Shapes endShape) {
        double a = (startShape.center().second() - endShape.center().second()) / (startShape.center().first() - endShape.center().first()) ;
        double b = startShape.center().second() - a * startShape.center().first();
        return new Pair<Double, Double>(a, b);
    }
    public Pair<Double, Double> getStartCoord()
    {
        Pair<Double, Double> curlinefunc = getLine(startShape, endShape);
        return getCounter(startShape, endShape, curlinefunc);
    }

    public void repaint()
    {
        startCoord = getStartCoord();
        endCoord = getEndCoord();
    }

    @Override
    public int getX() {
        return (int) Math.floor(startCoord.first());
    }
    @Override
    public int getY() {
        return (int) Math.floor(startCoord.second());
    }
}