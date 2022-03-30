import javax.swing.*;
import Shapes.Shapes;
import Shapes.classification;
import Shapes.composite;
import Shapes.use_case;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

public class Canvas {
    protected Vector<Shapes> shapesList = new Vector<Shapes>();
    public JPanel panel = new JPanel();
    protected Shapes PressedShape = null;
    protected Point PressedPos = null;

    public void Display()
    {
        System.out.println("\n----");

        for(var i : shapesList)
        {
            System.out.println(i.getClass().getName() + ": (" + i.getX() + ", " + i.getY() + ")" + ", Selected: " + i.isSelected());
        }
        System.out.println("----\n");
    }
    public Canvas()
    {
        panel.setLayout(null);
        panel.setBounds(100, 0, 500, 600);
        panel.setBackground(Color.white);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(SideBar.currentSelected)
                {
                    case SELECT:
                        ClearAllSelected();
                        var curShape = getClickedShape(shapesList, e.getPoint());
                        if(curShape != null)
                        {
                            curShape.setSelected(true);
                        }

                        if(curShape instanceof composite)
                        {
                            Main.menuBar.group.setEnabled(false);
                            Main.menuBar.ungroup.setEnabled(true);
                            composite.selectedComposite = (composite) curShape;
                        }
                        else
                        {
                            Main.menuBar.group.setEnabled(false);
                            Main.menuBar.ungroup.setEnabled(false);
                            composite.selectedComposite = null;
                        }
                        break;

                    case CLASS:
                        shapesList.add(new classification(e.getPoint()));
                        break;
                    case USECASE:
                        shapesList.add(new use_case(e.getPoint()));
                        break;

                    case ASSOCIATION:
                    case COMPOSITION:
                    case GENERALIZATION:
                    default:
                        break;
                }
                Repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if(SideBar.currentSelected != SideBar.Selected.USECASE && SideBar.currentSelected != SideBar.Selected.CLASS)
                {
                    PressedShape = getClickedShape(shapesList, me.getPoint());
                    PressedPos = me.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                var releasedShape = getClickedShape(shapesList, me.getPoint());
                switch(SideBar.currentSelected)
                {
                    case SELECT:
                        if(PressedShape == null)        // select a region
                        {
                            SelectAllInRegion(PressedPos, me.getPoint());
                        }
                        else if(PressedPos != null && getDistance(PressedPos, me.getPoint()) > 15*15)     // move a Shapes
                        {
                            PressedShape.move(/* from */PressedPos, /* to */ me.getPoint());
                        }
                        break;
                    case ASSOCIATION:
                        if(PressedShape != null && releasedShape != null)
                        {
                            // shapes.add(
                            //     new association(PressedShape, PressedPos, curShape, me.getPoint())
                            // );
                        }
                        break;
                    case GENERALIZATION:
                    if(PressedShape != null && releasedShape != null)
                        {
                            // shapes.add(
                            //     new generalization(PressedShape, PressedPos, curShape, me.getPoint())
                            // );
                        }
                        break;
                    case COMPOSITION:
                    if(PressedShape != null && releasedShape != null)
                        {
                            // shapes.add(
                            //     new composition(PressedShape, PressedPos, curShape, me.getPoint())
                            // );
                        }
                        break;
                    default:
                        break;
                }
                
                PressedShape = null;
                Repaint();
            }

            private double getDistance(Point startPos, Point endPos) {
                var x = startPos.getX() - endPos.getX();
                var y = startPos.getY() - endPos.getY();
                return x*x + y*y;
            }

            private void SelectAllInRegion(Point startPos, Point endPos) {
                NormalizeRectPos(startPos, endPos);
                int cnt = 0;
                Shapes curShape = null;
                for(var i : shapesList)
                {

                    if(inRegion(startPos, endPos, i))
                    {
                        
                        curShape =  i;
                        cnt++;
                        i.setSelected(true);
                    }
                    else
                        i.setSelected(false);

                }
                if(cnt > 1)
                {
                    Main.menuBar.group.setEnabled(true);
                    Main.menuBar.ungroup.setEnabled(false);
                }
                else if(cnt == 1 && curShape instanceof composite)
                {
                    Main.menuBar.group.setEnabled(false);
                    Main.menuBar.ungroup.setEnabled(true);
                }
                else
                {
                    Main.menuBar.group.setEnabled(false);
                    Main.menuBar.ungroup.setEnabled(false);
                }
            }


            private boolean inRegion(Point startPos, Point endPos, Shapes i) {
                var rectStartPos = i.getPoint(0);
                var rectEndPos = i.getPoint(8);

                return smaller(startPos, rectStartPos) && smaller(rectEndPos, endPos);
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

        });
            

    }

    public void ClearAllSelected() {
        for(var i : shapesList)
        {
                i.setSelected(false);
        }
    }
    private boolean smaller(Point p1, Point p2) {
        return p1.getX() < p2.getX() && p1.getY() < p2.getY();
    }
    private boolean pointInRegion(Point startPos, Point endPos, Point pos) {

        return smaller(startPos, pos) && smaller(pos, endPos);
    }
    protected Shapes getClickedShape(Vector<Shapes> shapesList, Point point)
    {
        for(int i=shapesList.size()-1; i >=0; i--)
        {
            var curShape = shapesList.get(i);
            if(pointInRegion(curShape.getPoint(0), curShape.getPoint(8), point))
                    return curShape;
        }
        return null;
    }

    protected void Repaint()
    {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        Repaint(this.shapesList);
    }

    protected void Repaint(Vector<Shapes> shapesList) {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        for(var shapes : shapesList)
        {
            if(shapes instanceof composite)
            {
                Repaint(((composite)shapes).getsubShapes());
            }

            g.setStroke(new BasicStroke( shapes.getStrokeWidth() ) );
            for(int i=0; i<shapes.getShapes().size(); i++)
            {
                g.setColor(shapes.getColors().get(i));
                g.draw(shapes.getShapes().get(i));
            }
            if(shapes.isSelected())
            {
                PaintSelectSquare(shapes);
            }
        }
    }

    
    private int selectedMarkSize = 10;
    private Shape getSelectSquare(double posX, double posY)
    {
        var x = posX - selectedMarkSize/2;
        var y = posY - selectedMarkSize/2;
        return new Rectangle2D.Double(x, y, selectedMarkSize, selectedMarkSize);
    }
    
    private void PaintSelectSquare(Shapes shapes) {
        Graphics2D g = (Graphics2D) panel.getGraphics();
        g.setStroke(new BasicStroke( 5 ) );
        g.setColor(Color.PINK);
        for(int i=1; i<8; i+=2)
        {
            var centerPos = shapes.getPoint(i);
            var selectSquare = getSelectSquare(centerPos.getX(), centerPos.getY());
            g.draw(selectSquare);
        }
    }
}
