package Shapes;

import java.awt.*;

public class generalization extends Lines
{
    public generalization(Shapes startShape, Pair<Integer, Integer> startPos, Shapes endShape, Pair<Integer, Integer> endPos)
    {
        super(startShape, startPos, endShape, endPos);
        for(var line : this.lines)
            shapes.add( new Pair<>(Color.green, line) );
    }


}