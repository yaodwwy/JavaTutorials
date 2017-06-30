package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/29.
 */
public class BorderLayoutDemo {

    public static void main(String[] args) {
        JLabel label1,label2,label3,label4,label5;

        JFrame mw = new JFrame("窗口");

        mw.setSize(250,200);
        Container contentPane = mw.getContentPane();
        BorderLayout borderLayout = new BorderLayout();
        contentPane.setLayout(borderLayout);

        label1 = new JLabel("标签1");
        contentPane.add(label1,BorderLayout.EAST);

        label2 = new JLabel("标签2",JLabel.CENTER);
        contentPane.add(label2,BorderLayout.SOUTH);

        label3 = new JLabel("标签3");
        contentPane.add(label3,BorderLayout.WEST);

        label4 = new JLabel("标签4",JLabel.CENTER);
        contentPane.add(label4,BorderLayout.NORTH);

        label5 = new JLabel("标签5",JLabel.CENTER);
        contentPane.add(label5,BorderLayout.CENTER);

        mw.setVisible(true);
    }
}
