import javax.swing.*;

public class Main {
    
    public static MenuBar menuBar = new MenuBar();
    public static JFrame frame;
    public static SideBar sideBar = new SideBar();
    public static Canvas canva  = new Canvas();


    private static void createAndShowGUI() {
    //#region JFrame head
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

    //#endregion

        frame.setJMenuBar(menuBar);

        sideBar.setBounds(0, 0, 100, 600);
        frame.getContentPane().add(sideBar);
        
        frame.getContentPane().add(canva.panel);

    //#region JFrame tail
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
    //#endregion
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}