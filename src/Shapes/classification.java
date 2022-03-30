package Shapes;
import java.awt.geom.*;

import java.awt.*;

public class classification extends Shapes{
    private int size = 3;

    public classification(Point pos) {
        super(pos, 80, 120);
        for(int i=0; i<size; i++)
        {
            var rect = new Rectangle2D.Double(this.getX(), this.getY()+this.height/this.size*i, 
                this.width, this.height/this.size);
            this.Add(rect, Color.BLACK);
        }
    }

    @Override
    public void setPoint(Point pos) {
        super.setPoint(pos);
        this.shapes.clear();
        this.colors.clear();
        for(int i=0; i<size; i++)
        {
            var rect = new Rectangle2D.Double(this.getX(), this.getY()+this.height/this.size*i,
                this.width, this.height/this.size);
            this.Add(rect, Color.BLACK);
        }
    }

}
