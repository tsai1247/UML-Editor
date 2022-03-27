package Shapes;
import java.awt.geom.*;

import java.awt.*;


public class use_case extends Shapes{
    public use_case(Point pos) {
        super(pos);
        this.width = 120;
        this.height = 60;
        var Circle = new Ellipse2D.Double(this.pos.getX(), this.pos.getY(), this.width, this.height);
        this.Add(Circle, Color.BLACK);


    }
}
