package cn.adbyte.gui;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by Adam on 2017/6/28.
 */
public class AppletDemo extends Applet {
    JPanelDemo jPanelDemo1, jPanelDemo2;
    JButton jButton;

    public void init() {
        FlowLayout layout = new FlowLayout();
        jPanelDemo1 = new JPanelDemo(layout, "确定", "取消", "标签，在面板1中");
        jPanelDemo2 = new JPanelDemo(layout, "确定", "取消", "标签，在面板2中");
        jButton = new JButton("新按钮");
        add(jPanelDemo1);
        add(jPanelDemo2);
        add(jButton);
        setSize(300, 200);
    }
}
class JPanelDemo extends JPanel {
    JButton btn1, btn2;
    JLabel jLabel;

    public JPanelDemo(LayoutManager layout, String s1, String s2, String s3) {
        super(layout);
        btn1 = new JButton(s1);
        btn2 = new JButton(s2);
        jLabel = new JLabel(s3);
        add(btn1);
        add(btn2);
        add(jLabel);
    }
}