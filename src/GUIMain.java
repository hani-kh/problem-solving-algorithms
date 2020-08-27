

import core.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class GUIMain {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {  }

        JFrame j = new JFrame();
        j.setTitle("Traveler Solver");

        j.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        j.setSize(new Dimension(900, 600));
        j.add(new MainWindow());
        j.setVisible(true);

    }

}
