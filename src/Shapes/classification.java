package Shapes;
import java.awt.geom.*;

import java.awt.*;

public class classification extends Shapes{
    private int size = 3;

    public classification(Point pos) {
        super(pos, 80, 120);
        for(int i=0; i<size; i++)
        {
            var rect = new Rectangle2D.Double(pos.getX(), pos.getY()+height/size*i, width, height/size);
            this.Add(rect, Color.BLACK);
        }
    }

}
