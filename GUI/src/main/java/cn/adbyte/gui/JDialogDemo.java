package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam on 2017/8/21.
 * jDialog 样例 6.6
 */
public class JDialogDemo extends JFrame implements ActionListener {
    private static int flag = 0;
    private static JButton jButton1, jButton2;
    private static JTextField text1, text2;

    public JDialogDemo(String title) throws HeadlessException {
        super(title);
        Container con = this.getContentPane();
        con.setLayout(new GridLayout(2, 2));
        this.setSize(200, 100);
        this.setLocation(100, 100);
        jButton1 = new JButton("选择水果");
        jButton2 = new JButton("选择食品");
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        text1 = new JTextField(20);
        text2 = new JTextField(20);

        con.add(jButton1);
        con.add(jButton2);
        con.add(text1);
        con.add(text2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.pack();

    }

    public static void main(String[] args) {
        new JDialogDemo("样例6.6");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyDialog dialog;
        if (e.getSource() == jButton1) {
            dialog = new MyDialog(this, "水果");
            dialog.setVisible(true);
            flag = 1;
        } else if (e.getSource() == jButton2) {
            dialog = new MyDialog(this, "食品");
            dialog.setVisible(true);
            flag = 2;
        }
    }

    public static void returnName(String text) {
        if (flag == 1) {
            text1.setText("选择的水果是：" + text);
        } else if (flag == 2) {
            text2.setText("选择的食品是：" + text);
        }
    }
}

class MyDialog extends JDialog implements ActionListener {

    JLabel title;
    JTextField text;
    JButton done;

    public MyDialog(JFrame frame, String s) {
        super(frame, s, false);
        Container con = this.getContentPane();
        title = new JLabel("输入" + s + "名字");
        text = new JTextField(10);
        text.setEditable(true);
        con.setLayout(new FlowLayout());
        con.setSize(200, 100);
        setModal(false);
        done = new JButton("确定");
        done.addActionListener(this);
        con.add(title);
        con.add(text);
        con.add(done);
        con.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == text) {
            JDialogDemo.returnName(text.getText());
        } else if (e.getSource() == done) {
            JDialogDemo.returnName(text.getText());
            setVisible(false);
            dispose();
        }
    }

}
