import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Shapes.Shapes;
import Shapes.composite;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MenuBar extends JMenuBar {
    protected JMenu file = new JMenu("File");

    protected JMenu edit = new JMenu("Edit");
    protected JMenuItem group = new JMenuItem("Group");
    protected JMenuItem ungroup = new JMenuItem("UnGroup");
    protected JMenuItem cname = new JMenuItem("Change object name");
    
    protected JMenu help = new JMenu("Help");
    protected JMenuItem about = new JMenuItem("About");

    public MenuBar()
    {
        this.add(file);
        
        this.add(edit);
        edit.add(group);
        group.setEnabled(false);
        edit.add(ungroup);
        ungroup.setEnabled(false);
        edit.add(cname);

        this.add(help);
        help.add(about);

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
        
        cname.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        
        about.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame f = new JFrame();
                JDialog msgbox = new JDialog(f);
                msgbox.setTitle("About");
                msgbox.setLayout(new FlowLayout());
                msgbox.add(new JLabel("Author: Wen-Lung, Tsai"));
                msgbox.add(new JLabel("Strudent ID: 107502565"));
                msgbox.setSize(260, 150);
                msgbox.setVisible(true);
            }
        });
        

    }
}
