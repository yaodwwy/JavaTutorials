package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 2017/6/30.
 */
public class SwingDemo {
    public static void main(String[] args) {
        FlowLayout layout = new FlowLayout();
        CardLayout cardLayout = new CardLayout();
        BorderLayout borderLayout = new BorderLayout();

        layout.setAlignment(FlowLayout.LEFT);

        MyJFrame demos = new MyJFrame("Demos", layout, 650, 160);
        MyJTextArea jTextArea = new MyJTextArea("文本区滚动条", 3, 18);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        MyJPanel myJPanel = new MyJPanel(cardLayout, 300, 80);
        myJPanel.add(jScrollPane);

        demos.add(myJPanel);

        MyLabel myLabel = new MyLabel("标签", 120, 80);
        MyJTextField myJTextField = new MyJTextField("文本框",8);

        MyJPanel myJPanel1 = new MyJPanel(borderLayout, 180, 80);

        myJPanel1.add(myLabel,BorderLayout.NORTH);
        myJPanel1.add(myJTextField,BorderLayout.SOUTH);

        MyJPanel myJPanel2 = new MyJPanel(borderLayout, 180, 80);
        MyLabel myLabel2 = new MyLabel("面板", 120, 80);
        myJPanel2.add(myLabel2,BorderLayout.CENTER);

        MyJPanel myJPanel3 = new MyJPanel(borderLayout, 180, 80);
        MyLabel myLabel3 = new MyLabel("面板", 120, 80);
        myJPanel3.add(myLabel3,BorderLayout.CENTER);

        demos.add(myJPanel1);
        demos.add(myJPanel2);
        demos.add(myJPanel3);

        demos.setVisible(true);
    }

    static class MyLabel extends JLabel {
        public MyLabel(String text, int w, int h) {
            super(text);
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setSize(w, h);
        }
    }


    static class MyJTextField extends JTextField {
        public MyJTextField(String text, int c) {
            super(text,c);
            this.setAutoscrolls(true);
        }
    }
    static class MyJTextArea extends JTextArea {
        public MyJTextArea(String text, int w, int h) {
            super(text,w, h);
            this.setAutoscrolls(true);
            this.setLineWrap(true);
        }
    }


    static class MyJPanel extends JPanel {
        public MyJPanel(LayoutManager layout,int w,int h) {
            super(layout);
            this.setBorder(BorderFactory.createDashedBorder(Color.gray));
            this.setSize(w,h);
        }
    }
    static class MyJFrame extends JFrame {
        public MyJFrame(String title, LayoutManager layout, int w, int h) throws HeadlessException {
            super(title);
            Container contentPane = this.getContentPane();
            contentPane.setLayout(layout);
            this.setSize(w, h);
//            this.setResizable(false);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


            //---------------------------设置窗口居中----------------------------------------------------------
            int windowWidth = this.getWidth();               //获得窗口宽
            int windowHeight = this.getHeight();             //获得窗口高
            Toolkit kit = Toolkit.getDefaultToolkit();             //定义工具包
            Dimension screenSize = kit.getScreenSize();            //获取屏幕的尺寸
            int screenWidth = screenSize.width;                    //获取屏幕的宽
            int screenHeight = screenSize.height;                  //获取屏幕的高
            this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
        }
    }


}
