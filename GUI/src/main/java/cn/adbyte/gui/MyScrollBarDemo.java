package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by Adam on 2017/8/22.
 * 例6.7
*/
 public class MyScrollBarDemo extends JScrollBar {


    public MyScrollBarDemo(int orientation, int value, int extent, int min, int max) {
        super(orientation, value, extent, min, max);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(125, 20);
    }

    public static void main(String[] args) {
        MyWindows demo = new MyWindows("滚动条实例");

    }
}

class MyWindows extends JFrame implements ActionListener, AdjustmentListener {
    private JButton button;
    private JTextField text;
    private boolean barOpened;

    public MyWindows(String title) throws HeadlessException {
        super(title);
        MyScrollBarDemo tempBar = new MyScrollBarDemo(JScrollBar.HORIZONTAL, 10, 10, 0, 255);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        this.setSize(200, 100);
        this.setLocation(100, 100);
        button = new JButton("开关滚动条");
        button.addActionListener(this);
        barOpened = false;
        tempBar.addAdjustmentListener(this);
        text = new JTextField("滚动条关闭", 20);
        contentPane.add(button);
        contentPane.add(text);
        contentPane.add(tempBar);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (barOpened) {
                text.setText("滚动条关闭");
            } else {
                text.setText("滚动条打开");
            }
            barOpened = !barOpened;
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (barOpened) {
            MyScrollBarDemo myScrollBar = (MyScrollBarDemo) e.getAdjustable();
            text.setText("选择的值是："+myScrollBar.getValue());
        }
    }
}
