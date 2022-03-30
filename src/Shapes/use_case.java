package Shapes;
import java.awt.geom.*;

import java.awt.*;


public class use_case extends Shapes{
    public use_case(Point pos) {
        super(pos, 120, 60);
        var Circle = new Ellipse2D.Double(this.getX(), this.getY(), this.width, this.height);
        this.Add(Circle, Color.BLACK);
    }
    
    @Override
    public void setPoint(Point pos) {
        super.setPoint(pos);
        this.shapes.clear();
        this.colors.clear();
        var Circle = new Ellipse2D.Double(this.getX(), this.getY(), this.width, this.height);
        this.Add(Circle, Color.BLACK);
    }
}
