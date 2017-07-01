package cn.adbyte.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by Adam on 2017/7/1.
 */
public class JListDemo {
    static JList jList1,jList2;
    static JTextArea jTextArea;
    static String[] news = {"人民日报","解放日报","新民晚报","文汇报"};
    static String[] sports = {"足球","排球","乒乓球","篮球"};

    public static void main(String[] args) {
        GridLayout layout = new GridLayout(2, 2);
        JFrameDemo demo = new JFrameDemo("Demo", layout, 640, 480);
        Container contentPane = demo.getContentPane();
        contentPane.setBackground(Color.PINK);
        jList1 = new JList(news);
        jList1.setVisibleRowCount(3);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Listener listener = new Listener();
        Listener listener2 = new Listener();
        jList1.addListSelectionListener(listener);
        jList2 = new JList(sports);
        jList2.setVisibleRowCount(2);
        jList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jList2.addListSelectionListener(listener2);

        contentPane.add(jList1);
        contentPane.add(jList2);
        jTextArea = new JTextArea(10,20);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        demo.add(jScrollPane);

        demo.setVisible(true);
        demo.pack();
    }

    static class Listener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getSource() == jList1){
                jTextArea.append("\n");
                String str = ((JList) e.getSource()).getSelectedValue().toString();
                for (String s : news) {
                    if(s.equals(str)){
                        jTextArea.append(str+"被选中 ");
                    }
                }
            }else if(e.getSource() == jList2){
                jTextArea.append("\n");
                String str = ((JList) e.getSource()).getSelectedValue().toString();
                for (String s : sports) {
                    if(s.equals(str)){
                        jTextArea.append(str+"被选中 ");
                    }
                }
            }
        }
    }
}
