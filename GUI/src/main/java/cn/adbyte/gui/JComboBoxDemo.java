package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Adam on 2017/7/1.
 */
public class JComboBoxDemo implements ItemListener,ActionListener{
    String[] proList={"吃饭","睡觉","打豆豆"};
    JTextField textField;
    JComboBox jComboBox;
    public JComboBoxDemo() {
        JFrameDemo jComboBoxDemo = new JFrameDemo("JComboBoxDemo", new FlowLayout(), 640, 480);
        Container contentPane = jComboBoxDemo.getContentPane();
        contentPane.setBackground(Color.GRAY);
        jComboBox = new JComboBox(proList);
        jComboBox.addActionListener(this);
        jComboBox.addItemListener(this);
        jComboBox.setEditable(true);
        contentPane.add(jComboBox);
        textField = new JTextField(10);
        contentPane.add(textField);
        jComboBoxDemo.setVisible(true);
    }

    public static void main(String[] args) {
        JComboBoxDemo jComboBoxDemo = new JComboBoxDemo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jComboBox){
            textField.setText(jComboBox.getSelectedItem().toString());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==jComboBox){
            textField.setText(jComboBox.getSelectedItem().toString());
        }
    }
}
