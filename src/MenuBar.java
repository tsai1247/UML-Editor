import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

    
    protected JMenu setting = new JMenu("Setting");
    protected JCheckBoxMenuItem cnameAutoUpdate = new JCheckBoxMenuItem("Preview when changing name");
    protected JCheckBoxMenuItem preferFontSize = new JCheckBoxMenuItem("Use preferred font size", true);

    public MenuBar()
    {
        this.add(file);
        
        this.add(edit);
        edit.add(group);
        group.setEnabled(false);
        edit.add(ungroup);
        ungroup.setEnabled(false);
        edit.add(cname);
        cname.setEnabled(false);

        this.add(setting);
        setting.add(cnameAutoUpdate);
        setting.add(preferFontSize);

        this.add(help);
        help.add(about);

        group.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vector<Shapes> shapes = new Vector<Shapes>();
                for(int i=0; i<Main.canva.shapesList.size(); i++)
                {
                    if(Main.canva.shapesList.get(i).isSelected())
                    {
                        shapes.add(Main.canva.shapesList.get(i));
                        shapes.lastElement().setSelected(false);;
                        Main.canva.shapesList.remove(i);
                        i--;
                    }
                }
                Main.canva.shapesList.add(new composite(shapes));
                Main.canva.shapesList.lastElement().setSelected(true);
                Main.canva.repaint();
                group.setEnabled(false);
                ungroup.setEnabled(true);
            }
        });

        ungroup.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                var curComposite = composite.selectedComposite;
                for(var shape : curComposite.getsubShapes())
                {
                    Main.canva.shapesList.add(shape);
                    Main.canva.shapesList.lastElement().setSelected(true);
                }
                Main.canva.shapesList.remove(curComposite);
                Main.canva.repaint();
                group.setEnabled(true);
                ungroup.setEnabled(false);
            }
        });
        
        cname.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                for(int i=0; i<Main.canva.shapesList.size(); i++)
                {
                    var curShape = Main.canva.shapesList.get(i);
                    if(curShape instanceof composite)
                        continue;
                    if(curShape.isSelected())
                    {
                        String originText = curShape.getName();
                        JFrame f = new JFrame();
                        JDialog msgbox = new JDialog(f);
                        msgbox.setTitle("Change " + curShape.getName() + " name");
                        msgbox.setLayout(new BorderLayout());
                        JTextArea area = new JTextArea(curShape.getName());
                        JButton btn_OK = new JButton("OK"); 
                        JButton btn_Cancel = new JButton("Cancel");

                        btn_OK.setPreferredSize(new Dimension(80, 80));
                        btn_Cancel.setPreferredSize(new Dimension(80, 80));

                        area.getDocument().addDocumentListener(new DocumentListener() {

                            @Override
                            public void changedUpdate(DocumentEvent arg0) { }

                            @Override
                            public void insertUpdate(DocumentEvent arg0) {
                                if(cnameAutoUpdate.isSelected())
                                {
                                    curShape.setName(area.getText());
                                    Main.canva.repaint();
                                }
                            }

                            @Override
                            public void removeUpdate(DocumentEvent arg0) {
                                if(cnameAutoUpdate.isSelected())
                                {
                                    curShape.setName(area.getText());
                                    Main.canva.repaint();
                                }
                            }
                            
                        });

                        btn_OK.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                curShape.setName(area.getText());
                                msgbox.setVisible(false);
                            }
                            
                        });
                        btn_Cancel.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                curShape.setName(originText);
                                msgbox.setVisible(false);
                            }
                            
                        });

                        msgbox.add(area, BorderLayout.NORTH);
                        msgbox.add(btn_OK, BorderLayout.WEST);
                        msgbox.add(btn_Cancel, BorderLayout.EAST);
                        msgbox.setSize(260, 150);
                        msgbox.setVisible(true);
                        break;
                    }
                }
                Main.frame.requestFocus();
                repaint();
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
        preferFontSize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.canva.repaint();
            }
            
        });
    }
}
