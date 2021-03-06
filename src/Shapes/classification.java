package Shapes;
import java.awt.geom.*;

import java.awt.*;

public class classification extends Shapes{
    

    public classification(Point pos) {
        super(pos, 80, 120);
        this.size = 3;
        for(int i=0; i<this.size; i++)
        {
            var rect = new Rectangle2D.Double(this.getX(), this.getY()+this.getHeight()/this.size*i, 
                this.getWidth(), this.getHeight()/this.size);
            this.Add(rect, Color.BLACK);
        }
        this.setName("Class");
    }
    
    @Override
    public void move(Point fromPos, Point toPos) {
        super.move(fromPos, toPos);
        this.getShapes().clear();
        this.getColors().clear();
        
        for(int i=0; i<size; i++)
        {
            var rect = new Rectangle2D.Double(this.getX(), this.getY()+this.getHeight()/this.size*i,
                this.getWidth(), this.getHeight()/this.size);
            this.Add(rect, Color.BLACK);
        }
    }

}
