import javax.swing.*;
import java.awt.*;

/**
 * Created by Acerolla on 17.03.2017.
 */
public class SwingPanelTest extends JFrame{
    public SwingPanelTest() {
        super("TestPanel");
        setVisible(true);
        JPanel panel = new JPanelTest();
        add(panel);

    }



    public static void main(String[] args) {
        SwingPanelTest panelTest = new SwingPanelTest();
        panelTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
