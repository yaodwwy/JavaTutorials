package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created by Adam on 2017/6/29.
 */
public class GridLayoutDemo {
    public static void main(String[] args) {
        JLabel label1,label2,label3,label4,label5;
        JButton button1,button2,button3,button4,button5;

        JFrame mw = new JFrame("窗口");

        mw.setSize(250,200);
        Container contentPane = mw.getContentPane();
        GridLayout gridLayout = new GridLayout(5,4);
        contentPane.setLayout(gridLayout);

        for (int i=0;i<5;i++){
            for (int j=0;j<4;j++){
                label1 = new JLabel("标签"+i+j,JLabel.CENTER);
                button1 = new JButton("按钮"+i+j);
                contentPane.add(label1);
                contentPane.add(button1);
            }
        }


        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mw.setVisible(true);
    }
}
