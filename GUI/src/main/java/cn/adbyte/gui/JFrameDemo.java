package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam on 2017/6/28.
 */
public class JFrameDemo extends JFrame{
    public JFrameDemo(String title,LayoutManager layout,int w,int h) throws HeadlessException {
        super(title);
        setSize(w,h);
        setLayout(layout);

        //---------------------------设置窗口居中----------------------------------------------------------
        int windowWidth = getWidth();               //获得窗口宽
        int windowHeight = getHeight();             //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();             //定义工具包
        Dimension screenSize = kit.getScreenSize();            //获取屏幕的尺寸
        int screenWidth = screenSize.width;                    //获取屏幕的宽
        int screenHeight = screenSize.height;                  //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public JFrameDemo(String title,LayoutManager layout) throws HeadlessException {
        super(title);
        setSize(640,480);
        setLayout(layout);

        //---------------------------设置窗口居中----------------------------------------------------------
        int windowWidth = getWidth();               //获得窗口宽
        int windowHeight = getHeight();             //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();             //定义工具包
        Dimension screenSize = kit.getScreenSize();            //获取屏幕的尺寸
        int screenWidth = screenSize.width;                    //获取屏幕的宽
        int screenHeight = screenSize.height;                  //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        JFrameDemo jFrameDemo = new JFrameDemo("我的第一个窗口",new FlowLayout(),350,280);
        JButton jButton = new JButton("点击");
        jFrameDemo.getContentPane().add(jButton);
        jFrameDemo.setVisible(true);
    }
}


class JFrameDemo2 extends JFrame{
    static JFrameDemo2 win1;
    static JFrameDemo2 win2;
    public static void main(String[] args) {
        final int i = 007;
        final int j = 0x3abb;
        final int m = 5000;
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

class JButtonListenerDemo3 extends JFrame implements ActionListener{

    public static void main(String[] args) {
        JButtonListenerDemo3 jButtonListenerDemo3 = new JButtonListenerDemo3("按钮事件", 250, 200);
        jButtonListenerDemo3.setVisible(true);
    }

    public JButtonListenerDemo3(String title, int w, int h) throws HeadlessException {
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