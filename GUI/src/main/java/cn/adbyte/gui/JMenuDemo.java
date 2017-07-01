package cn.adbyte.gui;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Adam on 2017/7/1.
 */
public class JMenuDemo extends Applet implements ActionListener {
    JTextField jTextField;
    JMenuDemo menuDemo;
    JButton button;
    boolean bflg;

    public JMenuDemo() {
        JFrameDemo jMenuDemo = new JFrameDemo("JMenuDemo", new BorderLayout());
        Container contentPane = jMenuDemo.getContentPane();
        JMenu menu = new JMenu("体育(S)");
        menu.setMnemonic('S');
        JMenuListener listener = new JMenuListener();
        addItem(menu,"跑步", listener);
        addItem(menu,"跳绳", listener);
        addItem(menu,"打球", listener);
        JMenu menu2 = new JMenu("娱乐");
        addItem(menu2,"唱歌", listener);
        addItem(menu2,"跳舞", listener);
        addItem(menu2,"游戏", listener);

        JMenuBar jMenuBar = new JMenuBar();
        jTextField = new JTextField();
        jMenuBar.add(menu);
        jMenuBar.add(menu2);
        jMenuDemo.setJMenuBar(jMenuBar);
        contentPane.add(jTextField,BorderLayout.NORTH);
        jMenuDemo.setVisible(true);
    }

    @Override
    public void init() {
        button = new JButton("打开体育娱乐之窗");
        bflg = true;
        menuDemo = new JMenuDemo();
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            if (bflg){
                menuDemo.setVisible(true);
                bflg=false;
                button.setText("关闭体育娱乐之窗");
            }else {
                menuDemo.setVisible(false);
                bflg=true;
                button.setText("打开体育娱乐之窗");
            }
        }
    }

    class JMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextField.setText(e.getActionCommand()+" choose");
        }
    }

    private void addItem(JMenu menu, String menuName, ActionListener listener){
        JMenuItem item = new JMenuItem(menuName);
        item.setActionCommand(menuName);
        item.addActionListener(listener);
        JMenuItem item1 = new JMenuItem("子级别");
        item1.addActionListener(listener);
        JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("子多选");
        checkBoxMenuItem.addActionListener(listener);
        JMenu menu1 = new JMenu("子菜单");
        menu1.add(item1);
        menu1.add(checkBoxMenuItem);
        menu1.add(checkBoxMenuItem);
        menu.add(item);
        menu.add(menu1);
    }

    public static void main(String[] args) {
        new JMenuDemo();

    }
}
