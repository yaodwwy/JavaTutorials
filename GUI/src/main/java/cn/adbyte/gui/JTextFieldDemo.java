package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/29.
 */
public class JTextFieldDemo {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("窗口");
        jFrame.setSize(550,400);
        jFrame.getContentPane().setLayout(new FlowLayout());

        JTextField jTextField = new JTextField(12);
        jTextField.setColumns(10);
        jTextField.setFont(Font.getFont("Arial"));
        jTextField.setEditable(true);
        jTextField.setHorizontalAlignment(JTextField.CENTER);
        jFrame.add(jTextField);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
