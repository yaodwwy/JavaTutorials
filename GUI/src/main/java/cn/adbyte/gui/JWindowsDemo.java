package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/28.
 */
public class JWindowsDemo extends JWindow {
    public JWindowsDemo(Frame owner,Color backColor,Color foreColor,int w,int h) {
        super(owner);
        this.setSize(w,h);
        this.setBackground(backColor);
        this.setForeground(foreColor);
    }

    public static void main(String[] args) {
        JButton jButton = new JButton("按钮");
        JFrameDemo2 frame = new JFrameDemo2("windows",jButton,Color.gray,300,280);
        JWindowsDemo jWindowsDemo = new JWindowsDemo(frame,Color.GRAY,Color.red,50,50);
        jWindowsDemo.setVisible(true);
        frame.setVisible(true);
    }
}
