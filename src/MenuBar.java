import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Shapes.Shapes;
import Shapes.composite;

import java.awt.event.*;
import java.util.Vector;

public class MenuBar extends JMenuBar {
    protected JMenu file = new JMenu("File");
    protected JMenu edit = new JMenu("Edit");
    protected JMenuItem group = new JMenuItem("Group");
    protected JMenuItem ungroup = new JMenuItem("UnGroup");
    
    public MenuBar()
    {
        this.add(file);
        this.add(edit);
        group.setEnabled(false);
        ungroup.setEnabled(false);
        edit.add(group);
        edit.add(ungroup);

        group.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vector<Shapes> shapes = new Vector<Shapes>();
                for(int i=0; i<Main.canva.shapes.size(); i++)
                {
                    if(((Shapes)Main.canva.shapes.get(i)).isSelected())
                    {
                        shapes.add(((Shapes)Main.canva.shapes.get(i)));
                        shapes.lastElement().setSelected(false);;
                        Main.canva.shapes.remove(i);
                        i--;
                    }
                }
                Main.canva.shapes.add(new composite(shapes));
                ((Shapes)(Main.canva.shapes.lastElement())).setSelected(true);
                Main.canva.Repaint();
                group.setEnabled(false);
                ungroup.setEnabled(true);
            }
        });

        ungroup.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                composite curComposite = (composite) Main.canva.PressedShape;
                for(var shape : curComposite.getsubShapes())
                {
                    Main.canva.shapes.add(shape);
                    ((Shapes)Main.canva.shapes.lastElement()).setSelected(true);
                }
                Main.canva.shapes.remove(curComposite);
                Main.canva.Repaint();
                group.setEnabled(true);
                ungroup.setEnabled(false);
            }
        });

    }
}
