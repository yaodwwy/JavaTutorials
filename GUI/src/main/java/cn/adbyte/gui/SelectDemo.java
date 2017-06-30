package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/30.
 */
public class SelectDemo {
    public static void main(String[] args) {
        MyJFrame myJFrame = new MyJFrame("SelectDemo",700,600);
        myJFrame.add(new MyPanel1(new GridLayout(1,3)));
        myJFrame.setVisible(true);
    }

    static class MyWindow{
        static String[] fName = {"惠普","IBM","戴尔"};
    }

    static class MyPanel1 extends JPanel{
        public MyPanel1(LayoutManager layout) {
            super(layout);
            ButtonGroup buttonGroup = new ButtonGroup();
            JLabel label = new JLabel();
            label.setName("计算机3选1");
            label.setText("计算机3选1:");
            add(label);
            for (int i = 0; i < MyWindow.fName.length; i++) {
                JRadioButton radioButton = new JRadioButton(MyWindow.fName[i] + "计算机", false);
                buttonGroup.add(radioButton);
                add(radioButton);
            }



        }
    }
    static class MyJFrame extends JFrame{
        public MyJFrame(String title,int w,int h) throws HeadlessException {
            super(title);
            getContentPane().setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(w,h);
        }
    }
}
