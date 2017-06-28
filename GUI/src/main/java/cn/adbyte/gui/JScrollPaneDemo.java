package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/28.
 */
public class JScrollPaneDemo extends JScrollPane{
    public JScrollPaneDemo(Component view) {
        setViewportView(view);
    }

    public static void main(String[] args) {
        JTextArea jTextArea = new JTextArea(20,30);
        JScrollPaneDemo jScrollPaneDemo = new JScrollPaneDemo(jTextArea);
        jScrollPaneDemo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneDemo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame jFrame = new JFrame("title");
        jFrame.getContentPane().add(jScrollPaneDemo);
        jFrame.setSize(300,200);
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);

    }
}
