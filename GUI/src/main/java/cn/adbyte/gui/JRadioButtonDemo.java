package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/30.
 */
public class JRadioButtonDemo{
    public static void main(String[] args) {
new MyJRadioButton("JRadioButtonDemo",300,300);
    }

    static class MyJRadioButton extends JFrame{
        public MyJRadioButton(String title,int w,int h) throws HeadlessException {
            super(title);
            setSize(w,h);
            getContentPane().setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JRadioButton rb1 = new JRadioButton("运动");
            JRadioButton rb2 = new JRadioButton("吃饭");
            JRadioButton rb3 = new JRadioButton("睡觉");

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(rb1);
            buttonGroup.add(rb3);
            add(rb1);
            add(rb2);
            add(rb3);
            setVisible(true);
        }
    }
}
