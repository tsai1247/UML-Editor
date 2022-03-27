import javax.swing.*;
import java.awt.event.*;



public class Menu extends JPanel{
    public enum Selected {SELECT, ASSOCIATION, GENERALIZATION, COMPOSITION, CLASS, USECASE}
    public static Selected currentSelected = Selected.ASSOCIATION;
    final int function_size = 6;

    JToggleButton[] options = new JToggleButton [function_size];
    String[] option_names = {"select", "association line", "generalization line", "composition line", "class", "use case"};
    
    
    public Menu()
    {
    	this.setLayout(null);
        this.setBounds(0, 0, 100, 600);
        for(int i=0; i<function_size; i++)
        {
            options[i] = new JToggleButton(option_names[i]);
            options[i].setBounds(0, i*94, 100, 94);
            this.add(options[i]);
            options[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    for(var option : options)
                    {
                        option.setSelected(false);
                    }
                    ((JToggleButton)e.getSource()).setSelected(true);
                    for(int i = 0; i < options.length; i++)
                    {
                        if(options[i].isSelected())
                        {
                            currentSelected = Selected.values()[i];
                            break;
                        }
                    }

                }
            });
        }
        options[currentSelected.ordinal()].setSelected(true);
    }
}
