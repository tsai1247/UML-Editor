package Shapes;

import java.awt.geom.*;
import java.awt.*;

public class association extends Lines
{
    public association(Shapes startShape, Shapes endShape) {
        super(startShape, endShape);
        repaint();
        Line2D line = new Line2D.Double(startCoord.first(), startCoord.second(), endCoord.first(), endCoord.second());
        shapes.add( new Pair<>(Color.red, line) );
    }


}