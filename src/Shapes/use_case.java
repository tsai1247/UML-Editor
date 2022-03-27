package Shapes;
import java.awt.geom.*;

import java.awt.*;


public class use_case extends Shapes{
    public use_case(int x, int y) {
        super(x, y);
        this.width = 120;
        this.height = 60;
        var Circle = new Ellipse2D.Double(x, y, this.width, this.height);
        shapes.add(new Pair<>(Color.BLACK, Circle));


    }
}
