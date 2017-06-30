package cn.adbyte.gui;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Adam on 2017/6/30.
 */
public class JCheckboxDemo {
    public static void main(String[] args) {
        JFrameDemo jCheckBoxDemo = new JFrameDemo("JCheckBoxDemo");
        jCheckBoxDemo.setSize(400,300);
        MyPanel myPanel = new MyPanel();
        jCheckBoxDemo.add(myPanel);
        jCheckBoxDemo.setVisible(true);
    }

    static class MyPanel extends JPanel {
        JCheckBox b1,b2,b3;
        public MyPanel() {
            ButtonGroup buttonGroup = new ButtonGroup();
            b1=new JCheckBox("足球",true);
            b1.addItemListener(e -> b3.setSelected(true));
            b2=new JCheckBox("排球");
            b3=new JCheckBox("篮球");
            add(b1);
            add(b2);
            add(b3);
            buttonGroup.add(b2);
            buttonGroup.add(b3);
        }
    }
}
