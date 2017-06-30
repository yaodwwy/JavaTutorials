package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/29.
 */
public class CardLayoutDemo {
    public static void main(String[] args) {
        JButton button1 = new JButton("第一个"), button2 = new JButton("上一个"),
                button3 = new JButton("下一个"), button4 = new JButton("最后一个");

        JFrame mw = new JFrame("窗口");

        mw.setSize(580, 430);
        Container contentPane = mw.getContentPane();

        BorderLayout borderLayout = new BorderLayout();
        contentPane.setLayout(borderLayout);

        JPanel btnPanel = new JPanel(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel jPanel = new JPanel(cardLayout);

        button1.addActionListener(e -> cardLayout.first(jPanel));
        btnPanel.add(button1);
        button2.addActionListener(e -> cardLayout.previous(jPanel));
        btnPanel.add(button2);
        button3.addActionListener(e -> cardLayout.next(jPanel));
        btnPanel.add(button3);
        button4.addActionListener(e -> cardLayout.last(jPanel));
        btnPanel.add(button4);

        for (int i = 0; i < 10; i++) {
            Label label = new Label("Label " + i, Label.CENTER);
            label.setBackground(new Color(255 - (i * 10), 255 - (i * 5), 255 - (i * 2)));
            jPanel.add(label);
        }

        contentPane.add(btnPanel, BorderLayout.SOUTH);
        contentPane.add(jPanel, BorderLayout.CENTER);


        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mw.setVisible(true);
    }
}
