package Shapes;

import java.awt.*;
import java.util.*;

public abstract class Component {
    public Vector<Pair<Color, Shape>> shapes = new Vector<Pair<Color, Shape>>();
    public double strokeWidth = 0;
    public abstract int getX();
    public abstract int getY();
}
