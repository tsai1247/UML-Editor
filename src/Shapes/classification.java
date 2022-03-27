package Shapes;
import java.awt.geom.*;

import java.awt.*;

public class classification extends Shapes{
    private int size = 3;

    public classification(int x, int y) {
        super(x, y);
        this.width = 80;
        this.height = 120;
        for(int i=0; i<size; i++)
        {
            shapes.add(new Pair<>(Color.BLACK, new Rectangle2D.Double(x, y+height/size*i, width, height/size)));
        }
    }

}
