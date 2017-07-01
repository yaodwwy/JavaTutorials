package cn.adbyte.gui;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Adam on 2017/6/30.
 */
public class SelectDemo extends Applet {
    MyWindow window = new MyWindow("示例小程序");

    public static void main(String[] args) {
        MyWindow window = new MyWindow("示例小程序");
    }

    static class MyWindow extends JFrame implements ItemListener {
        static String[] fName = {"惠普", "IBM", "戴尔"};
        static String[] fNum = {"1台", "2台", "3台"};
        static Double[][] priTbl = {{1.20, 1.15, 1.10}, {1.70, 1.65, 1.60}, {1.65, 1.60, 1.58}};
        MyPanel1 myPanel1;
        MyPanel2 myPanel2;
        static int production = -1;
        TextArea textArea1;
        TextArea textArea2;
        JLabel jLabel1;
        JLabel jLabel2;

        public MyWindow(String title) throws HeadlessException {
            super(title);
            Container contentPane = getContentPane();
            contentPane.setLayout(new GridLayout(4, 2));
            setLocation(300, 300);
            setSize(800, 280);
            GridLayout gridLayout = new GridLayout(2, 3);
            myPanel1 = new MyPanel1(gridLayout);
            myPanel2 = new MyPanel2(gridLayout);
            jLabel1 = new JLabel();
            jLabel1.setText("产品介绍");
            jLabel1.setHorizontalAlignment(JLabel.CENTER);
            jLabel2 = new JLabel();
            jLabel2.setText("产品价格");
            jLabel2.setHorizontalAlignment(JLabel.CENTER);

            textArea1 = new TextArea();
            textArea2 = new TextArea();
            contentPane.add(jLabel1);
            contentPane.add(jLabel2);
            contentPane.add(myPanel1);
            contentPane.add(myPanel2);
            contentPane.add(textArea1);
            contentPane.add(textArea2);

            myPanel1.rb1.addItemListener(this);
            myPanel1.rb2.addItemListener(this);
            myPanel1.rb3.addItemListener(this);

            myPanel2.cb1.addItemListener(this);
            myPanel2.cb2.addItemListener(this);
            myPanel2.cb3.addItemListener(this);


            this.setVisible(true);
        }


        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getItemSelectable() == myPanel1.rb1) {
                production = 0;
                textArea1.setText(fName[0] + "公司生产");
                textArea2.setText("");
            } else if (e.getItemSelectable() == myPanel1.rb2) {
                production = 1;
                textArea1.setText(fName[1] + "公司生产");
                textArea2.setText("");
            } else if (e.getItemSelectable() == myPanel1.rb3) {
                production = 2;
                textArea1.setText(fName[2] + "公司生产");
                textArea2.setText("");
            } else {
                if (production == -1) return;
                if (e.getItemSelectable() == myPanel2.cb1) {
                    textArea2.setText(priTbl[production][0] + "万元/台");
                } else if (e.getItemSelectable() == myPanel2.cb2) {
                    textArea2.setText(priTbl[production][1] + "万元/台");
                } else if (e.getItemSelectable() == myPanel2.cb3) {
                    textArea2.setText(priTbl[production][2] + "万元/台");
                }
            }
        }
    }

    static class MyPanel1 extends JPanel {
        JRadioButton rb1, rb2, rb3;

        public MyPanel1(LayoutManager layout) {
            super(layout);
            ButtonGroup buttonGroup = new ButtonGroup();
            JLabel label = new JLabel();
            label.setText("计算机3选1:");
            add(label);
            rb1 = new JRadioButton(MyWindow.fName[0] + "计算机", false);
            rb2 = new JRadioButton(MyWindow.fName[1] + "计算机", false);
            rb3 = new JRadioButton(MyWindow.fName[2] + "计算机", false);
            buttonGroup.add(rb1);
            buttonGroup.add(rb2);
            buttonGroup.add(rb3);
            add(rb1);
            add(rb2);
            add(rb3);
        }
    }

    static class MyPanel2 extends JPanel {
        JCheckBox cb1, cb2, cb3;

        public MyPanel2(LayoutManager layout) {
            super(layout);
            ButtonGroup buttonGroup = new ButtonGroup();
            JLabel label = new JLabel();
            label.setText("购买数量：");
            add(label);
            cb1 = new JCheckBox(MyWindow.fNum[0], false);
            cb2 = new JCheckBox(MyWindow.fNum[1], false);
            cb3 = new JCheckBox(MyWindow.fNum[2], false);
            buttonGroup.add(cb1);
            buttonGroup.add(cb2);
            buttonGroup.add(cb3);
            add(cb1);
            add(cb2);
            add(cb3);
        }
    }
}
