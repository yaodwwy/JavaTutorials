package cn.adbyte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Adam on 2017/8/24.
 * 示例 6.8
*/

class MyGraphicsPanel extends JPanel {

    public void print(int r) {
        Graphics g = getGraphics();
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.red);
        g.fillOval(10, 10, r, r);
    }
}

class MyMouseTest extends JFrame implements MouseListener {
    JTextArea text;
    MyGraphicsPanel panel;
    int x, y, r = 10;
    int mouseFlg = 0;
    private String mouseStates[] = {"按下", "松开", "进来", "离开", "双击"};

    public MyMouseTest(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container con = this.getContentPane();
        con.setLayout(new GridLayout(2, 1));
        this.setSize(200, 300);
        this.setLocation(100, 100);
        panel = new MyGraphicsPanel();
        con.add(panel);
        text = new JTextArea(10, 20);
        text.setBackground(Color.BLUE);
        con.add(text);
        addMouseListener(this);
        this.setVisible(true);
        this.pack();
    }

    public void print(Graphics g) {
        r = r + 4;
        if (r > 80) {
            r = 10;
        }
        text.append(mouseStates[mouseFlg] + " 了，位置是：" + x + " , " + y + "\n");
        panel.print(r);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 0;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 1;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 2;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 3;
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            x = e.getX();
            y = e.getY();
            mouseFlg = 4;
            repaint();
        }
    }
}

public class MouseListenerDemo {
    static JButton btn;
    public static void main(String[] args) {
        //btn = new JButton("按钮也能发生鼠标事件");
        new MyMouseTest("鼠标事件示意程序");
    }
}
