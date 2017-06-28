package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam on 2017/6/28.
 */
public class JFrameDemo extends JFrame{
    public JFrameDemo(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        JFrameDemo jFrameDemo = new JFrameDemo("我的第一个窗口");
        jFrameDemo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrameDemo.setSize(350,280);

        //---------------------------设置窗口居中----------------------------------------------------------
        int windowWidth = jFrameDemo.getWidth();               //获得窗口宽
        int windowHeight = jFrameDemo.getHeight();             //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();             //定义工具包
        Dimension screenSize = kit.getScreenSize();            //获取屏幕的尺寸
        int screenWidth = screenSize.width;                    //获取屏幕的宽
        int screenHeight = screenSize.height;                  //获取屏幕的高
        jFrameDemo.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示

        JButton jButton = new JButton("点击");
        jFrameDemo.getContentPane().add(jButton);
        jFrameDemo.setVisible(true);
    }
}


class JFrameDemo2 extends JFrame{
    static JFrameDemo2 win1;
    static JFrameDemo2 win2;
    public static void main(String[] args) {
        JButton jButton1 = new JButton("按钮1");
        win1 = new JFrameDemo2("窗口标题1", jButton1, Color.gray, 300, 400);

        JButton jButton2 = new JButton("按钮1");
        win2 = new JFrameDemo2("窗口标题2", jButton2, Color.blue, 300, 400);

        win1.setVisible(true);
        win2.setVisible(true);

    }
    public JFrameDemo2(String title,JButton button,Color color,int w,int h) throws HeadlessException {
        super(title);
        this.setTitle(title);
        this.setSize(w,h);
        this.getContentPane().add(button);
        this.setBackground(color);
    }

}

class JFrameDemo3 extends JFrame implements ActionListener{

    public static void main(String[] args) {
        JFrameDemo3 jFrameDemo3 = new JFrameDemo3("按钮事件", 250, 200);
        jFrameDemo3.setVisible(true);
    }

    public JFrameDemo3(String title,int w,int h) throws HeadlessException {
        super(title);
        setSize(w,h);
        Container contentPane = getContentPane();

        JButton jButton1 = new JButton("red");
        jButton1.addActionListener(this);
        contentPane.add(jButton1);
        JButton jButton2 = new JButton("green");
        jButton2.addActionListener(this);
        contentPane.add(jButton2);


        contentPane.setLayout(new FlowLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container contentPane = getContentPane();
        if(e.getActionCommand().equals("red")){
            contentPane.setBackground(Color.RED);
        }else if(e.getActionCommand().equals("green")){
            contentPane.setBackground(Color.GREEN);
        }
    }
}