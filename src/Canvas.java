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
                    case ASSOCIATION:
                    case COMPOSITION:
                    case GENERALIZATION:
                        break;

                    case CLASS:
                        shape = new classification(e.getX(), e.getY());
                        break;
                    
                        case USECASE:
                        shape = new use_case(e.getX(), e.getY());
                        break;

                    default:
                        break;
                }

                if(shape != null)
                {
                    shapes.add(shape);
                    Repaint();
                }

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
                    case USECASE:
                    case CLASS:
                        return;
                    default:
                        break;
                }

                for (var shape : shapes)
                {
                    for (var s : shape.shapes)
                    {
                        
                        if (s.second().contains(me.getPoint())) {//check if mouse is clicked within shape
                            System.out.println("Pressed on " + shape.getClass().getName());
                            PressedShape = (Shapes) shape;
                            return;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                super.mouseReleased(me);
                switch(Menu.currentSelected)
                {
                    case SELECT:
                    case USECASE:
                    case CLASS:
                        return;
                    default:
                        break;
                }

                for (var shape : shapes)
                {
                    if(shape == PressedShape)
                        continue;
                    for (var s : shape.shapes)
                    {
                        if (s.second().contains(me.getPoint())) {//check if mouse is clicked within shape
                            System.out.println("Released on " + shape.getClass().getName());
                            switch(Menu.currentSelected)
                            {
                                case ASSOCIATION:
                                    shapes.add(new association(PressedShape, (Shapes)shape));
                                    break;
                                case GENERALIZATION:
                                    shapes.add(new generalization(PressedShape, (Shapes)shape));
                                    break;
                                case COMPOSITION:
                                    shapes.add(new composition(PressedShape, (Shapes)shape));
                                    break;
                                default:
                                    break;
                            }
                            
                            PressedShape = null;
                            Repaint();
                            return;
                        }
                    }
                }
                PressedShape = null;
            }

        });
    }

    
    protected void Repaint() {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        for(var i : shapes)
        {
            g.setStroke(new BasicStroke((float)i.strokeWidth));
            for(var j: i.shapes)
            {
                g.setColor(j.first());
                g.draw(j.second());
            }
        }
    }
}
