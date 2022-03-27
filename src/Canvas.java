import javax.swing.*;

import Shapes.Component;
import Shapes.Shapes;
import Shapes.association;
import Shapes.classification;
import Shapes.composition;
import Shapes.generalization;
import Shapes.use_case;

import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

public class Canvas {
    public Vector<Component> shapes = new Vector<Component>();
    public JPanel panel = new JPanel();
    private Shapes PressedShape = null;
    private Point PressedPos = null;
    public Canvas()
    {
        panel.setLayout(null);
        panel.setBounds(100, 0, 500, 600);
        panel.setBackground(Color.white);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click canvas at (" + e.getX() + ", " + e.getY() + ")");
                
                Shapes shape = null;
                switch(Menu.currentSelected)
                {
                    case SELECT:
                        ClearAllSelected();
                        var curShape = getClickedShape(e.getPoint());
                        if(curShape != null)
                        {
                            curShape.setSelected(true);
                        }
                        break;
                    case ASSOCIATION:
                    case COMPOSITION:
                    case GENERALIZATION:
                        break;

                    case CLASS:
                        shape = new classification(e.getPoint());
                        break;
                    
                        case USECASE:
                        shape = new use_case(e.getPoint());
                        break;

                    default:
                        break;
                }

                if(shape != null)
                {
                    shapes.add(shape);
                }
                Repaint();
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                super.mousePressed(me);
                if(PressedShape != null)
                    return;
                switch(Menu.currentSelected)
                {
                    case SELECT:
                        PressedPos = getClickedShape(me.getPoint()) == null ? me.getPoint() : null;
                    case USECASE:
                    case CLASS:
                        return;
                    default:
                        break;
                }
                PressedShape = getClickedShape(me.getPoint());
                PressedPos = me.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                super.mouseReleased(me);
                switch(Menu.currentSelected)
                {
                    case SELECT:
                        if(PressedPos != null && getDistance(PressedPos, me.getPoint()) > 10)
                            SelectAllInRegion(PressedPos, me.getPoint());
                        break;
                    case ASSOCIATION:
                        shapes.add(
                            new association(
                                PressedShape, PressedPos, getClickedShape(me.getPoint()), me.getPoint()
                            )
                        );
                        break;
                    case GENERALIZATION:
                        shapes.add(
                            new generalization(
                                PressedShape, PressedPos, getClickedShape(me.getPoint()), me.getPoint()
                            )
                        );
                        break;
                    case COMPOSITION:
                        shapes.add(
                            new composition(
                                PressedShape, PressedPos, getClickedShape(me.getPoint()), me.getPoint()
                            )
                        );
                        break;
                    default:
                        break;
                }
                
                PressedShape = null;
                Repaint();
                return;
            }

            private double getDistance(Point startPos, Point endPos) {
                var x = startPos.getX() - endPos.getX();
                var y = startPos.getY() - endPos.getY();
                return x*x + y*y;
            }

            private boolean smaller(Point p1, Point p2)
            {
                return p1.getX() < p2.getX() && p1.getY() < p2.getY();
            }

            private void SelectAllInRegion(Point startPos, Point endPos) {
                NormalizeRectPos(startPos, endPos);

                for(var i : shapes)
                {
                    var rectStartPos = i.getPoint();
                    var rectEndPos = new Point();
                    rectEndPos.x += rectStartPos.getX() + i.getWidth();
                    rectEndPos.y += rectStartPos.getY() + i.getHeight();

                    boolean condition = smaller(startPos, rectStartPos) && smaller(rectEndPos, endPos);
                    System.out.println(i.getClass().getName());
                    
                    if(i instanceof Shapes)
                    {
                        System.out.println("Selected: " + condition);
                        ((Shapes)i).setSelected(condition);
                    }
                }
            }

            private void NormalizeRectPos(Point startPos, Point endPos) {
                if(startPos.getX() > endPos.getX())
                {
                    var tmp = startPos.x;
                    startPos.x = endPos.x;
                    endPos.x = tmp;
                }
                if(startPos.getY() > endPos.getY())
                {
                    var tmp = startPos.y;
                    startPos.y = endPos.y;
                    endPos.y = tmp;
                }
                
            }

            private void ClearAllSelected() {
                for(var i : shapes)
                {
                    if(i instanceof Shapes)
                        ((Shapes)i).setSelected(false);
                }
            }


        });
            
    }

    
    protected Shapes getClickedShape(Point point)
    {

        for (var shape : shapes)
        {
            if(shape instanceof Shapes)
            {
                for(int i=0; i<shape.getShapes().size(); i++)
                {
                    if (shape.getShapes().get(i).contains(point))
                    {
                        return (Shapes) shape;
                    }                    
                }
            }
        }
        return null;
    }
    protected void Repaint() {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        for(var shape : shapes)
        {
            g.setStroke(new BasicStroke( shape.getStrokeWidth() ) );
            for(int i=0; i<shape.getShapes().size(); i++)
            {
                g.setColor(shape.getColors().get(i));
                g.draw(shape.getShapes().get(i));

            }
        }
    }
}
