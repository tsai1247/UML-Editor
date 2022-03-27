import javax.swing.*;

public class Main {
    private static void createAndShowGUI() {
//#region JFrame head
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
//#endregion

        Menu menu = new Menu();
        menu.setBounds(0, 0, 100, 600);
        frame.getContentPane().add(menu);
        
        Canvas canva  = new Canvas();
        frame.getContentPane().add(canva.panel);

//#region JFrame tail
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
//#endregion
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}