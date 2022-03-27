package Shapes;

import java.awt.*;

public class association extends Lines
{
    public association(Shapes startShape, Point startPos, Shapes endShape, Point endPos)
    {
        super(startShape, startPos, endShape, endPos);
        for(var line : this.lines)
        {
            this.Add(line, Color.RED);
        }
    }


}