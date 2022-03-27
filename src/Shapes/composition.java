package Shapes;

import java.awt.*;

public class composition extends Lines
{
    public composition(Shapes startShape, Pair<Integer, Integer> startPos, Shapes endShape, Pair<Integer, Integer> endPos)
    {
        super(startShape, startPos, endShape, endPos);
        for(var line : this.lines)
            shapes.add( new Pair<>(Color.yellow, line) );
    }


}