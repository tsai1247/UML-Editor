package Shapes;
import java.awt.geom.*;

import java.awt.*;


public class use_case extends Shapes{
    public use_case(Point pos) {
        super(pos, 120, 60);
        var Circle = new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.Add(Circle, Color.BLACK);
    }
    

    @Override
    public void move(Point fromPos, Point toPos) {
        super.move(fromPos, toPos);
        this.getShapes().clear();
        this.getColors().clear();
        var Circle = new Ellipse2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.Add(Circle, Color.BLACK);
    }
}
