package Shapes;

import java.awt.*;

public class generalization extends Lines
{
    public generalization(Shapes startShape, Point startPos, Shapes endShape, Point endPos)
    {
        super(startShape, startPos, endShape, endPos);
        for(var line : this.lines)
        {
            this.Add(line, Color.GREEN);
        }
    }


}